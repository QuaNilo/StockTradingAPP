package com.example.TPSIMobileProjecto.ui.home.stockFragments.simpleCard
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.TPSIMobileProjecto.R
import com.example.TPSIMobileProjecto.ui.home.stockFragments.checklist.ChecklistRecyclerAdapter
import com.squareup.picasso.Picasso
import retrofit.TickerSummary


class SimpleRecyclerAdapter(private val context: Context, private var stockList: List<TickerSummary>) : RecyclerView.Adapter<SimpleRecyclerAdapter.MyViewHolder>() {
    private var clickListener: DetailedViewOnClick? = null
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
        holder.price.text = ItemsViewModel.current_price.toString()
        holder.symbol.text = ItemsViewModel.symbol
        holder.percentage.text = ItemsViewModel.change_percent.toString()

        val color = if (ItemsViewModel.change_percent < 0) {
            R.color.red
        } else {
            R.color.green
        }

        holder.percentage.setTextColor(ContextCompat.getColor(context, color))
        holder.percgraph.setTextColor(ContextCompat.getColor(context, color))

        // Rotate percgraph based on the sign of change_percent
        val rotationAngle = if (ItemsViewModel.change_percent < 0) {
            180f // Rotate 180 degrees for negative change
        } else {
            0f // No rotation for positive change
        }
        holder.percgraph.rotation = rotationAngle


        // Load and display image using Picasso
        ItemsViewModel.logo_url.let {
            Picasso.get().load(it).into(holder.imageView)
        }

        holder.itemView.setOnClickListener {
            Log.e("Tag", "Clicked item $ItemsViewModel")
            clickListener?.onDetailedViewClick(ItemsViewModel)
        }


    }


    // This class defines the ViewHolder object for each item in the RecyclerView
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val price: TextView = itemView.findViewById(R.id.tvPrice)
        val symbol: TextView = itemView.findViewById(R.id.tvSymbol)
        val percentage : TextView = itemView.findViewById(R.id.tvPercentage)
        val percgraph   : TextView = itemView.findViewById(R.id.tvPercentageGraph)
    }
    fun setDetailedItemClickListener(listener: SimpleRecyclerAdapter.DetailedViewOnClick) {
        clickListener = listener
    }
    interface DetailedViewOnClick {
        fun onDetailedViewClick(tickerSummary: TickerSummary)

    }
}