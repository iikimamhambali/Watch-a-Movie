package com.android.themoviedb.deps.module

import com.android.themoviedb.helper.AppExecutors
import org.koin.dsl.module

val appModule = module {
    single { AppExecutors() }
}