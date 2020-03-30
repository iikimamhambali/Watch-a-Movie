package com.android.themoviedb.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.themoviedb.R
import com.android.themoviedb.model.MovieReviewList

class ReviewAdapter(private val items: MutableList<MovieReviewList>) :
    RecyclerView.Adapter<ReviewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder =
        ReviewViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_item_review,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(items[position])
    }
}