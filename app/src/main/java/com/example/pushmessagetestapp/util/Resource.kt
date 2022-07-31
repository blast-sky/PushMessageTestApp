package com.example.pushmessagetestapp.util

sealed class Resource<out T> {
    class Success<out T>(val value: T) : Resource<T>()
    class Error<out T>(val error: Throwable): Resource<T>()
    class Loading<out T>: Resource<T>()
    class Created<out T>: Resource<T>()
}
