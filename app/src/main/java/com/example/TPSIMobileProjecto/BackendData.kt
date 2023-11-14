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


class BackendData {
    private val tickersAPI = RetrofitHelper.getInstance().create(retrofitInterface::class.java)
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private var tickersListDeferred: Deferred<List<String>>? = null

    init {
        coroutineScope.launch {
            getSymbols()
        }
    }


    private suspend fun getSymbols() {
        try {
            tickersListDeferred = coroutineScope.async {
                try {
                    val symbolsResponse = tickersAPI.getSymbols()

                    if (symbolsResponse.isSuccessful) {
                        return@async symbolsResponse.body() ?: emptyList()
                    } else {
                        withContext(Dispatchers.Main) {
                            Log.e("MyTag: ", "Failed to fetch symbols")
                        }
                        return@async emptyList()
                    }
                } catch (e: CancellationException) {
                    throw e  // Propagate cancellation exception
                } catch (e: Exception) {
                    throw e
                }
            }

            tickersListDeferred?.await()

        } catch (e: CancellationException) {
            // Handle cancellation, if needed
            throw e  // Propagate cancellation exception
        } catch (e: Exception) {
            throw e
        }
    }

    private suspend fun getTickersList(): List<String> {
        return tickersListDeferred?.await() ?: emptyList()
    }

    suspend fun fetchNews(): List<News> = coroutineScope {
        try {
            val deferredNews = async {
                try {
                    val newsResponse: Response<List<News>> = tickersAPI.getNews()

                    if (newsResponse.isSuccessful) {
                        val news: List<News>? = newsResponse.body()

                        if (news.isNullOrEmpty()) {
                            return@async emptyList<News>()
                        }

                        return@async news
                    } else {
                        withContext(Dispatchers.Main) {
                            Log.e("MyTag: ", "Failed to get news")
                        }
                        return@async emptyList<News>()
                    }
                } catch (e: CancellationException) {
                    throw e  // Propagate cancellation exception
                } catch (e: Exception) {
                    throw e
                }
            }

            return@coroutineScope deferredNews.await()

        } catch (e: CancellationException) {
            // Handle cancellation, if needed
            throw e  // Propagate cancellation exception
        } catch (e: Exception) {
            throw e
        }
    }


    suspend fun fetchTickerDetailsList(): List<TickerDetails> = coroutineScope {
        try {
            val tickersList = getTickersList() ?: return@coroutineScope emptyList()

            val deferredDetails = tickersList.map { ticker ->
                async {
                    try {
                        val responseSymbolDetail: Response<TickerDetails> = tickersAPI.getSymbolDetails(ticker)
                        if (responseSymbolDetail.isSuccessful) {
                            responseSymbolDetail.body()
                        } else {
                            withContext(Dispatchers.Main) {
                                Log.e("MyTag: ", "Failed to fetch details for ticker: $ticker")
                            }
                            null
                        }
                    } catch (e: CancellationException) {
                        throw e
                    } catch (e: Exception) {
                        throw e
                    }
                }
            }

            return@coroutineScope deferredDetails.awaitAll().filterNotNull()

        } catch (e: CancellationException) {
            throw e
        }
    }

    suspend fun fetchTickerSummary(): List<TickerSummary> = coroutineScope {
        try {
            val tickersList = getTickersList() ?: return@coroutineScope emptyList()

            val deferredSummary = tickersList.map { ticker ->
                async {
                    try {
                        val responseSymbolSummary: Response<TickerSummary> = tickersAPI.getSymbolSummary(ticker)
                        if (responseSymbolSummary.isSuccessful) {
                            responseSymbolSummary.body()
                        } else {
                            withContext(Dispatchers.Main) {
                                Log.e("MyTag: ", "Failed to fetch summary for ticker: $ticker")
                            }
                            null
                        }
                    } catch (e: CancellationException) {
                        throw e
                    } catch (e: Exception) {
                        throw e
                    }
                }
            }

            return@coroutineScope deferredSummary.awaitAll().filterNotNull()

        } catch (e: CancellationException) {
            throw e
        }
    }


}
