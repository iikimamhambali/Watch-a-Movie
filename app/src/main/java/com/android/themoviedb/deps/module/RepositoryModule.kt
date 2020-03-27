package com.android.themoviedb.deps.module

import com.android.themoviedb.repository.MovieRepository
import org.koin.dsl.module

val repositoryModule = module {

    single { MovieRepository(get(), get()) }
}