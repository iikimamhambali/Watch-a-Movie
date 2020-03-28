package com.android.themoviedb.ui.favorite

import android.os.Bundle
import android.view.View
import com.android.themoviedb.R
import com.android.themoviedb.base.BaseActivity
import com.android.themoviedb.base.BaseRecyclerView
import com.android.themoviedb.model.MovieDetailResult
import com.android.themoviedb.ui.favorite.adapter.FavoriteAdapter
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.android.synthetic.main.layout_toolbar_default.*

class FavoriteActivity : BaseActivity() {

    private val resultList = mutableListOf<MovieDetailResult>()
    private val adapterFavorite by lazy { FavoriteAdapter(resultList) }

    override fun getLayoutResId(): Int = R.layout.activity_favorite

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        setupToolbar()
        setupRecyclerView()
    }

    override fun initEvent() {
        super.initEvent()
        setupOnClickToolbar()
    }

    private fun setupToolbar() {
        ivToolbarBack.visibility = View.VISIBLE
        tvToolbarInfoTitle.text = getString(R.string.label_favorite)
    }

    private fun setupRecyclerView() {
        with(rvFavorite) {
            initRecyclerView(adapterFavorite, BaseRecyclerView.LayoutManager.VERTICAL)
        }
    }

    private fun setupOnClickToolbar() {
        ivToolbarBack.setOnClickListener { finish() }
    }
}
