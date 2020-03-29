package com.android.themoviedb.helper

interface Mapper<in From, out To> {

    fun transform(from: From): To

    fun transfrom(from: List<From>): List<To> {
        val result = mutableListOf<To>()
        for (item in from) {
            result.add(transform(item))
        }
        return result
    }
}