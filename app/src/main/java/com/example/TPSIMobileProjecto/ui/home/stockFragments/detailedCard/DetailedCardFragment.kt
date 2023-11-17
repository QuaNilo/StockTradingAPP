package com.example.TPSIMobileProjecto.ui.home.stockFragments.detailedCard

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.ProgressBar
import com.example.TPSIMobileProjecto.R
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import com.squareup.picasso.Picasso
import retrofit.TickerDetails
import retrofit.TickerSummary

class DetailedCardFragment(tickerSummary: TickerSummary) : Fragment() {
    lateinit var progressBar: ProgressBar
    private val data = tickerSummary
    private var tickerDetailedList: MutableList<TickerDetails> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detailed_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.e("Lifecycle", "DetailedFragment onViewCreated()")
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this).get(DetailedCardViewModel::class.java)
        val graphlist : List<GraphData>
        progressBar = view.findViewById(R.id.progressBarDetailed)
        showProgressBar()
        viewModel.symbolDetailsList.observe(viewLifecycleOwner){ symbolDetails ->
            tickerDetailedList.clear()
            tickerDetailedList.addAll(symbolDetails)
            val ticker = tickerDetailedList.find { it.symbol == data.symbol }
            Log.e("tag", "Ticker detailed = ${ticker.toString()}")

            val price: TextView = view.findViewById(R.id.tvPriceDetailed)
            val symbol: TextView = view.findViewById(R.id.tvSymbolDetailed)
            val percentage : TextView = view.findViewById(R.id.tvPercentDetailed)
            val sector : TextView = view.findViewById(R.id.tvSectorDetailed)
            val imageView : ImageView = view.findViewById(R.id.imageviewDetailed)

            if (ticker != null) {
                sector.text = ticker.sector
                price.text = ticker.details?.current_price.toString()
                symbol.text = ticker.symbol
                percentage.text = ticker.details?.change_percent.toString()
                ticker.logo_url?.let {
                    Picasso.get().load(it).into(imageView)
                }
            }
            hideProgressBar()
        }






    }

    fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }
}
