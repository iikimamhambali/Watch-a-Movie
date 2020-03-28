package com.android.themoviedb.ui.favorite.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.android.themoviedb.R
import com.android.themoviedb.helper.loadFromUrlWithPlaceholder
import com.android.themoviedb.model.MovieDetailResult
import kotlinx.android.synthetic.main.layout_item_home_page.view.*

class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(items: MovieDetailResult) {
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
        }
    }
}