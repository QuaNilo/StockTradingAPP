package com.example.TPSIMobileProjecto.ui.home.stockFragments.sharedViewModels

import androidx.lifecycle.MutableLiveData
import retrofit.TickerDetails
import androidx.lifecycle.ViewModel

class SimpleChecklistSharedViewModel : ViewModel() {
    val addedItems = MutableLiveData<List<TickerDetails>>()

    fun setAddedItems(items: MutableList<TickerDetails>) {
        addedItems.value = items
    }
}