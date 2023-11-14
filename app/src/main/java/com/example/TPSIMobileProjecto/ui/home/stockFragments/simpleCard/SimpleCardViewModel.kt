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
    private val _tickerDetailsList = MutableLiveData<List<TickerDetails>>()
    val tickerDetailsList: LiveData<List<TickerDetails>> get() = _tickerDetailsList

    fun setTickerDetailsList(list: List<TickerDetails>) {
        _tickerDetailsList.value = list
    }
}