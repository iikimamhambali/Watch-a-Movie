package com.android.themoviedb.ui.detail

import android.os.Bundle
import android.util.Log.d
import android.view.View
import androidx.lifecycle.Observer
import com.android.themoviedb.R
import com.android.themoviedb.base.BaseActivity
import com.android.themoviedb.base.BaseRecyclerView
import com.android.themoviedb.helper.checkPermissionResults
import com.android.themoviedb.helper.hasPermissions
import com.android.themoviedb.helper.loadFromUrlWithPlaceholder
import com.android.themoviedb.helper.runTimePermissions
import com.android.themoviedb.local.RepositoryDao
import com.android.themoviedb.model.*
import com.android.themoviedb.ui.detail.adapter.CompanyAdapter
import com.android.themoviedb.ui.detail.adapter.ReviewAdapter
import com.android.themoviedb.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.layout_toolbar_default.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMovieActivity : BaseActivity() {

    private val viewModel by viewModel<MovieViewModel>()

    private lateinit var resultDetail: MovieDetailResult
    private val resultList = mutableListOf<MovieReviewList>()
    private var resultCompany = mutableListOf<ProductionCompany>()
    private val adapterReview by lazy { ReviewAdapter(resultList) }
    private val adapterCompany by lazy { CompanyAdapter(resultCompany) }

    private var movieId = 0
    private var titleMovie = ""
    private val dao: RepositoryDao? = null

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
        val imageUrl = "https://image.tmdb.org/t/p/w500" + resultData.backdropPath
        ivThumbnailMovie.loadFromUrlWithPlaceholder(
            imageUrl,
            R.drawable.ic_thumbnails,
            R.drawable.ic_thumbnails
        )
        tvVoteAverage.text = resultData.voteAverage.toString()
        tvTitle.text = resultData.title
        tvReleaseDate.text = resultData.releaseDate
        tvDescription.text = resultData.overview
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
                    dao?.saveFavorite(resultDetail)
                    d("LOGLOG", resultDetail.toString())
                }
                else -> runTimePermissions(permissions, PERMISSION_REQUEST)
            }
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
                addDataCompany(result.productionCompanies)
            })
        })

        viewModel.reviewMovie.observe(this, Observer {
            parseObserveData(it, resultSuccess = { result, pagination ->
                if (result.results.isNullOrEmpty()) {
                    return@parseObserveData
                }
                addData(result.results)
            })
        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST) {
            when (checkPermissionResults(grantResults)) {
                true -> dao?.saveFavorite(resultDetail)
            }
        }
    }
}
