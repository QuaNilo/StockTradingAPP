package com.example.TPSIMobileProjecto.ui.News

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.TPSIMobileProjecto.BackendData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit.News
import retrofit.TickerDetails

class NewsViewModel : ViewModel() {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    val data = BackendData()

    // Define a LiveData property to hold symbolDetailsList
    private val _newsList = MutableLiveData<List<News>>()
    val newsList : LiveData<List<News>> get() = _newsList

    init {
        coroutineScope.launch {
            val newsList = data.fetchNews()
            _newsList.postValue(newsList)
        }
    }
}