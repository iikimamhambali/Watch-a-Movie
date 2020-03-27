package com.android.themoviedb.ui.detail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.android.themoviedb.R
import com.android.themoviedb.base.BaseActivity
import com.android.themoviedb.helper.loadFromUrlWithPlaceholder
import com.android.themoviedb.model.MovieDetailRequest
import com.android.themoviedb.model.MovieDetailResult
import com.android.themoviedb.ui.homePage.HomePageViewModel
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.layout_toolbar_default.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMovieActivity : BaseActivity() {

    private val viewModel by viewModel<HomePageViewModel>()

    private var movieId = 0
    private var titleMovie = ""

    companion object {
        const val MOVIE_ID = "movie_id"
        const val TITLE_MOVIE = "title_movie"
        const val LANGUAGE = "en-US"
    }

    override fun getLayoutResId(): Int = R.layout.activity_detail_movie

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        getIntentData()
        setupToolbar()
    }

    override fun initEvent() {
        super.initEvent()
        setOnClickToolbar()
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

    private fun setupContentData(resultData: MovieDetailResult) {
        val imageUrl = "https://image.tmdb.org/t/p/w500" + resultData.posterPath
        ivThumbnailMovie.loadFromUrlWithPlaceholder(
            imageUrl,
            R.drawable.ic_thumbnails,
            R.drawable.ic_thumbnails
        )
        tvTitle.text = resultData.title
        tvReleaseDate.text = resultData.releaseDate
        tvDescription.text = resultData.overview
    }

    private fun setOnClickToolbar() {
        ivToolbarBack.setOnClickListener { finish() }
    }

    override fun loadingData(isFromSwipe: Boolean) {
        super.loadingData(isFromSwipe)
        val requestData = MovieDetailRequest(movieId, getString(R.string.api_key), LANGUAGE, "")
        viewModel.getDetailMovie(requestData)
    }

    override fun observeData() {
        super.observeData()
        viewModel.detailMovie.observe(this, Observer {
            parseObserveData(it, resultSuccess = { result, _ ->
                setupContentData(result)
            })
        })
    }

    override fun startLoading() {
        progressDetail.visibility = View.VISIBLE
        progressDetail.startShimmer()
    }

    override fun stopLoading() {
        progressDetail.visibility = View.GONE
        progressDetail.stopShimmer()
    }
}
