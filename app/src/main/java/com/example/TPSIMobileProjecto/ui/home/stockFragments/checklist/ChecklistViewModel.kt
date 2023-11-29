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
    private var _isDataLoadedSuccessfully = MutableLiveData<Boolean?>()
    val isDataLoadedSuccessfully: MutableLiveData<Boolean?> get() = _isDataLoadedSuccessfully


    init {
        //Check if it's the first time loading APP or coming from news Fragment
        if (fetchedData == null) {
            refreshData()
        }
    }

    fun setDataLoaded(isLoaded : Boolean){
        _isDataLoadedSuccessfully.postValue(null)
        if(isLoaded){
        _isDataLoadedSuccessfully.postValue(true)
        }else{
        _isDataLoadedSuccessfully.postValue(false)
        }
    }
    fun refreshData(isRefreshed : Boolean = false) {
        coroutineScope.launch {
            fetchedData = data.fetchTickerSummary()
            //Check if it came null from backend and handle it
            if (fetchedData == null || fetchedData!!.isEmpty() && !isRefreshed) {
                _isDataLoadedSuccessfully.postValue(false)
            } else {
                _isDataLoadedSuccessfully.postValue(true)
            }
            _symbolSummaryList.postValue(fetchedData)
        }
    }
}