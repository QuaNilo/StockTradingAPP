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
import retrofit2.await


class BackendData {
    private val tickersAPI = RetrofitHelper.getInstance().create(retrofitInterface::class.java)
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private var tickersListDeferred: Deferred<List<String>>? = null

    init {
        getSymbols()
    }


    private fun getSymbols() {
        tickersListDeferred = coroutineScope.async {
            val symbolsResponse = tickersAPI.getSymbols()
            if (symbolsResponse.isSuccessful) {
                symbolsResponse.body() ?: emptyList()
            } else {
                emptyList()
            }
        }
    }

    suspend fun getTickersList(): List<String> {
        return tickersListDeferred?.await() ?: emptyList()
    }

    suspend fun fetchNews(): List<News> {
        val newsResponse : Response<List<News>>
        newsResponse = tickersAPI.getNews()
        var news :  List<News>?

        if (newsResponse.isSuccessful){
            news = newsResponse.body()
        }
        else{
            Log.e("MyTag: ", "Failed to get news")
            return emptyList()
        }
        if(news.isNullOrEmpty()){
            return emptyList()
        }
        return news

    }

    //TODO Create method to get a single Ticker detail

    suspend fun fetchTickerDetailsList(): List<TickerDetails> {
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
    }

    fun fetchTickerSummary(){}
    //TODO Implement fetchTickerSummary

}
