package com.example.TPSIMobileProjecto.ui.home.stockFragments.checklist

import android.health.connect.datatypes.units.Length
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.TPSIMobileProjecto.R
import retrofit.TickerDetails
import retrofit.TickerSummary


class ChecklistRecyclerAdapter(private var stockList: List<TickerDetails>) : RecyclerView.Adapter<ChecklistRecyclerAdapter.MyViewHolder>() {
    // This method creates a new ViewHolder object for each item in the RecyclerView

    private var clickListener: ChecklistItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // Inflate the layout for each item and return a new ViewHolder object
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_checklist_card, parent, false)
        return MyViewHolder(itemView)
    }


    // This method returns the total
    // number of items in the data set
    override fun getItemCount(): Int {
        return stockList.size
    }

    // This method binds the data to the ViewHolder object
    // for each item in the RecyclerView
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val ItemsViewModel = stockList[position]
//        holder.imageView.setImageResource(R.drawable.ic_home_black_24dp)
        holder.price.text = ItemsViewModel.details.current_price.toString()
        holder.symbol.text = ItemsViewModel.symbol
        holder.percentage.text = ItemsViewModel.details.change_percent.toString()

        holder.addButton.setOnClickListener {
            Log.e("Mytag :", "Clicked Button with item ${ItemsViewModel.toString()}")
            clickListener?.onAddButtonClick(ItemsViewModel)
        }
    }

    // This class defines the ViewHolder object for each item in the RecyclerView
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val price: TextView = itemView.findViewById(R.id.tvPriceChecklist)
        val symbol: TextView = itemView.findViewById(R.id.tvSymbolChecklist)
        val percentage : TextView = itemView.findViewById(R.id.tvPercentageChecklist)
        val addButton : Button = itemView.findViewById(R.id.btnAddChecklist)

    }
    fun setChecklistItemClickListener(listener: ChecklistItemClickListener) {
        clickListener = listener
    }
    interface ChecklistItemClickListener {
        fun onAddButtonClick(tickerDetails: TickerDetails)
    }
}

