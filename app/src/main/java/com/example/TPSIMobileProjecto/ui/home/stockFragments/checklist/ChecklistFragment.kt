package com.example.TPSIMobileProjecto.ui.home.stockFragments.checklist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.TPSIMobileProjecto.R
import com.example.TPSIMobileProjecto.ui.home.stockFragments.simpleCard.SimpleCardFragment
import retrofit.TickerSummary

class ChecklistFragment(watchList : MutableList<TickerSummary>) : Fragment(), ChecklistRecyclerAdapter.ChecklistItemClickListener{
    val watchList = watchList
    private lateinit var itemAdapter : ChecklistRecyclerAdapter
    private lateinit var viewModel: ChecklistViewModel
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
        val button: Button = view.findViewById(R.id.btnSimple)

        button.text = "HOME"
        button.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.display_fragment, SimpleCardFragment(watchList, true))
                .addToBackStack(null)
                .commit()
        }


        viewModel = ViewModelProvider(this).get(ChecklistViewModel::class.java)
        progressBar = view.findViewById(R.id.progressBar)

        showProgressBar()
        viewModel.symbolSummaryList.observe(viewLifecycleOwner) { symbolSummaryList ->
            symbolsSummaryList.clear()
            symbolsSummaryList.addAll(symbolSummaryList)

            itemAdapter = ChecklistRecyclerAdapter(requireContext(), symbolsSummaryList, watchList) // Initialize the adapter
            val recyclerView: RecyclerView = view.findViewById(R.id.recycleView)
            itemAdapter.setChecklistItemClickListener(this) // Set the item click listener
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = itemAdapter
            hideProgressBar()


            view.findViewById<EditText>(R.id.etSearch).addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    Log.e("tag", "Before text changed")
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    Log.e("tag", "After text changed")
                }

                override fun afterTextChanged(p0: Editable?) {
                    filter(p0.toString())
                }
            })

        }


    }

    fun filter(text: String) {
        if (text.isNotEmpty()) {
            val filteredTickers = symbolsSummaryList.filter { it.symbol.first().uppercaseChar() == text.first().uppercaseChar() }
            itemAdapter.filter(filteredTickers)
        } else {
            itemAdapter.filter(symbolsSummaryList)
        }
    }


    override fun onStart() {
        super.onStart()
    }
    override fun onStop() {
        super.onStop()
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

