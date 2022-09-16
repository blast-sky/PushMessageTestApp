package com.astrog.firestorecommon

import com.google.firebase.firestore.FirebaseFirestore


abstract class CommonFirestoreUtil {

    private val firebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    protected val chatsCollectionReference by lazy {
        firebaseFirestore.collection(Collections.CHAT_COLLECTION)
    }

    protected val usersCollectionReference by lazy {
        firebaseFirestore.collection(Collections.USERS_COLLECTION)
    }

    private fun getChatReferenceById(chatId: String) = chatsCollectionReference.document(chatId)

    protected companion object {
        object Collections {
            const val CHAT_COLLECTION = "chats"
            const val MESSAGE_COLLECTION = "messages"
            const val USERS_COLLECTION = "users"
        }

        object Fields {
            object User {
                const val NAME = "name"
                const val PASSWORD = "password"
                const val MESSAGE_TOKEN = "messageToken"
            }

            const val USERS_FIELD_IN_CHAT = "users"
        }
    }
}