package com.example.TPSIMobileProjecto.ui.home.stockFragments.simpleCard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.TPSIMobileProjecto.R
import retrofit.TickerDetails


class SimpleRecyclerAdapter(private var stockList: List<TickerDetails>) : RecyclerView.Adapter<SimpleRecyclerAdapter.MyViewHolder>() {

    // This method creates a new ViewHolder object for each item in the RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // Inflate the layout for each item and return a new ViewHolder object
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_simple_card, parent, false)
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
        holder.imageView.setImageResource(R.drawable.ic_home_black_24dp)
        holder.price.text = ItemsViewModel.details.current_price.toString()
        holder.symbol.text = ItemsViewModel.symbol
        holder.sector.text = ItemsViewModel.sector
    }


    // This class defines the ViewHolder object for each item in the RecyclerView
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val price: TextView = itemView.findViewById(R.id.tvPrice)
        val symbol: TextView = itemView.findViewById(R.id.tvSymbol)
        val sector: TextView = itemView.findViewById(R.id.tvSector)
    }
}