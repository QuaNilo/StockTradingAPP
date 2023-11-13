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

    companion object {
        fun newInstance() = SimpleCardFragment()
    }

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
        viewModel = ViewModelProvider(this).get(SimpleCardViewModel::class.java)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SimpleChecklistSharedViewModel::class.java) // Initialize the sharedViewModel


        sharedViewModel.addedItems.observe(viewLifecycleOwner) { addedItems ->
            // Do something with the watchlist in this fragment
            Log.e("Mytag : ", "Received watchlist in OtherFragment: $addedItems")
            val itemAdapter = SimpleRecyclerAdapter(addedItems) // Initialize the adapter
            val recyclerView: RecyclerView = view.findViewById(R.id.recycleView)
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = itemAdapter
        }
        // Observe the LiveData and update the UI when data is available
//        viewModel.symbolDetailsList.observe(viewLifecycleOwner) { symbolDetailsList ->
//            val itemAdapter = SimpleRecyclerAdapter(symbolDetailsList) // Initialize the adapter
//            val recyclerView: RecyclerView = view.findViewById(R.id.recycleView)
//            recyclerView.layoutManager = LinearLayoutManager(context)
//            recyclerView.adapter = itemAdapter
//        }
    }

}