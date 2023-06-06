package com.app.weatherinfo.util

import com.app.weatherinfo.api.Status

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: String?
) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String?, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, message)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }

        fun <T> completed(data: T?): Resource<T> {
            return Resource(Status.COMPLETED, data, null)
        }
    }
}