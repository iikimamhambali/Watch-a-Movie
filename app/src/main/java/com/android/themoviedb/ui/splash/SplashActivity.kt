package com.android.themoviedb.ui.splash

import android.os.Bundle
import android.os.Handler
import com.android.themoviedb.R
import com.android.themoviedb.base.BaseActivity
import com.android.themoviedb.ui.homePage.HomePageActivity
import org.jetbrains.anko.startActivity

class SplashActivity : BaseActivity() {

    companion object {
        private const val DELAY_SPLASH_SCREEN: Long = 2000
    }

    override fun getLayoutResId(): Int = R.layout.activity_splash

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)

        Handler().postDelayed({
            startActivity<HomePageActivity>()
        }, DELAY_SPLASH_SCREEN)
    }
}
