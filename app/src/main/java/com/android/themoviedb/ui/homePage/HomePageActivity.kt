package com.android.themoviedb.ui.homePage

import android.os.Bundle
import android.view.View
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import com.android.themoviedb.R
import com.android.themoviedb.base.BaseActivity
import com.android.themoviedb.base.BaseRecyclerView
import com.android.themoviedb.helper.getColorCompat
import com.android.themoviedb.model.MovieList
import com.android.themoviedb.model.MovieRequest
import com.android.themoviedb.ui.detail.DetailMovieActivity
import com.android.themoviedb.ui.detail.DetailMovieActivity.Companion.MOVIE_ID
import com.android.themoviedb.ui.detail.DetailMovieActivity.Companion.TITLE_MOVIE
import com.android.themoviedb.ui.favorite.FavoriteActivity
import com.android.themoviedb.ui.homePage.adapter.HomePageAdapter
import com.android.themoviedb.ui.homePage.adapter.HomePageViewHolder
import com.android.themoviedb.viewmodel.MovieViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_bottom_sheet_category.view.*
import kotlinx.android.synthetic.main.layout_toolbar_default.*
import org.jetbrains.anko.startActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HomePageActivity : BaseActivity(), HomePageViewHolder.SetItemListener {

    private val currentPage = 0
    private val totalPage = 0
    private var isLoading = false

    private val viewModel by viewModel<MovieViewModel>()

    private var resultItems = mutableListOf<MovieList>()
    private val adapterMovie by lazy { HomePageAdapter(resultItems, this) }

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

    private fun setScrollListener() {
        nestedHome.setOnScrollChangeListener(
            NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, oldScrollY ->
                if (v.getChildAt(v.childCount - 1) != null && currentPage < totalPage && !isLoading) {
                    if ((scrollY >= (v.getChildAt(v.childCount - 1).measuredHeight - v.measuredHeight)) &&
                        scrollY > oldScrollY
                    ) {
//                        loadingData(currentPage)
                    }
                }
            })
    }

    private fun setupListener() {
        setOnClickToolbar()
        setOnClickCategory()
    }

    private fun setOnClickToolbar() {
        ivFavorite.setOnClickListener {
            startActivity<FavoriteActivity>()
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
                this.ivClose.setOnClickListener { dialog.dismiss() }
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

    override fun onClick(items: MovieList) {
        startActivity<DetailMovieActivity>(MOVIE_ID to items.id, TITLE_MOVIE to items.title)
    }

    private fun request(page: Int, region: String = ""): MovieRequest =
        MovieRequest(
            getString(R.string.api_key),
            LANGUAGE, page, region
        )

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
//        isLoading = true
//        when {
//            isLoading -> {
                progress_horizontal.visibility = View.VISIBLE
//            }
//            else -> swipeHome.isRefreshing = true
//        }
    }

    override fun stopLoading() {
//        isLoading = false
//        when {
//            isLoading -> {
                progress_horizontal.visibility = View.GONE
//            }
//            else -> swipeHome.isRefreshing = false
//        }
//        swipeHome.isRefreshing = false
    }
}
