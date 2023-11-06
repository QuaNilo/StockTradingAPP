package com.example.TPSIMobileProjecto.ui.home.stockFragments.simpleCard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.TPSIMobileProjecto.BackendData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit.RetrofitHelper
import retrofit.TickerDetails
import retrofit.retrofitInterface

class SimpleCardViewModel : ViewModel() {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    val data = BackendData()

    // Define a LiveData property to hold symbolDetailsList
    private val _symbolDetailsList = MutableLiveData<List<TickerDetails>>()
    val symbolDetailsList: LiveData<List<TickerDetails>> get() = _symbolDetailsList

    init {
        coroutineScope.launch {
            val symbolDetailsList = data.fetchTickerDetails()
            _symbolDetailsList.postValue(symbolDetailsList)
        }

    }

}