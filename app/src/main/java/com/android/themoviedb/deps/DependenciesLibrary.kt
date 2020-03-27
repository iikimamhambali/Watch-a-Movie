package com.android.themoviedb.deps

import com.android.themoviedb.deps.module.appModule
import com.android.themoviedb.deps.module.networkModule
import com.android.themoviedb.deps.module.repositoryModule
import com.android.themoviedb.deps.module.viewModelModule

val library = listOf(appModule, repositoryModule, viewModelModule, networkModule)