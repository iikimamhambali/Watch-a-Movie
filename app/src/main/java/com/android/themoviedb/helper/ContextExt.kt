package com.android.themoviedb.helper

import android.content.Context
import android.os.Build
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun Context.getColorCompat(@ColorRes colorId: Int) =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        ContextCompat.getColor(this, colorId)
    } else this.resources.getColor(colorId)
