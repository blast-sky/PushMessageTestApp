package com.example.pushmessagetestapp.util

import com.example.pushmessagetestapp.data.StoreUtil
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.suspendCancellableCoroutine

fun <T> optimizedLazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)

suspend fun <T> Task<T>.suspend() = suspendCancellableCoroutine<T> { continuation ->
    addOnSuccessListener { value ->
        continuation.resumeWith(Result.success(value))
    }.addOnFailureListener { exception ->
        continuation.resumeWith(Result.failure(exception))
    }.addOnCanceledListener {
        continuation.cancel()
    }
}