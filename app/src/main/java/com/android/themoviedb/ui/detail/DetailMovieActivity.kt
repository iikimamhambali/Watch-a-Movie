package com.android.themoviedb.ui.detail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.android.themoviedb.R
import com.android.themoviedb.base.BaseActivity
import com.android.themoviedb.base.BaseRecyclerView
import com.android.themoviedb.helper.*
import com.android.themoviedb.model.*
import com.android.themoviedb.ui.detail.adapter.CompanyAdapter
import com.android.themoviedb.ui.detail.adapter.ReviewAdapter
import com.android.themoviedb.viewmodel.DaoViewModel
import com.android.themoviedb.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.activity_detail_movie.ivFavoriteMovie
import kotlinx.android.synthetic.main.activity_detail_movie.ivThumbnailMovie
import kotlinx.android.synthetic.main.activity_detail_movie.nested
import kotlinx.android.synthetic.main.activity_detail_movie.progressDetail
import kotlinx.android.synthetic.main.activity_detail_movie.rvCompany
import kotlinx.android.synthetic.main.activity_detail_movie.rvReview
import kotlinx.android.synthetic.main.activity_detail_movie.sectionEmptyState
import kotlinx.android.synthetic.main.activity_detail_movie.sectionReview
import kotlinx.android.synthetic.main.activity_detail_movie.tvBodyAlertVerified
import kotlinx.android.synthetic.main.activity_detail_movie.tvDescription
import kotlinx.android.synthetic.main.activity_detail_movie.tvTitle
import kotlinx.android.synthetic.main.activity_detail_movie.tvTitleAlertVerified
import kotlinx.android.synthetic.main.activity_detail_movie.tvVoteAverage
import kotlinx.android.synthetic.main.layout_toolbar_default.*
import org.jetbrains.anko.doAsync
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMovieActivity : BaseActivity() {

    private val viewModel by viewModel<MovieViewModel>()
    private val viewModelDao by viewModel<DaoViewModel>()

    private lateinit var resultDetail: MovieDetailResult
    private val resultList = mutableListOf<MovieReviewList>()
    private var resultCompany = mutableListOf<ProductionCompany>()
    private val adapterReview by lazy { ReviewAdapter(resultList) }
    private val adapterCompany by lazy { CompanyAdapter(resultCompany) }

    private var movieId = 0
    private var titleMovie = ""
    private var isSelected = false

    companion object {
        const val MOVIE_ID = "movie_id"
        const val TITLE_MOVIE = "title_movie"
        const val LANGUAGE = "en-US"
        const val PERMISSION_REQUEST = 100
    }

    override fun getLayoutResId(): Int = R.layout.activity_detail_movie

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        getIntentData()
        setupToolbar()
        setupRecyclerView()
    }

    override fun initEvent() {
        super.initEvent()
        setOnClickToolbar()
        setOnClickFavorite()
    }

    private fun getIntentData() {
        intent?.extras?.let {
            movieId = it.getInt(MOVIE_ID, 0)
            titleMovie = it.getString(TITLE_MOVIE, "")
        }
    }

    private fun setupToolbar() {
        ivToolbarBack.visibility = View.VISIBLE
        tvToolbarInfoTitle.text = titleMovie
    }

    private fun setupRecyclerView() {
        with(rvReview) {
            initRecyclerView(adapterReview, BaseRecyclerView.LayoutManager.VERTICAL)
        }

        with(rvCompany) {
            initRecyclerView(adapterCompany, BaseRecyclerView.LayoutManager.HORIZONTAL)
            initItemDecoration(4)
        }
    }

    private fun setupContentData(resultData: MovieDetailResult) {
        val imageUrl = when (!resultData.backdropPath.isNullOrEmpty()) {
            true -> "https://image.tmdb.org/t/p/w780" + resultData.backdropPath
            else -> ""
        }

        ivThumbnailMovie.loadFromUrlWithPlaceholder(
            imageUrl,
            R.drawable.ic_thumbnails,
            R.drawable.ic_thumbnails
        )
        tvVoteAverage.text = resultData.voteAverage.toString()
        tvTitle.text = resultData.title
        tvTotalCount.text = resultData.voteCount.toString() + " " + "Reviews"
        tvDescription.text = resultData.overview
        addDataCompany(resultData.productionCompanies)

    }

    private fun setOnClickToolbar() {
        ivToolbarBack.setOnClickListener { finish() }
    }

    private val permissions = arrayOf(
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.READ_EXTERNAL_STORAGE
    )

    private fun setOnClickFavorite() {
        ivFavoriteMovie.setOnClickListener {
            when (hasPermissions(permissions)) {
                true -> {
                    doAsync {
                        when (viewModelDao.storeDataMovie(resultDetail)) {
                            true -> resultDetail.isChecked = true
                            else -> setVisibilityFavourite(false)
                        }
                    }
                    setVisibilityFavourite(true)
                }
                else -> {
                    runTimePermissions(permissions, PERMISSION_REQUEST)
                    isSelected = false
                }
            }
        }
    }

    private fun setVisibilityFavourite(visible: Boolean) {
        when (visible) {
            true -> ivFavoriteMovie.loadFromResource(R.drawable.ic_heart_color)
            else -> ivFavoriteMovie.loadFromResource(R.drawable.ic_heart_line)
        }
    }

    private fun addData(data: List<MovieReviewList>) {
        resultList.clear()
        resultList.addAll(data)
        adapterReview.notifyDataSetChanged()
    }

    private fun addDataCompany(data: List<ProductionCompany>) {
        resultCompany.clear()
        resultCompany.addAll(data)
        adapterCompany.notifyDataSetChanged()
    }

    override fun loadingData(isFromSwipe: Boolean) {
        super.loadingData(isFromSwipe)
        val requestData = MovieDetailRequest(movieId, getString(R.string.api_key), LANGUAGE, "")
        val requestReview =
            MovieReviewRequest(movieId, getString(R.string.api_key), LANGUAGE, 1)
        viewModel.getDetailMovie(requestData)
        viewModel.getReviewMovie(requestReview)
    }

    override fun observeData() {
        super.observeData()
        viewModel.detailMovie.observe(this, Observer {
            parseObserveData(it, resultSuccess = { result, _ ->
                resultDetail = result
                setupContentData(result)
                if (isSelected) setVisibilityFavourite(true)
            })
        })

        viewModel.reviewMovie.observe(this, Observer {
            parseObserveData(
                it,
                resultLoading = {},
                resultDataNotFound = {},
                resultSuccess = { result, pagination ->
                    if (result.results.isNullOrEmpty()) {
                        sectionReview.visibility = View.INVISIBLE
                        return@parseObserveData
                    }
                    addData(result.results)
                })
        })
    }

    override fun startLoading() {
        progressDetail.visibility = View.VISIBLE
        nested.visibility = View.GONE
    }

    override fun stopLoading() {
        progressDetail.visibility = View.GONE
        nested.visibility = View.VISIBLE
    }

    override fun onDataNotFound() {
        sectionEmptyState.visibility = View.VISIBLE
        tvTitleAlertVerified.text = getString(R.string.label_title_empty_detail)
        tvBodyAlertVerified.text = getString(R.string.label_subtitle_empty_detail)
    }

    override fun onInternetError() {
        sectionEmptyState.visibility = View.VISIBLE
        tvTitleAlertVerified.text = getString(R.string.label_title_empty_detail)
        tvBodyAlertVerified.text = getString(R.string.label_subtitle_empty_detail)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST) {
            when (checkPermissionResults(grantResults)) {
                true -> doAsync {
                    viewModelDao.storeDataMovie(resultDetail)
                }
            }
        }
    }
}
