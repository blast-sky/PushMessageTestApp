package com.example.pushmessagetestapp.data

import android.content.Context
import android.util.Log
import com.example.pushmessagetestapp.data.mapper.MessageMapper
import com.example.pushmessagetestapp.domain.model.Message
import com.example.pushmessagetestapp.util.optimizedLazy
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StoreUtil @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    private val firebaseFirestore by optimizedLazy { FirebaseFirestore.getInstance() }

    private val chatsCollectionReference by optimizedLazy {
        firebaseFirestore.collection(CHAT_COLLECTION)
    }

    suspend fun getUserChats(userId: String) =
        suspendCancellableCoroutine<QuerySnapshot> { continuation ->
            chatsCollectionReference.whereArrayContains(USERS_COLLECTION, userId).get()
                .addOnSuccessListener { chats ->
                    continuation.resumeWith(Result.success(chats))
                }.addOnFailureListener { exception ->
                    continuation.resumeWith(Result.failure(exception))
                }.addOnCanceledListener {
                    continuation.cancel()
                }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getChatMessagesFlow(chatId: String) = callbackFlow {
        val registration = chatsCollectionReference.document(chatId).collection(MESSAGE_COLLECTION)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    Log.e("APP", "error with firestore chat snapshot listener", error)
                    return@addSnapshotListener
                }
                trySend(value!!.documents.map(MessageMapper::map))
            }
        awaitClose { registration.remove() }
    }

    companion object {
        const val CHAT_COLLECTION = "chats"
        const val MESSAGE_COLLECTION = "messages"
        const val USERS_COLLECTION = "users"
    }
}