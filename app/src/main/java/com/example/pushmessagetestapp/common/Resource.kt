package com.example.pushmessagetestapp.common

sealed class Resource<out T> {

    class Success<out T>(val value: T) : Resource<T>()

    class Error<out T>(val message: String, val error: Throwable? = null): Resource<T>()

    class Loading<out T>: Resource<T>()
}
