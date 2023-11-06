package com.example.TPSIMobileProjecto.ui.News

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.TPSIMobileProjecto.R
import retrofit.News
import retrofit.TickerDetails


class NewsRecyclerAdapter(private var stockList: List<News>) : RecyclerView.Adapter<NewsRecyclerAdapter.MyViewHolder>() {

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
        holder.date.text = ItemsViewModel.date
        holder.description.text = ItemsViewModel.description
        holder.image_url.text = ItemsViewModel.image_url
        holder.title.text = ItemsViewModel.title
    }


    // This class defines the ViewHolder object for each item in the RecyclerView
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val date: TextView = itemView.findViewById(R.id.tvPrice)
        val description: TextView = itemView.findViewById(R.id.tvPrice)
        val image_url: TextView = itemView.findViewById(R.id.tvSymbol)
        val title: TextView = itemView.findViewById(R.id.tvSector)
    }
}