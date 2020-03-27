package com.android.themoviedb.deps.module

import com.android.themoviedb.ui.HomePage.HomePageViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { HomePageViewModel(get()) }
}