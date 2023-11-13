package com.example.TPSIMobileProjecto.ui.home.stockFragments.checklist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.TPSIMobileProjecto.R
import com.example.TPSIMobileProjecto.ui.home.stockFragments.simpleCard.SimpleCardViewModel
import com.example.TPSIMobileProjecto.ui.home.stockFragments.simpleCard.SimpleRecyclerAdapter
import retrofit.TickerDetails
import retrofit.TickerSummary

class ChecklistFragment : Fragment(), ChecklistRecyclerAdapter.OnItemClickListener {
    private lateinit var clickedItems: List<TickerDetails>
    private lateinit var adapter: ChecklistRecyclerAdapter
    companion object {
        fun newInstance() = ChecklistFragment()
    }

    private lateinit var viewModel: ChecklistViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_checklist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lateinit var clickedItems : List<TickerSummary>
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ChecklistViewModel::class.java)

        // Observe the LiveData and update the UI when data is available
        viewModel.symbolDetailsList.observe(viewLifecycleOwner) { symbolDetailsList ->
            val itemAdapter = SimpleRecyclerAdapter(symbolDetailsList) // Initialize the adapter
            val recyclerView: RecyclerView = view.findViewById(R.id.recycleView)
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = itemAdapter
        }
        val addButton : Button = view.findViewById(R.id.btnAdd)
        addButton.setOnClickListener {

        }
    }

    override fun onItemClick(position: Int) {
        // Handle the click event for the card at the given position
        // You can store the clicked position in a list or perform any other action
        // Example: Add the clicked position to the clickedItems list
        if (::clickedItems.isInitialized) {
            clickedItems = clickedItems + viewModel.symbolDetailsList.value?.get(position)
        }
    }


    private fun getClickedItems(): List<TickerSummary> {
        // Return the list of clicked items
        return clickedItems
    }


}