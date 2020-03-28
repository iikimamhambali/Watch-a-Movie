package com.android.themoviedb.base

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.android.themoviedb.helper.*

abstract class BaseRepo<Type>(private val appExecutors: AppExecutors) {

    private val result = MediatorLiveData<Resource<Type>>()

    init {
        result.value = Resource.loading(null)
        @Suppress("LeakingThis")
        val dbSource = loadFromLocal()
        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetchFromNetwork(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData ->
                    setValue(Resource.success(newData))
                }
            }
        }
    }

    @MainThread
    private fun setValue(newValue: Resource<Type>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<Type>) {
        val apiResponse = loadFromNetwork()
        result.addSource(dbSource) { newData ->
            setValue(Resource.loading(newData))
        }
        setValue(Resource.loading())
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when (response) {
                is ApiSuccessResponse -> {
                    appExecutors.diskIO().execute {
                        val newResponse = processResponse(response)
                        saveFromNetwork(newResponse)
                        appExecutors.mainThread().execute {
                            result.addSource(apiResponse) {
                                setValue(Resource.success(newResponse))
                            }
                        }
                    }
                }
                is ApiEmptyResponse -> {
                    appExecutors.mainThread().execute {
                        // reload from disk whatever we had
                        setValue(Resource.empty())
                    }
                }
                is ApiErrorResponse -> {
                    appExecutors.mainThread().execute {
                        setValue(Resource.error(null))
                    }
                }
            }
        }
    }

    fun asLiveData() = result as LiveData<Resource<Type>>

    @WorkerThread
    protected open fun processResponse(response: ApiSuccessResponse<Type>) = response.body

    @WorkerThread
    protected abstract fun saveFromNetwork(item: Type)

    @MainThread
    protected abstract fun shouldFetchFromNetwork(data: Type?): Boolean

    @MainThread
    protected abstract fun loadFromLocal(): LiveData<Type>

    @MainThread
    protected abstract fun loadFromNetwork(): LiveData<ApiResponse<Type>>
}