package com.example.pushmessagetestapp.data

import android.content.Context
import android.util.Log
import com.example.pushmessagetestapp.data.mapper.MessageMapper
import com.example.pushmessagetestapp.data.mapper.toUserDto
import com.example.pushmessagetestapp.domain.model.User
import com.example.pushmessagetestapp.util.optimizedLazy
import com.example.pushmessagetestapp.util.suspend
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StoreUtil @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    private val firebaseFirestore by optimizedLazy {
        FirebaseFirestore.getInstance()
    }

    private val chatsCollectionReference by optimizedLazy {
        firebaseFirestore.collection(CHAT_COLLECTION)
    }

    private val usersCollectionReference by optimizedLazy {
        firebaseFirestore.collection(USERS_COLLECTION)
    }

    suspend fun getUserChats(userId: String): QuerySnapshot =
        chatsCollectionReference
            .whereArrayContains(USERS_COLLECTION_IN_CHAT, userId)
            .get()
            .suspend()

    suspend fun getMessages(charReference: DocumentReference) =
        charReference.collection(MESSAGE_COLLECTION)
            .get()
            .suspend()
            .documents

    suspend fun getUserById(userId: String): DocumentSnapshot =
        usersCollectionReference.document(userId).get().suspend()

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

    suspend fun addNewUser(user: User): DocumentReference =
        usersCollectionReference.add(user.toUserDto()).suspend()

    companion object {
        const val CHAT_COLLECTION = "chats"
        const val MESSAGE_COLLECTION = "messages"
        const val USERS_COLLECTION = "users"
        const val USERS_COLLECTION_IN_CHAT = "users"
    }
}