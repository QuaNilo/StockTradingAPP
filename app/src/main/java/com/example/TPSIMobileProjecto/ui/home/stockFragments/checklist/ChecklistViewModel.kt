package com.example.TPSIMobileProjecto.ui.home.stockFragments.checklist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.TPSIMobileProjecto.BackendData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit.TickerDetails

class ChecklistViewModel : ViewModel() {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    val data = BackendData()

    // Define a LiveData property to hold symbolDetailsList
    private val _symbolDetailsList = MutableLiveData<List<TickerDetails>>()
    val symbolDetailsList: LiveData<List<TickerDetails>> get() = _symbolDetailsList

    init {
        coroutineScope.launch {
            val symbolDetailsList = data.fetchTickerDetailsList()
            _symbolDetailsList.postValue(symbolDetailsList)
        }
    }
}