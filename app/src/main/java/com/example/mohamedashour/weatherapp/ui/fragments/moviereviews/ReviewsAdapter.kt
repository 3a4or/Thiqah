package com.example.mohamedashour.weatherapp.ui.fragments.moviereviews

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.mohamedashour.weatherapp.R
import com.example.mohamedashour.weatherapp.data.models.MovieReviews
import kotlinx.android.synthetic.main.item_review.view.*

class ReviewsAdapter(val list: List<MovieReviews.Result>) : RecyclerView.Adapter<ReviewsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_review, p0, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.name.text = list[p1].author
        p0.comment.text = list[p1].content
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.name
        val comment: TextView = itemView.comment
    }
}