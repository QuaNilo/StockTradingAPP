package com.example.TPSIMobileProjecto.ui.home.stockFragments.sharedViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit.TickerSummary

class SimpleChecklistSharedViewModel : ViewModel() {
    val addedItemsSimple = MutableLiveData<List<TickerSummary>>()
    val addedItemsChecklist = MutableLiveData<List<TickerSummary>>()


    fun setAddedItemsToSimple(items: MutableList<TickerSummary>) {
        addedItemsSimple.value = items
    }

    fun setAddedItemsToChecklist(items: MutableList<TickerSummary>) {
        addedItemsChecklist.value = items
    }
}