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
import android.os.Bundle
import android.util.Log


class data {

    fun fetchData(){
        val tickersAPI = RetrofitHelper.getInstance().create(retrofitInterface::class.java)
        val coroutineScope = CoroutineScope(Dispatchers.IO)
        // launching a new coroutine
        coroutineScope.launch {
            try{
                val symbolSummariesList = mutableListOf<TickerSummary>()
                val symbolDetailsList = mutableListOf<TickerDetails>()
                val tickersList: List<String>?
                val newsList : List<News>?

                val responseSymbols = tickersAPI.getSymbols()
                val responseNews = tickersAPI.getNews()

                if (responseNews.isSuccessful){
                    newsList = responseNews.body()
                    Log.d("MyTag: ", newsList.toString())
                }

                if (responseSymbols.isSuccessful) {
                    tickersList = responseSymbols.body()

                    if (tickersList != null) {
                        for (ticker in tickersList){
                            try {
                                val responseSymbolSummary: Response<TickerSummary> = tickersAPI.getSymbolSummary(ticker)
                                val responseSymbolDetail: Response<TickerDetails> = tickersAPI.getSymbolDetails(ticker)

                                if (responseSymbolSummary.isSuccessful) {
                                    val tickerSummary = responseSymbolSummary.body()
                                    val tickerDetails = responseSymbolDetail.body()

                                    if (tickerDetails != null) {
                                        symbolDetailsList.add(tickerDetails)
                                    }
                                    if (tickerSummary != null) {
                                        symbolSummariesList.add(tickerSummary)
                                    }
                                }
                            } catch (e: Exception) {
                                // Handle the exception, log or ignore it
                                Log.e("MyTag: ", "Error processing ticker $ticker: ${e.message}")
                            }
                        }
                        Log.d("MyTag: ", tickersList.toString())
                        Log.d("MyTag: ", symbolSummariesList[0].toString())
                        Log.d("MyTag: ", symbolDetailsList[0].toString())
                    }
                }

            }catch (e: Exception) {
                // Handle network or coroutine exception
                Log.e("MyTag: ", e.message ?: "Unknown error")
            }
        }

    }
}