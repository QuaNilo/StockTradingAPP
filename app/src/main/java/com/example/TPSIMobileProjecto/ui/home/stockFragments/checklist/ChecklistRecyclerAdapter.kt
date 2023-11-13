package com.example.TPSIMobileProjecto.ui.home.stockFragments.checklist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.TPSIMobileProjecto.R
import retrofit.TickerDetails
import retrofit.TickerSummary


class ChecklistRecyclerAdapter(private var stockList: List<TickerDetails>) : RecyclerView.Adapter<ChecklistRecyclerAdapter.MyViewHolder>() {
    val checkedItems = mutableListOf<TickerDetails>()
    // This method creates a new ViewHolder object for each item in the RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // Inflate the layout for each item and return a new ViewHolder object
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_simple_card, parent, false)
        return MyViewHolder(itemView)
    }


    fun getClickedItems(): List<TickerDetails> {
        return checkedItems
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
        holder.imageView.setImageResource(R.drawable.ic_home_black_24dp)
        holder.price.text = ItemsViewModel.details.current_price.toString()
        holder.symbol.text = ItemsViewModel.symbol
        holder.percentage.text = ItemsViewModel.details.change_percent.toString()

        // Set click listener for the entire card
        holder.addButton.setOnClickListener {
            val clickedItem = stockList[position]
            if (!checkedItems.contains(clickedItem)) {
                checkedItems.add(clickedItem)
                holder.listener.onItemClick(position)
            }
        }
    }


    // This class defines the ViewHolder object for each item in the RecyclerView
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val price: TextView = itemView.findViewById(R.id.tvPrice)
        val symbol: TextView = itemView.findViewById(R.id.tvSymbol)
        val percentage : TextView = itemView.findViewById(R.id.tvPercentage)
        val addButton : Button = itemView.findViewById(R.id.btnAdd)
        lateinit var listener: OnItemClickListener
    }

    // Interface for item click
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}