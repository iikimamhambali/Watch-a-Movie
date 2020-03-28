package com.android.themoviedb.deps.module

import com.android.themoviedb.local.makeLocalDatabase
import com.android.themoviedb.local.makeRepositoryDao
import org.koin.dsl.module

val localModule = module {

    single { makeLocalDatabase(get()) }

    single { makeRepositoryDao(get()) }
}