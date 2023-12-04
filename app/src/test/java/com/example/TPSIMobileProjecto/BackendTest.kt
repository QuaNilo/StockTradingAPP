package com.example.TPSIMobileProjecto

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit.News
import retrofit.RetrofitHelper
import retrofit.TickerDetails
import retrofit.TickerSummary
import retrofit.retrofitInterface


class BackendDataTest {
    private val tickersAPI = RetrofitHelper.getInstance().create(retrofitInterface::class.java)
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val backend = BackendData()

    @Test
    fun testGetSymbols() = runBlocking {
        // Call the function under test
        val symbolsResponse =  backend.getTickersList()

        assertTrue(symbolsResponse.isNotEmpty())
        assertTrue(symbolsResponse.contains("AAPL"))
        assertTrue(symbolsResponse.contains("GOOGL"))
        assertTrue(symbolsResponse.contains("MSFT"))
        symbolsResponse.map { assertTrue( it != null) }
        symbolsResponse.map { assertTrue( it is String) }
        return@runBlocking
    }

    @Test
    fun testGetNews() = runBlocking {
        // Call the function under test
        val response =  tickersAPI.getNews()

        // Assert that the result is a Deferred<List<String>>
        assertTrue(response.isSuccessful)
        assertTrue(response.body() is  List<News>)
        return@runBlocking
    }

    @Test
    fun testGetTickerDetails() = runBlocking {
            // Call the function under test
            val tickersList = backend.getTickersList()

            assertTrue(tickersList.isNotEmpty())
            assertTrue(tickersList.contains("AAPL"))
            assertTrue(tickersList.contains("GOOGL"))
            assertTrue(tickersList.contains("MSFT"))

            val response = tickersList.map { tickersAPI.getSymbolDetails(it) }

            response.map { assertTrue(it.isSuccessful) }
            response.map { assertTrue(it.body() is TickerDetails) }
            return@runBlocking
    }

    @Test
    fun testGetTickerSummary() = runBlocking {
        // Call the function under test
        val tickersList = backend.getTickersList()

        assertTrue(tickersList.isNotEmpty())
        assertTrue(tickersList.contains("AAPL"))
        assertTrue(tickersList.contains("GOOGL"))
        assertTrue(tickersList.contains("MSFT"))

        val response = tickersList.map { tickersAPI.getSymbolSummary(it) }

        response.map { assertTrue(it.isSuccessful) }
        response.map { assertTrue(it.body() is TickerSummary) }
        return@runBlocking
    }
}


