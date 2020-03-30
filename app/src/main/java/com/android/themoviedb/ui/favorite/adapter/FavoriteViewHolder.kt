package com.android.themoviedb.ui.favorite.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.android.themoviedb.R
import com.android.themoviedb.base.BaseRecyclerView
import com.android.themoviedb.helper.loadFromUrlWithPlaceholder
import com.android.themoviedb.model.MovieDetailAdapter
import kotlinx.android.synthetic.main.layout_item_favorite.view.*

class FavoriteViewHolder(view: View, private val listener: SetItemListener) :
    RecyclerView.ViewHolder(view) {

    fun bind(items: MovieDetailAdapter) {
        with(itemView) {
            ivBackdrop.loadFromUrlWithPlaceholder(
                items.backdropPath,
                R.drawable.ic_thumbnails,
                R.drawable.ic_thumbnails
            )
            tvTitle.text = items.title
            tvDescription.text = items.overview
            ivRemove.visibility = View.VISIBLE
            ivRemove.setOnClickListener { listener.onClickRemove(items) }

            rvGenre.visibility = View.VISIBLE
            rvGenre.initRecyclerView(
                GenreAdapter(items.genres),
                BaseRecyclerView.LayoutManager.HORIZONTAL
            )
            rvGenre.initItemDecoration(2)
        }
    }

    interface SetItemListener {

        fun onClickRemove(items: MovieDetailAdapter)
    }
}