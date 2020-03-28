package com.android.themoviedb.ui.detail.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.android.themoviedb.model.MovieReviewList
import kotlinx.android.synthetic.main.layout_item_review.view.*

class ReviewViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(items: MovieReviewList) {
        with(itemView) {
            tvAuthor.text = items.author
            tvContent.text = items.content
        }
    }
}