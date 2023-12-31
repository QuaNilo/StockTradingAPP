package com.example.TPSIMobileProjecto.ui.home.stockFragments.checklist

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
    private val _symbolSummaryList = MutableLiveData<List<TickerSummary>?>()
    val symbolSummaryList: MutableLiveData<List<TickerSummary>?> get() = _symbolSummaryList
    // Variable to store fetched data
    private var fetchedData: List<TickerSummary>? = null

    init {
        //Check if it's the first time loading APP or coming from news Fragment
        if (fetchedData == null) {
            refreshData()
        }
    }

    fun refreshData(isRefreshed : Boolean = false) {
        coroutineScope.launch {
            fetchedData = data.fetchTickerSummary()
            _symbolSummaryList.postValue(fetchedData)
        }
    }
}