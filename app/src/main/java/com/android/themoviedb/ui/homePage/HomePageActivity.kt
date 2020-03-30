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

    private var currentPage = 1
    private var totalPage = 0
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
        setContentVisibility(true)
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

    private fun setContentVisibility(visible: Boolean) {
        when (visible) {
            true -> {
                sectionEmptyState.visibility = View.GONE
                nestedHome.visibility = View.VISIBLE
            }
            else -> {
                nestedHome.visibility = View.GONE
                sectionEmptyState.visibility = View.VISIBLE
            }
        }
    }

    private fun setupListener() {
        setOnClickToolbar()
        setOnClickCategory()
        setScrollListener()
        setupSwipeRefresh()
    }

    private fun setupSwipeRefresh() {
        with(swipeRefreshHome) {
            setOnRefreshListener {
                resetData()
                loadingData()
            }
            setColorSchemeColors(
                context.getColorCompat(R.color.colorPrimary),
                context.getColorCompat(R.color.colorPrimary),
                context.getColorCompat(R.color.colorPrimary)
            )
            val typedValue = android.util.TypedValue()
            context.theme?.resolveAttribute(R.attr.actionBarSize, typedValue, true)
            setProgressViewOffset(
                false,
                0,
                context.resources.getDimensionPixelSize(typedValue.resourceId)
            )
        }
    }

    private fun setScrollListener() {
        nestedHome.setOnScrollChangeListener(
            NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, oldScrollY ->
                if (v.getChildAt(v.childCount - 1) != null && currentPage < totalPage && !isLoading) {
                    if ((scrollY >= (v.getChildAt(v.childCount - 1).measuredHeight - v.measuredHeight)) &&
                        scrollY > oldScrollY
                    ) {
                        loadingDataNowPlaying(currentPage)
                    }
                }
            })
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

    private fun addDataNowPlaying(data: List<MovieList>) {
        val positionStart = resultItems.size + 1
        val itemCount = data.size
        resultItems.addAll(data)
        adapterMovie.notifyItemRangeInserted(positionStart, itemCount)
    }

    private fun addData(data: List<MovieList>) {
        resultItems.clear()
        resultItems.addAll(data)
        adapterMovie.notifyDataSetChanged()
    }

    private fun resetData() {
        currentPage = 1
        resultItems.clear()
        adapterMovie.notifyDataSetChanged()
    }

    override fun onClick(items: MovieList) {
        startActivity<DetailMovieActivity>(MOVIE_ID to items.id, TITLE_MOVIE to items.title)
    }

    private fun request(page: Int = 0, region: String = ""): MovieRequest =
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
                addDataNowPlaying(result.results)
                result.page = currentPage
                currentPage++
                totalPage = result.totalPages
            })
        })

        viewModel.listPopularMovie.observe(this, Observer {
            parseObserveData(it, resultLoading = {}, resultSuccess = { result, pagination ->
                addData(result.results)
            })
        })

        viewModel.listUpComingMovie.observe(this, Observer {
            parseObserveData(it, resultLoading = {}, resultSuccess = { result, pagination ->
                addData(result.results)
            })
        })

        viewModel.listTopRatedMovie.observe(this, Observer {
            parseObserveData(it, resultLoading = {}, resultSuccess = { result, pagination ->
                addData(result.results)
            })
        })
    }

    override fun startLoading() {
        isLoading = true
        when {
            request().isFirstPage() -> {
                progress_horizontal.visibility = View.VISIBLE
            }
            else -> swipeRefreshHome.isRefreshing = true
        }
    }

    override fun stopLoading() {
        isLoading = false
        when {
            request().isFirstPage() -> {
                progress_horizontal.visibility = View.GONE
            }
            else -> swipeRefreshHome.isRefreshing = false
        }
        swipeRefreshHome.isRefreshing = false
    }

    override fun onDataNotFound() {
        setContentVisibility(false)
    }

    override fun onInternetError() {
        setContentVisibility(false)
    }
}
