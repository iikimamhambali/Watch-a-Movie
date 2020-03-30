package com.android.themoviedb.ui.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.themoviedb.R
import com.android.themoviedb.model.MovieDetailAdapter

class FavoriteAdapter(
    private val items: MutableList<MovieDetailAdapter>,
    private val listener: FavoriteViewHolder.SetItemListener
) :
    RecyclerView.Adapter<FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder =
        FavoriteViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_item_favorite,
                parent,
                false
            ), listener
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(items[position])
    }
}