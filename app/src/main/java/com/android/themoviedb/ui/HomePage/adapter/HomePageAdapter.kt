package com.android.themoviedb.ui.HomePage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.themoviedb.R
import com.android.themoviedb.model.Images
import com.android.themoviedb.model.MovieList

class HomePageAdapter(private val items: MutableList<MovieList>) :
    RecyclerView.Adapter<HomePageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePageViewHolder =
        HomePageViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_item_home_page,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: HomePageViewHolder, position: Int) {
        holder.bind(items = items[position])
    }

}