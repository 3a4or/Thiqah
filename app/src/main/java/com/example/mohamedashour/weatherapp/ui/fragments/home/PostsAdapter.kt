package com.example.mohamedashour.weatherapp.ui.fragments.home

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.example.mohamedashour.weatherapp.R
import com.example.mohamedashour.weatherapp.data.models.Posts
import com.example.mohamedashour.weatherapp.utils.OnRecyclerClick
import kotlinx.android.synthetic.main.item_post.view.*

class PostsAdapter(val context: Context, val  list: ArrayList<Posts>, recyclerClick: OnRecyclerClick) : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    var recyclerClick: OnRecyclerClick = recyclerClick

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(p0.context).inflate(R.layout.item_post, p0, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.title.text = list[p1].title
        p0.container.setOnClickListener { v -> recyclerClick.onItemClick(v, p0.adapterPosition, "container") }
        p0.add.setOnClickListener { v -> recyclerClick.onItemClick(v, p0.adapterPosition, "add") }
        p0.edit.setOnClickListener { v -> recyclerClick.onItemClick(v, p0.adapterPosition, "edit") }
        p0.delete.setOnClickListener { v -> recyclerClick.onItemClick(v, p0.adapterPosition, "delete") }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val  title: TextView = itemView.tv_title
        val  add: Button = itemView.btn_add
        val  edit: Button = itemView.btn_edit
        val  delete: Button = itemView.btn_delete
        val  container: LinearLayout = itemView.ll_container
    }

}