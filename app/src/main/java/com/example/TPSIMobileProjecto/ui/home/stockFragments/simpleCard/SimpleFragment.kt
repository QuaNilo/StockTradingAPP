package com.example.TPSIMobileProjecto.ui.home.stockFragments.simpleCard

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
import com.example.TPSIMobileProjecto.ui.home.stockFragments.sharedViewModels.SimpleChecklistSharedViewModel
import com.example.TPSIMobileProjecto.ui.home.stockFragments.simpleCard.Adapter.SimpleRecyclerAdapter
import retrofit.TickerDetails

class SimpleCardFragment : Fragment() {

    private var watchList: MutableList<TickerDetails> = mutableListOf()
    private lateinit var viewModel: SimpleCardViewModel
    private lateinit var sharedViewModel: SimpleChecklistSharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_simple_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("Lifecycle", "SimpleFragment onViewCreated()")
        viewModel = ViewModelProvider(this).get(SimpleCardViewModel::class.java)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SimpleChecklistSharedViewModel::class.java) // Initialize the sharedViewModel

        viewModel.tickerDetailsList.observe(viewLifecycleOwner, { tickerDetailsList ->
            watchList.clear()
            watchList.addAll(tickerDetailsList)
        })

    }

    override fun onPause() {
        Log.e("Lifecycle", "SimpleFragment onPause()")

        super.onPause()
        sharedViewModel.setAddedItemsToChecklist(watchList)
    }

    override fun onStart() {
        super.onStart()
        sharedViewModel.addedItemsSimple.observe(viewLifecycleOwner) { addedItems ->
            // Do something with the watchlist in this fragment
            Log.e("Mytag : ", "Received watchlist in OtherFragment: $addedItems")
            watchList.clear()
            watchList.addAll(addedItems)
        }
        val itemAdapter = SimpleRecyclerAdapter(watchList) // Initialize the adapter
        val recyclerView: RecyclerView = requireView().findViewById(R.id.recycleView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = itemAdapter
    }
    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.setTickerDetailsList(watchList)
    }


}