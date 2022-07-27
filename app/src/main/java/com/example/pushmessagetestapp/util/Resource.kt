package com.example.pushmessagetestapp.util

sealed class Resource<T> {
    class Success<T>(val value: T) : Resource<T>()
    class Error<T>(val error: Throwable): Resource<T>()
    class Loading<T>: Resource<T>()
    class Created<T>: Resource<T>()
}
