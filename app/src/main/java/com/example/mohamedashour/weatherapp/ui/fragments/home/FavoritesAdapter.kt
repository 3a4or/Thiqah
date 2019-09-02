package com.example.mohamedashour.weatherapp.ui.fragments.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.mohamedashour.weatherapp.R
import com.example.mohamedashour.weatherapp.data.db.entities.Movie
import com.example.mohamedashour.weatherapp.utils.OnRecyclerClick
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*

class FavoritesAdapter(val context: Context, val  list: List<Movie>, recyclerClick: OnRecyclerClick) : RecyclerView.Adapter<FavoritesAdapter.ViewHolder>(){

    var recyclerClick: OnRecyclerClick = recyclerClick

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(p0.context).inflate(R.layout.item_movie, p0, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        Picasso.get().load("http://image.tmdb.org/t/p/w185" + list[p1].image).into(p0.movieImage)
        p0.movieImage.setOnClickListener { v -> recyclerClick.onItemClick(v, p0.adapterPosition) }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val  movieImage: ImageView = itemView.image_icon
    }
}