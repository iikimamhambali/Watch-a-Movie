package com.android.themoviedb.helper

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

fun Context.hasPermissions(permissions: Array<String>): Boolean {
    permissions.forEach {
        if (ActivityCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED)
            return false
    }
    return true
}

fun Activity.runTimePermissions(permissions: Array<String>, permissionCode: Int) {
    ActivityCompat.requestPermissions(this, permissions, permissionCode)
}

fun Context.checkPermissionResults(grantResults: IntArray): Boolean {
    grantResults.forEach {
        if (it != PackageManager.PERMISSION_GRANTED)
            return false
    }
    return true
}