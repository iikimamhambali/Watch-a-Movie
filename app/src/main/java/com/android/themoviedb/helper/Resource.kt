package com.android.themoviedb.helper

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val throwable: Throwable? = null
) {

    companion object {

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data)
        }

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> empty(): Resource<T> {
            return Resource(Status.DATA_NOT_FOUND, null, null)
        }

        fun <T> invalidToken(): Resource<T> {
            return Resource(Status.INVALID_TOKEN, null, null)
        }

        fun <T> error(throwable: Throwable? = null): Resource<T> {
            return Resource(Status.ERROR, null, throwable)
        }
    }
}