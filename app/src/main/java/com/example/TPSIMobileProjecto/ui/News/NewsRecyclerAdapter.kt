package com.example.TPSIMobileProjecto.ui.News

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.TPSIMobileProjecto.R
import com.squareup.picasso.Picasso
import retrofit.News
//import retrofit.TickerDetails


class NewsRecyclerAdapter(private var stockList: List<News>) : RecyclerView.Adapter<NewsRecyclerAdapter.MyViewHolder>() {

    // This method creates a new ViewHolder object for each item in the RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // Inflate the layout for each item and return a new ViewHolder object
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_news_card, parent, false)
        return MyViewHolder(itemView)
    }

    // This method returns the total
    // number of items in the data set
    override fun getItemCount(): Int {
        return stockList.size
    }

    // This method binds the data to the ViewHolder object
    // for each item in the RecyclerView
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val newsItem = stockList[position]
        holder.date.text = newsItem.date
        holder.description.text = newsItem.description
        holder.title.text = newsItem.title

        holder.description.maxLines = 3

        // Button to change the lines displayed
        holder.description.post {
            val lineCount = holder.description.layout.lineCount
            Log.e("Lines:", lineCount.toString())
            if (lineCount < 3) {
                holder.readMoreButton.visibility = View.GONE
            } else {
                holder.readMoreButton.visibility = View.VISIBLE
                holder.description.maxLines = 3
        
                // Button to change the lines displayed
                holder.readMoreButton.setOnClickListener {
                    if (holder.description.maxLines == 3) {
                        holder.description.maxLines = Int.MAX_VALUE
                        holder.readMoreButton.text = "Read Less"
                    } else {
                        holder.description.maxLines = 3
                        holder.readMoreButton.text = "Read More"
                    }
                }
            }
        }
        // Load and display image using Picasso
        newsItem.image_url.let {
            Picasso.get().load(it).into(holder.imageView)
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val date: TextView = itemView.findViewById(R.id.tvDate)
        val description: TextView = itemView.findViewById(R.id.tvDesc)
        val title: TextView = itemView.findViewById(R.id.tvTitle)
        val imageView: ImageView = itemView.findViewById(R.id.ivImage)
        val readMoreButton: Button = itemView.findViewById(R.id.btnReadMore)
    }


}


