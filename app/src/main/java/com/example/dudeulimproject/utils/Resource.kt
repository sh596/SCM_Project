package com.example.dudeulimproject.utils

import com.example.dudeulimproject.utils.Resource.Companion.failed

class Resource<out T>(
    val status: State,
    val data : T?,
    val message: String?
) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(State.Success, data, null)
        }
        fun <T> failed(msg :String): Resource<T> {
            return Resource(State.Failed, null, msg)
        }
        fun <T> loading(): Resource<T> {
            return Resource(State.Loading, null, null)
        }
    }
}