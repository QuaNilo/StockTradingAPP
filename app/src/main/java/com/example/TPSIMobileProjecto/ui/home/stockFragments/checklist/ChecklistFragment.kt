package com.example.TPSIMobileProjecto.ui.home.stockFragments.checklist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.TPSIMobileProjecto.R
import com.example.TPSIMobileProjecto.ui.home.stockFragments.checklist.ChecklistRecyclerAdapter
import com.example.TPSIMobileProjecto.ui.home.stockFragments.sharedViewModels.SimpleChecklistSharedViewModel
import retrofit.TickerSummary

class ChecklistFragment : Fragment(), ChecklistRecyclerAdapter.ChecklistItemClickListener{
    private lateinit var viewModel: ChecklistViewModel
    private lateinit var sharedViewModel: SimpleChecklistSharedViewModel
    private var watchList: MutableList<TickerSummary> = mutableListOf()
    val symbolsSummaryList = mutableListOf<TickerSummary>()
    lateinit var progressBar: ProgressBar // Import ProgressBar if not done already
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
        progressBar = view.findViewById(R.id.progressBar)
        showProgressBar()
        sharedViewModel.addedItemsChecklist.observe(viewLifecycleOwner) {addedItems ->
            watchList.clear()
            watchList.addAll(addedItems)
        }
        viewModel.symbolSummaryList.observe(viewLifecycleOwner) { symbolSummaryList ->
            symbolsSummaryList.clear()
            symbolsSummaryList.addAll(symbolSummaryList)
            val itemAdapter = ChecklistRecyclerAdapter(requireContext(), symbolsSummaryList, watchList) // Initialize the adapter
            val recyclerView: RecyclerView = view.findViewById(R.id.recycleView)
            itemAdapter.setChecklistItemClickListener(this) // Set the item click listener
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = itemAdapter
            hideProgressBar()
        }

    }

    override fun onStart() {
        super.onStart()
        sharedViewModel.addedItemsChecklist.observe(viewLifecycleOwner) {addedItems ->
            watchList.clear()
            watchList.addAll(addedItems)
        }
    }
    override fun onStop() {
        super.onStop()
        sharedViewModel.setAddedItemsToSimple(watchList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
    override fun onAddButtonClick(tickerSummary: TickerSummary) {
        watchList.add(tickerSummary)
    }

    override fun onRemoveButtonClick(tickerSummary: TickerSummary) {
        watchList.remove(tickerSummary)
    }


    fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }
}

