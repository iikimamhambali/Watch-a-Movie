package com.android.themoviedb.deps.module

import com.android.themoviedb.viewmodel.DaoViewModel
import com.android.themoviedb.viewmodel.MovieViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { MovieViewModel(get()) }

    viewModel { DaoViewModel(get()) }
}