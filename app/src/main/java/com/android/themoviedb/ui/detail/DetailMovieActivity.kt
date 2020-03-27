package com.android.themoviedb.ui.detail

import android.os.Bundle
import android.view.View
import com.android.themoviedb.R
import com.android.themoviedb.base.BaseActivity
import com.android.themoviedb.helper.loadFromUrlWithPlaceholder
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.layout_toolbar_default.*

class DetailMovieActivity : BaseActivity() {

    private var movieId = 0
    private var titleMovie = ""

    companion object {
        const val MOVIE_ID = "movie_id"
        const val TITLE_MOVIE = "title_movie"
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
        setupContentData()
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

    private fun setupContentData() {
        ivThumbnailMovie.loadFromUrlWithPlaceholder("",R.drawable.ic_thumbnails,R.drawable.ic_thumbnails)
        tvTitle.text = ""
        tvReleaseDate.text = ""
        tvDescription.text = ""
    }

    private fun setOnClickToolbar() {
        ivToolbarBack.setOnClickListener { finish() }
    }
}
