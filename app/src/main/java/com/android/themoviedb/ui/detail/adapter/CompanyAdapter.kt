package com.android.themoviedb.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.themoviedb.R
import com.android.themoviedb.model.ProductionCompany

class CompanyAdapter(private val items: MutableList<ProductionCompany>) :
    RecyclerView.Adapter<CompanyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder =
        CompanyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_item_company,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        holder.bind(items[position])
    }
}