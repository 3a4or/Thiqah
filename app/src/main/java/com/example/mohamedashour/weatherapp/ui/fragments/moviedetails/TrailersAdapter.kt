package com.example.mohamedashour.weatherapp.ui.fragments.moviedetails

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.mohamedashour.weatherapp.R
import com.example.mohamedashour.weatherapp.data.models.MovieDetails
import com.example.mohamedashour.weatherapp.utils.OnRecyclerClick
import kotlinx.android.synthetic.main.item_trailer.view.*

class TrailersAdapter(val context: Context, val  list: List<MovieDetails.Result>, recyclerClick: OnRecyclerClick) : RecyclerView.Adapter<TrailersAdapter.ViewHolder>(){

    var recyclerClick: OnRecyclerClick = recyclerClick

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_trailer, p0, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.trailerName.text = list[p1].name
        p0.container.setOnClickListener { v -> recyclerClick.onItemClick(v, p0.adapterPosition) }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val trailerName: TextView = itemView.trailer_text
        val container: LinearLayout = itemView.container
    }
}