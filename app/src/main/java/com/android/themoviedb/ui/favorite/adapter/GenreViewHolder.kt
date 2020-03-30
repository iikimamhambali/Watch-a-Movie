package com.android.themoviedb.ui.favorite.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.android.themoviedb.model.Genre
import kotlinx.android.synthetic.main.layout_item_genre.view.*

class GenreViewHolder(view: View) :
    RecyclerView.ViewHolder(view) {

    fun bind(item: Genre) {
        with(itemView) {
            tvGenre.text = item.name
        }
    }
}