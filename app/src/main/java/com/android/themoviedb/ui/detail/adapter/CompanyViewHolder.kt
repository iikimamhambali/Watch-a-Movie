package com.android.themoviedb.ui.detail.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.android.themoviedb.R
import com.android.themoviedb.helper.loadFromUrlWithPlaceholder
import com.android.themoviedb.model.ProductionCompany
import kotlinx.android.synthetic.main.layout_item_company.view.*

class CompanyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(items: ProductionCompany) {
        with(itemView) {
            val imageUrl = "https://image.tmdb.org/t/p/original" + items.logoPath
            ivAvaCompany.loadFromUrlWithPlaceholder(
                imageUrl,
                R.drawable.ic_thumbnails,
                R.drawable.ic_thumbnails
            )
        }
    }
}