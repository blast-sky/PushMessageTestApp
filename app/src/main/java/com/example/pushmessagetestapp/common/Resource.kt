package com.example.pushmessagetestapp.common

sealed class Resource<out T> {

    class Success<out T>(val value: T) : Resource<T>()

    class Error<out T>(val message: String, val error: Throwable? = null) : Resource<T>()

    class Loading<out T> : Resource<T>()
}

fun <T, R> Resource.Success<T>.map(transformation: (T) -> R) =
    Resource.Success(transformation.invoke(value))

fun <T, R> Resource.Error<T>.map() =
    Resource.Error<R>(message, error)

fun <T, R> Resource.Loading<T>.map() =
    Resource.Loading<R>()

fun <T, R> Resource<T>.map(transformation: (T) -> R) = when (this) {
    is Resource.Error -> map()
    is Resource.Loading -> map()
    is Resource.Success -> map(transformation)
}
