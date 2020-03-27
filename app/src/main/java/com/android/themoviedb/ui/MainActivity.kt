package com.android.themoviedb.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.android.themoviedb.R
import com.android.themoviedb.base.BaseActivity
import com.android.themoviedb.base.BaseRecyclerView
import com.android.themoviedb.model.MovieList
import com.android.themoviedb.model.MovieRequest
import com.android.themoviedb.ui.HomePage.HomePageViewModel
import com.android.themoviedb.ui.HomePage.adapter.HomePageAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_bottom_sheet_category.view.*
import kotlinx.android.synthetic.main.layout_toolbar_default.*
import org.jetbrains.anko.toast
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val viewModel by viewModel<HomePageViewModel>()

    private var resultItems = mutableListOf<MovieList>()
    private val adapterMovie by lazy { HomePageAdapter(resultItems) }

    companion object {
        const val LANGUAGE = "en-US"
    }

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
        setOnClickCategory()
    }

    private fun setOnClickToolbar() {
        ivFavorite.setOnClickListener {
        }
    }

    private fun setOnClickCategory() {
        btnCategory.setOnClickListener {
            val dialog = BottomSheetDialog(this)
            val dialogView: View = layoutInflater.inflate(
                R.layout.layout_bottom_sheet_category,
                null
            )

            with(dialogView) {
                this.tvPopular.setOnClickListener {
                    loadingDataPopular(1)
                    dialog.dismiss()
                }
                this.tvUpComing.setOnClickListener {
                    loadingDataUpComing(1)
                    dialog.dismiss()
                }
                this.tvTopRated.setOnClickListener {
                    loadingDataTopRated(1)
                    dialog.dismiss()
                }
                this.tvNowPlaying.setOnClickListener {
                    loadingDataNowPlaying(1)
                    dialog.dismiss()
                }
            }
            dialog.apply {
                setContentView(dialogView)
                show()
            }
        }
    }

    private fun addData(data: List<MovieList>) {
        resultItems.clear()
        resultItems.addAll(data)
        adapterMovie.notifyDataSetChanged()
    }

    private fun request(page: Int, region: String = ""): MovieRequest =
        MovieRequest(getString(R.string.api_key), LANGUAGE, page, region)

    private fun loadingDataNowPlaying(page: Int, region: String = "") {
        viewModel.getNowPlayingMovie(request(page, region))
    }

    private fun loadingDataPopular(page: Int, region: String = "") {
        viewModel.getPopularMovie(request(page, region))
    }

    private fun loadingDataUpComing(page: Int, region: String = "") {
        viewModel.getUpComingMovie(request(page, region))
    }

    private fun loadingDataTopRated(page: Int, region: String = "") {
        viewModel.getTopRatedMovie(request(page, region))
    }

    override fun loadingData(isFromSwipe: Boolean) {
        super.loadingData(isFromSwipe)
        loadingDataNowPlaying(1)
    }

    override fun observeData() {
        super.observeData()
        viewModel.listNowPlayingMovie.observe(this, Observer {
            parseObserveData(it, resultSuccess = { result, pagination ->
                addData(result.results)
            })
        })

        viewModel.listPopularMovie.observe(this, Observer {
            parseObserveData(it, resultSuccess = { result, pagination ->
                addData(result.results)
            })
        })

        viewModel.listUpComingMovie.observe(this, Observer {
            parseObserveData(it, resultSuccess = { result, pagination ->
                addData(result.results)
            })
        })

        viewModel.listTopRatedMovie.observe(this, Observer {
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
