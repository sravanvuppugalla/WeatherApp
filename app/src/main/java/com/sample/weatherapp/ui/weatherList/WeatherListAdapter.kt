package com.sample.weatherapp.ui.weatherList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sample.weatherapp.R
import com.sample.weatherapp.model.ListItem

class WeatherListAdapter(
    private val dataModelList: List<ListItem>?,
    private val listener: CustomClickListener
) : RecyclerView.Adapter<WeatherListAdapter.ViewHolder>() {
    private val context: Context? = null
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.weather_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val dataModel = dataModelList!![position]
        holder.bind(dataModel)
        holder.rlListItemRoot.setOnClickListener { v: View? ->
            listener.cardClicked(
                position
            )
        }
    }

    override fun getItemCount(): Int {
        return dataModelList!!.size
    }

    inner class ViewHolder(rootView: View) :
        RecyclerView.ViewHolder(rootView) {
        private val tvListItemType: TextView
        private val tvListItemTypeValue: TextView
        public val rlListItemRoot: RelativeLayout
        fun bind(listItem: ListItem) {
            tvListItemType.text = listItem.weather!![0]!!.main
            tvListItemTypeValue.text = "Temp: " + listItem.main!!.temp
        }

        init {
            tvListItemType = rootView.findViewById(R.id.tvListItemType)
            tvListItemTypeValue = rootView.findViewById(R.id.tvListItemTypeValue)
            rlListItemRoot = rootView.findViewById(R.id.rlListItemRoot)
        }
    }

}