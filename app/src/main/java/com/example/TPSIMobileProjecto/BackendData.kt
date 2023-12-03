package com.example.TPSIMobileProjecto
import retrofit.News
import retrofit.TickerDetails
import retrofit.TickerSummary
import retrofit2.Response
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit.RetrofitHelper
import retrofit.retrofitInterface
import kotlinx.coroutines.Dispatchers
import android.util.Log
import kotlinx.coroutines.async
import kotlinx.coroutines.*
import java.lang.Exception


class BackendData {
    private val tickersAPI = RetrofitHelper.getInstance().create(retrofitInterface::class.java)
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    var tickersListDeferred: Deferred<List<String>>? = null

    init {
        getSymbols()
    }


    //Function to get all symbols
    fun getSymbols() {
        try {
            tickersListDeferred = coroutineScope.async {
                val symbolsResponse = tickersAPI.getSymbols()
                if (symbolsResponse.isSuccessful) {
                    symbolsResponse.body() ?: emptyList()
                } else {
                    emptyList()
                }
            }
        }catch (e : Exception){
            throw e
        }
    }


    //Function to call async and await all symbols to pass onto other functions
    suspend fun getTickersList(): List<String> {
        return tickersListDeferred?.await() ?: emptyList()
    }

    suspend fun fetchNews(): List<News> {
        try {
            val newsResponse: Response<List<News>>
            newsResponse = tickersAPI.getNews()
            var news: List<News>?

            if (newsResponse.isSuccessful) {
                news = newsResponse.body()
            } else {
                Log.e("MyTag: ", "Failed to get news")
                return emptyList()
            }
            if (news.isNullOrEmpty()) {
                return emptyList()
            }
            return news

        }catch (e : Exception){
            throw e
        }

    }

    suspend fun fetchTickerDetailsList(): List<TickerDetails> {
        try{
            val tickersList = getTickersList()
            if (tickersList.isNullOrEmpty()) {
                Log.e("MyTag: ", "Failed to fetch details for ticker: $tickersList")
                return emptyList()
            }

            val symbolDetailsList = mutableListOf<TickerDetails>()

            for (ticker in tickersList!!) {
                val responseSymbolDetail: Response<TickerDetails> = tickersAPI.getSymbolDetails(ticker)
                if (responseSymbolDetail.isSuccessful) {
                    val tickerDetails = responseSymbolDetail.body()
                    if (tickerDetails != null) {
                        symbolDetailsList.add(tickerDetails)
                    }
                } else {
                    // Handle the error, log it, or throw a custom exception
                    Log.e("MyTag: ", "Failed to fetch details for ticker: $ticker")
                }
            }
            return symbolDetailsList

        }catch (e: Exception){
            throw e
        }
    }

    suspend fun fetchTickerSummary(): List<TickerSummary> {
        try {
            val symbolSummaryList = mutableListOf<TickerSummary>()
            val tickersList = getTickersList()

            if (tickersList.isNullOrEmpty()) {
                Log.e("MyTag: ", "Failed to fetch details for ticker: $tickersList")
                return emptyList()
            }
            for (ticker in tickersList!!) {
                val responseSymbolSummary: Response<TickerSummary> =
                    tickersAPI.getSymbolSummary(ticker)
                if (responseSymbolSummary.isSuccessful) {
                    val tickerSummary = responseSymbolSummary.body()
                    if (tickerSummary != null) {
                        symbolSummaryList.add(tickerSummary)
                    }
                } else {
                    // Handle the error, log it, or throw a custom exception
                    Log.e("MyTag: ", "Failed to fetch summary for ticker: $ticker")
                }
            }
            Log.e("Summary : ", "Fetched $symbolSummaryList")
            return symbolSummaryList
        } catch (e: Exception) {
            throw e
        }


    }
}
