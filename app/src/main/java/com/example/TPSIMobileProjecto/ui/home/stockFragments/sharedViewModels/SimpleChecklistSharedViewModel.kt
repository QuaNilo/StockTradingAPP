package com.example.TPSIMobileProjecto.ui.home.stockFragments.sharedViewModels

import androidx.lifecycle.MutableLiveData
import retrofit.TickerDetails
import androidx.lifecycle.ViewModel

class SimpleChecklistSharedViewModel : ViewModel() {
    val addedItemsSimple = MutableLiveData<List<TickerDetails>>()
    val addedItemsChecklist = MutableLiveData<List<TickerDetails>>()


    fun setAddedItemsToSimple(items: MutableList<TickerDetails>) {
        addedItemsSimple.value = items
    }

    fun setAddedItemsToChecklist(items: MutableList<TickerDetails>) {
        addedItemsChecklist.value = items
    }
}