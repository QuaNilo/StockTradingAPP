package com.example.TPSIMobileProjecto.ui.home.stockFragments.simpleCard

import android.content.Context
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
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit.TickerSummary

class SimpleCardFragment : Fragment() {

    private var watchList: MutableList<TickerSummary> = mutableListOf()
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
        retrieveData()
    }

    override fun onPause() {
        Log.e("Lifecycle", "SimpleFragment onPause()")

        super.onPause()
        sharedViewModel.setAddedItemsToChecklist(watchList)
    }

    override fun onStop() {
        super.onStop()
        saveData()
    }

    override fun onStart() {
        super.onStart()
        sharedViewModel.addedItemsSimple.observe(viewLifecycleOwner) { addedItems ->
            // Do something with the watchlist in this fragment
            Log.e("Mytag : ", "Received watchlist in OtherFragment: $addedItems")
            watchList.clear()
            watchList.addAll(addedItems)
        }
        val itemAdapter = SimpleRecyclerAdapter(requireContext(), watchList) // Initialize the adapter
        val recyclerView: RecyclerView = requireView().findViewById(R.id.recycleView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = itemAdapter
    }
    override fun onDestroyView() {
        super.onDestroyView()
    }
    private fun saveData() {
        val sharedPreferences = requireContext().getSharedPreferences("Watchlist", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val gson = Gson()
        val json = gson.toJson(watchList)

        editor.putString("tickerList", json)
        editor.apply()
    }

    // Retrieve the data using SharedPreferences and Gson
    private fun retrieveData() {
        val sharedPreferences = requireContext().getSharedPreferences("Watchlist", Context.MODE_PRIVATE)
        val json = sharedPreferences.getString("tickerList", "")

        val gson = Gson()
        val type = object : TypeToken<List<TickerSummary>>() {}.type

        val savedList = gson.fromJson<List<TickerSummary>>(json, type) ?: emptyList()
        watchList.clear()
        watchList.addAll(savedList)
    }
}
