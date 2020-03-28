package com.android.themoviedb.deps.module

import com.android.themoviedb.local.LocalDatabseFactory
import org.koin.dsl.module

val localModule = module {

    single { LocalDatabseFactory.makeLocalDatabase(get()) }

    single { LocalDatabseFactory.makeRepositoryDao(get()) }
}