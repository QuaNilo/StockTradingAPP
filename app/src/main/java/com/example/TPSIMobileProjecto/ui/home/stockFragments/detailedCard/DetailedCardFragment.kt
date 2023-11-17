package com.example.TPSIMobileProjecto.ui.home.stockFragments.detailedCard

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.TPSIMobileProjecto.R
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import retrofit.TickerSummary

class DetailedCardFragment(tickerSummary: TickerSummary) : Fragment() {

    private val data = tickerSummary
    private lateinit var viewModel: DetailedCardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detailed_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.e("Lifecycle", "DetailedFragment onViewCreated()")
        Log.e("tag", data.toString())
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailedCardViewModel::class.java)
        // TODO: Use the ViewModel

        val graphlist : List<GraphData>


    }
}
