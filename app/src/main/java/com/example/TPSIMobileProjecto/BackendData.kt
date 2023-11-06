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


    init {
        // Start the coroutine in the init block
        getSymbols()
    }

    private var tickersList: List<String>? = null  // Change ResponseType to the actual type you expect

    private fun getSymbols() {
        coroutineScope.launch {
            try {
                val symbolsDeferred = async {
                    tickersAPI.getSymbols()
                }
                val symbolsResponse = symbolsDeferred.await()
                if (symbolsResponse.isSuccessful) {
                    tickersList = symbolsResponse.body()
                    // Handle the response or update UI as needed
                } else {
                    // Handle the case when the response is not successful
                }
            } catch (e: Exception) {
                // Handle exceptions, such as network errors
            }
        }
    }


    fun fetchNews(){}
    //TODO Implement fetchNews
    fun fetchTickersList(){}
    //TODO Implement fetchTickersList
    suspend fun fetchTickerDetails(): List<TickerDetails> {
        if (tickersList.isNullOrEmpty()) {
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
        Log.e("MyTag: ", "Trying to get symbolsDetailsList")
        Log.e("MyTag: ", "SymbolDetailsList: $symbolDetailsList")
        return symbolDetailsList
    }


    fun fetchTickerSummary(){}
    //TODO Implement fetchTickerSummary

}
