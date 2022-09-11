package com.example.pushmessagetestapp.data.remote

import com.example.pushmessagetestapp.data.dto.firestore.ChatDto
import com.example.pushmessagetestapp.data.dto.firestore.MessageDto
import com.example.pushmessagetestapp.data.dto.firestore.UserDto
import com.example.pushmessagetestapp.data.mapper.raw.toChatDto
import com.example.pushmessagetestapp.data.mapper.raw.toMessageDto
import com.example.pushmessagetestapp.data.mapper.raw.toUserDto
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StoreUtil @Inject constructor() {

    private val firebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    private val chatsCollectionReference by lazy {
        firebaseFirestore.collection(CHAT_COLLECTION)
    }

    private val usersCollectionReference by lazy {
        firebaseFirestore.collection(USERS_COLLECTION)
    }

    private fun getChatReferenceById(chatId: String) = chatsCollectionReference.document(chatId)

    private companion object {
        const val MESSAGE_TOKEN = "messageToken"
        const val CHAT_COLLECTION = "chats"
        const val MESSAGE_COLLECTION = "messages"
        const val USERS_COLLECTION = "users"
        const val USERS_FIELD_IN_CHAT = "users"
    }

    suspend fun updateMessagingToken(userId: String, newToken: String): Unit =
        usersCollectionReference
            .document(userId)
            .update(MESSAGE_TOKEN, newToken)
            .suspend()

    suspend fun getChats(userId: String): List<ChatDto> =
        chatsCollectionReference
            .whereArrayContains(USERS_FIELD_IN_CHAT, userId)
            .get()
            .suspend()
            .map(DocumentSnapshot::toChatDto)

    suspend fun getChat(chatId: String): ChatDto =
        chatsCollectionReference
            .document(chatId)
            .get()
            .suspend()
            .toChatDto()

    suspend fun getChatMessages(chatId: String): List<MessageDto> =
        getChatReferenceById(chatId)
            .collection(MESSAGE_COLLECTION)
            .get()
            .suspend()
            .documents
            .map(DocumentSnapshot::toMessageDto)

    suspend fun getUserById(userId: String): UserDto =
        usersCollectionReference
            .document(userId)
            .get()
            .suspend()
            .toUserDto()

    suspend fun addNewMessage(chatId: String, messageDto: MessageDto): DocumentReference =
        getChatReferenceById(chatId)
            .collection(MESSAGE_COLLECTION)
            .add(messageDto)
            .suspend()

    suspend fun addNewUser(user: UserDto): DocumentReference =
        usersCollectionReference
            .add(user)
            .suspend()

    suspend fun addNewChat(chatDto: ChatDto): DocumentReference =
        chatsCollectionReference
            .add(chatDto)
            .suspend()

    suspend fun getAllUser(): List<UserDto> =
        usersCollectionReference
            .get()
            .suspend()
            .map(DocumentSnapshot::toUserDto)

    fun getMessagesFlow(chatId: String): Flow<List<MessageDto>> =
        getChatReferenceById(chatId)
            .collection(MESSAGE_COLLECTION)
            .createCallbackFlow(
                errorMessage = "error with firestore chat message snapshot listener",
                mapper = DocumentSnapshot::toMessageDto
            )

    fun getChatsFlow(userId: String): Flow<List<ChatDto>> =
        chatsCollectionReference
            .whereArrayContains(USERS_FIELD_IN_CHAT, userId)
            .createCallbackFlow(
                errorMessage = "error with firestore chats snapshot listener",
                mapper = DocumentSnapshot::toChatDto
            )
}