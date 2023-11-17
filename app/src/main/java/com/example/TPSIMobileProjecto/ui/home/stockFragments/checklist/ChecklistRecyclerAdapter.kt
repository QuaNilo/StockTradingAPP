package com.example.TPSIMobileProjecto.ui.home.stockFragments.checklist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.TPSIMobileProjecto.R
import com.squareup.picasso.Picasso
import retrofit.TickerSummary


class ChecklistRecyclerAdapter(private val context: Context, private var stockList: List<TickerSummary>, private var addedItems : List<TickerSummary>) : RecyclerView.Adapter<ChecklistRecyclerAdapter.MyViewHolder>() {
    // This method creates a new ViewHolder object for each item in the RecyclerView
    private var filteredList: List<TickerSummary> = stockList
    private var clickListener: ChecklistItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // Inflate the layout for each item and return a new ViewHolder object
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_checklist_card, parent, false)
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
        holder.price.text = ItemsViewModel.current_price.toString()
        holder.symbol.text = ItemsViewModel.symbol
        holder.percentage.text = ItemsViewModel.change_percent.toString()

        ItemsViewModel.logo_url.let {
            Picasso.get().load(it).into(holder.imageView)
        }

        val color = if (ItemsViewModel.change_percent < 0) R.color.red else R.color.green
        holder.percentage.setTextColor(ContextCompat.getColor(context, color))

        val isInWatchList = addedItems.any { it.symbol == ItemsViewModel.symbol }
        holder.addButton.text = "Remove"
        if (isInWatchList) {
            holder.addButton.text = "Remove"
            holder.addButton.setBackgroundColor(ContextCompat.getColor(context, R.color.red))
        }else{
                holder.addButton.text = "Add"
                holder.addButton.setBackgroundColor(ContextCompat.getColor(context, R.color.purple_500))
        }
        holder.addButton.setOnClickListener {
            if (holder.addButton.text == "Remove"){
                clickListener?.onRemoveButtonClick(ItemsViewModel)
                holder.addButton.text = "Add"
                holder.addButton.setBackgroundColor(ContextCompat.getColor(context, R.color.purple_500))
            }
            else{
                clickListener?.onAddButtonClick(ItemsViewModel)
                holder.addButton.text = "Remove"
                holder.addButton.setBackgroundColor(ContextCompat.getColor(context, R.color.red))

            }
        }

    }

    // This class defines the ViewHolder object for each item in the RecyclerView
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val price: TextView = itemView.findViewById(R.id.tvPriceChecklist)
        val symbol: TextView = itemView.findViewById(R.id.tvSymbolChecklist)
        val percentage : TextView = itemView.findViewById(R.id.tvPercentageChecklist)
        val addButton : Button = itemView.findViewById(R.id.btnAddChecklist)
        val imageView : ImageView = itemView.findViewById(R.id.imgViewLogoChecklist)
    }
    fun setChecklistItemClickListener(listener: ChecklistItemClickListener) {
        clickListener = listener
    }
    interface ChecklistItemClickListener {
        fun onAddButtonClick(tickerSummary: TickerSummary)
        fun onRemoveButtonClick(tickerSummary: TickerSummary)

    }


    fun filter(filteredList : List<TickerSummary>) {
        this.stockList = filteredList
        notifyDataSetChanged()
    }
}

