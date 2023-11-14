package com.example.TPSIMobileProjecto.ui.home.stockFragments.checklist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.TPSIMobileProjecto.BackendData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit.TickerSummary

class ChecklistViewModel : ViewModel() {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    val data = BackendData()

    // Define a LiveData property to hold symbolSummaryList
    private val _symbolSummaryList = MutableLiveData<List<TickerSummary>>()
    val symbolSummaryList: LiveData<List<TickerSummary>> get() = _symbolSummaryList

    init {
        coroutineScope.launch {
            val symbolSummaryList = data.fetchTickerSummary()
            _symbolSummaryList.postValue(symbolSummaryList)
        }
    }
}