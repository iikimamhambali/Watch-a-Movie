package com.android.themoviedb

import com.android.themoviedb.base.BaseApplication
import com.android.themoviedb.deps.library
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WhatAMoviewApp : BaseApplication() {

    override fun initApplication() {
        startKoin {
            modules(library)
            androidContext(this@WhatAMoviewApp)
        }
    }
}