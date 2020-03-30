package com.android.themoviedb.ui.homePage.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.android.themoviedb.R
import com.android.themoviedb.helper.loadFromUrlWithPlaceholder
import com.android.themoviedb.model.MovieList
import kotlinx.android.synthetic.main.layout_item_movie.view.*

class HomePageViewHolder(
    view: View, private val listener: SetItemListener
) : RecyclerView.ViewHolder(view) {

    fun bind(items: MovieList) {
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
            cvMovie.setOnClickListener { listener.onClick(items) }
        }
    }

    interface SetItemListener {

        fun onClick(items: MovieList)
    }
}