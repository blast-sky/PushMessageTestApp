package com.astrog.chats_list.data.remote

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.suspendCancellableCoroutine


internal inline fun <T> Query.createCallbackFlow(
    errorMessage: String,
    crossinline mapper: (DocumentSnapshot) -> T,
): Flow<List<T>> = callbackFlow {
    val registration = addSnapshotListener { value, error ->
        if (error != null) {
            Log.e("APP", errorMessage, error)
            return@addSnapshotListener
        }
        value?.documents?.map(mapper)?.let { chat ->
            trySend(chat)
        }
    }
    awaitClose { registration.remove() }
}

@JvmName("suspendVoid")
suspend fun Task<Void>.suspend() = suspendCancellableCoroutine { continuation ->
    addOnSuccessListener {
        continuation.resumeWith(Result.success(Unit))
    }.addOnFailureListener { exception ->
        continuation.resumeWith(Result.failure(exception))
    }.addOnCanceledListener {
        continuation.cancel()
    }
}

suspend fun <T> Task<T>.suspend() = suspendCancellableCoroutine<T> { continuation ->
    addOnSuccessListener { value ->
        continuation.resumeWith(Result.success(value))
    }.addOnFailureListener { exception ->
        continuation.resumeWith(Result.failure(exception))
    }.addOnCanceledListener {
        continuation.cancel()
    }
}