package com.example.TPSIMobileProjecto.ui.home.stockFragments.detailedCard

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import com.example.TPSIMobileProjecto.R
import com.example.TPSIMobileProjecto.ui.home.stockFragments.simpleCard.SimpleCardFragment
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import com.squareup.picasso.Picasso
import org.w3c.dom.Text
import retrofit.TickerDetails
import retrofit.TickerSummary

class DetailedCardFragment(chosenTicker : TickerSummary) : Fragment() {
    lateinit var progressBar: ProgressBar
    lateinit var noDataFoundtv: TextView
    private val chosenTicker = chosenTicker
    private var tickerDetailedList: MutableList<TickerDetails> = mutableListOf()
    lateinit var lineGraphView: GraphView
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

        noDataFoundtv = view.findViewById(R.id.tvDetailedNotFound)
        noDataFoundtv.visibility = View.GONE

        progressBar = view.findViewById(R.id.progressBarDetailed)
        showProgressBar()
        viewModel.symbolDetailsList.observe(viewLifecycleOwner){ symbolDetails ->
            tickerDetailedList.clear()
            tickerDetailedList.addAll(symbolDetails)
            val ticker = tickerDetailedList.find { it.symbol == chosenTicker.symbol }
            Log.e("tag", "Ticker detailed = ${ticker.toString()}")

            val price: TextView = view.findViewById(R.id.tvPriceDetailed)
            val symbol: TextView = view.findViewById(R.id.tvSymbolDetailed)
            val percentage : TextView = view.findViewById(R.id.tvPercentDetailed)
            val sector : TextView = view.findViewById(R.id.tvSectorDetailed)
            val imageView : ImageView = view.findViewById(R.id.imageviewDetailed)
            lineGraphView = view.findViewById(R.id.idGraphView)
            lineGraphView.visibility = View.GONE

            if (ticker != null) {
                populateGraph(ticker, lineGraphView)
                sector.text = ticker.sector
                price.text = ticker.details?.current_price.toString()
                symbol.text = ticker.symbol
                percentage.text = ticker.details?.change_percent.toString()
                ticker.logo_url?.let {
                    Picasso.get().load(it).into(imageView)
                }
                val color = if (ticker.details.change_percent < 0) R.color.red else R.color.green
                percentage.setTextColor(ContextCompat.getColor(requireContext(), color))

            }
            else{
                noDataFoundtv.visibility = View.VISIBLE
            }
            hideProgressBar()
        }

        val button: Button = view.findViewById(R.id.btnHome)
        button.text = "HOME"
        button.setOnClickListener {
        parentFragmentManager.beginTransaction()
            .replace(R.id.display_fragment, SimpleCardFragment(mutableListOf(), false))
            .addToBackStack(null)
            .commit()
        }


    }

    fun populateGraph(ticker: TickerDetails, graph: GraphView) {
        val graphList = mutableListOf<DataPoint>()

        for ((index, value) in ticker.chart_data.October_2022.withIndex()) {
            graphList.add(DataPoint(index.toDouble(), value))
        }

        val series: LineGraphSeries<DataPoint> = LineGraphSeries(graphList.toTypedArray())

        // Set manual bounds for X-axis to match the data range.
        graph.viewport.setXAxisBoundsManual(true)
        graph.viewport.setMinX(0.0)
        graph.viewport.setMaxX(graphList.size.toDouble() - 1)

        // Set other viewport settings
        graph.viewport.isScrollable = true
        graph.viewport.isScalable = true
        graph.viewport.setScalableY(true)
        graph.viewport.setScrollableY(true)

        // Set color for the series
        series.color = R.color.purple_200

        // Add the series to the graph
        graph.addSeries(series)
        graph.visibility = View.VISIBLE
    }
    fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }
}
