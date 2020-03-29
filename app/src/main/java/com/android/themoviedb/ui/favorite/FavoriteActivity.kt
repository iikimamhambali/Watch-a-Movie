package com.android.themoviedb.ui.favorite

import android.os.Bundle
import android.util.Log.d
import android.view.View
import androidx.lifecycle.Observer
import com.android.themoviedb.R
import com.android.themoviedb.base.BaseActivity
import com.android.themoviedb.base.BaseRecyclerView
import com.android.themoviedb.model.MovieDetailAdapter
import com.android.themoviedb.ui.favorite.adapter.FavoriteAdapter
import com.android.themoviedb.ui.favorite.adapter.FavoriteViewHolder
import com.android.themoviedb.viewmodel.DaoViewModel
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.android.synthetic.main.layout_toolbar_default.*
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteActivity : BaseActivity(), FavoriteViewHolder.SetItemListener {

    private val viewModelDao by viewModel<DaoViewModel>()

    private val resultList = mutableListOf<MovieDetailAdapter>()
    private val adapterFavorite by lazy { FavoriteAdapter(resultList, this) }

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

    private fun addData(data: List<MovieDetailAdapter>) {
        resultList.clear()
        resultList.addAll(data)
        adapterFavorite.notifyDataSetChanged()
    }

    override fun onClickRemove(items: MovieDetailAdapter) {
        d("LOGLOG", items.toString())
    }

    override fun observeData() {
        super.observeData()
        viewModelDao.getAllData().observe(this, Observer { addData(it) })
    }
}
