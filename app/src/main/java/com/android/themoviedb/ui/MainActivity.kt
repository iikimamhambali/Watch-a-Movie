package com.android.themoviedb.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.android.themoviedb.R
import com.android.themoviedb.base.BaseActivity
import com.android.themoviedb.base.BaseRecyclerView
import com.android.themoviedb.model.Images
import com.android.themoviedb.model.MovieList
import com.android.themoviedb.model.NowPlayingRequest
import com.android.themoviedb.ui.HomePage.HomePageViewModel
import com.android.themoviedb.ui.HomePage.adapter.HomePageAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_toolbar_default.*
import org.jetbrains.anko.toast
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private var resultItems = mutableListOf<MovieList>()

    private val adapterMovie by lazy { HomePageAdapter(resultItems) }

    companion object {
        const val LANGUAGE = "en-US"
    }

    private val viewModel by viewModel<HomePageViewModel>()

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        setupToolbar()
        setupRecyclerView()
    }

    override fun initEvent() {
        super.initEvent()
        setupListener()
    }

    private fun setupToolbar() {
        tvToolbarInfoTitle.text = getString(R.string.app_name)
        ivFavorite.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        with(rvHomePage) {
            adapterMovie?.let { initRecyclerView(it, BaseRecyclerView.LayoutManager.VERTICAL) }
        }
    }

    private fun setupListener() {
        setOnClickToolbar()
    }

    private fun setOnClickToolbar() {
        ivFavorite.setOnClickListener { toast("Favorite") }
    }

    private fun addData(data: List<MovieList>) {
        resultItems.clear()
        resultItems.addAll(data)
        adapterMovie.notifyDataSetChanged()
    }

    private fun loadingData(page: Int, region: String = "") {
        val requestData = NowPlayingRequest(getString(R.string.api_key), LANGUAGE, page, region)
        viewModel.getNowPlayingMovie(requestData)
    }

    override fun loadingData(isFromSwipe: Boolean) {
        super.loadingData(isFromSwipe)
        loadingData(1)
    }

    override fun observeData() {
        super.observeData()

        viewModel.listNowPlayingMovie.observe(this, Observer {
            parseObserveData(it, resultSuccess = { result, pagination ->
                addData(result.results)
            })
        })
    }

    override fun startLoading() {
        progress_horizontal.visibility = View.VISIBLE
    }

    override fun stopLoading() {
        progress_horizontal.visibility = View.GONE
    }
}
