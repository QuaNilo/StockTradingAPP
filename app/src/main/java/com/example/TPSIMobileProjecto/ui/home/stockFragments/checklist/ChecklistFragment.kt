package com.example.TPSIMobileProjecto.ui.home.stockFragments.checklist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.TPSIMobileProjecto.R
import com.example.TPSIMobileProjecto.ui.home.stockFragments.checklist.ChecklistRecyclerAdapter
import com.example.TPSIMobileProjecto.ui.home.stockFragments.sharedViewModels.SimpleChecklistSharedViewModel
import retrofit.TickerDetails

class ChecklistFragment : Fragment(), ChecklistRecyclerAdapter.ChecklistItemClickListener{
    private lateinit var viewModel: ChecklistViewModel
    private lateinit var sharedViewModel: SimpleChecklistSharedViewModel
    private var watchList: MutableList<TickerDetails> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_checklist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ChecklistViewModel::class.java)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SimpleChecklistSharedViewModel::class.java) // Initialize the sharedViewModel

        // Observe the LiveData and update the UI when data is available
        viewModel.symbolDetailsList.observe(viewLifecycleOwner) { symbolDetailsList ->
            val itemAdapter = ChecklistRecyclerAdapter(symbolDetailsList) // Initialize the adapter
            val recyclerView: RecyclerView = view.findViewById(R.id.recycleView)
            itemAdapter.setChecklistItemClickListener(this) // Set the item click listener
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = itemAdapter
        }
    }

    override fun onAddButtonClick(tickerDetails: TickerDetails) {
        Log.e("Mytag : ", "Current watch list = ${tickerDetails.toString()}")
        watchList.add(tickerDetails)

    }

    override fun onPause() {
        super.onPause()
        sharedViewModel.setAddedItems(watchList)
    }
}

