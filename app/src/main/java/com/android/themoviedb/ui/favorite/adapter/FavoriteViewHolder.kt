package com.android.themoviedb.ui.favorite.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.android.themoviedb.R
import com.android.themoviedb.helper.loadFromUrlWithPlaceholder
import com.android.themoviedb.model.MovieDetailAdapter
import kotlinx.android.synthetic.main.layout_item_movie.view.*

class FavoriteViewHolder(view: View, private val listener: SetItemListener) : RecyclerView.ViewHolder(view) {

    fun bind(items: MovieDetailAdapter) {
        with(itemView) {
            val imageUrl = "https://image.tmdb.org/t/p/w500" + items.posterPath
            ivThumbnailMovie.loadFromUrlWithPlaceholder(
                imageUrl,
                R.drawable.ic_thumbnails,
                R.drawable.ic_thumbnails
            )
            tvTitle.text = items.title
            tvReleaseDate.text = items.releaseDate
            tvDescription.text = items.overview
            ivRemove.visibility = View.VISIBLE
            ivRemove.setOnClickListener { listener.onClickRemove(items) }
        }
    }

    interface SetItemListener{

        fun onClickRemove(items: MovieDetailAdapter)
    }
}