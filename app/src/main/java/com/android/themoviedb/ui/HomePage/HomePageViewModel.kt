package com.android.themoviedb.ui.HomePage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.android.themoviedb.base.BaseViewModel
import com.android.themoviedb.helper.Resource
import com.android.themoviedb.model.ConfirgurationResult
import com.android.themoviedb.model.LatestResult
import com.android.themoviedb.model.NowPlayingRequest
import com.android.themoviedb.model.MovieResult
import com.android.themoviedb.repository.MovieRepository

class HomePageViewModel(repository: MovieRepository) : BaseViewModel() {

    private val nowPlayingRequest = MutableLiveData<NowPlayingRequest>()
    private val configurationRequest = MutableLiveData<String>()

    val listNowPlayingMovie: LiveData<Resource<MovieResult>> = Transformations
        .switchMap(nowPlayingRequest) {
            repository.getNowPlaying(it)
        }

    fun getNowPlayingMovie(request: NowPlayingRequest) {
        nowPlayingRequest.value = request
    }

    val configuration: LiveData<Resource<ConfirgurationResult>> = Transformations
        .switchMap(configurationRequest) {
            repository.getConfiguration(it)
        }

    fun getConfiguration(key: String) {
        configurationRequest.value = key
    }
}