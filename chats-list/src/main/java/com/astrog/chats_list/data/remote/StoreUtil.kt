package com.astrog.chats_list.data.remote

import com.astrog.firestorecommon.dto.ChatDto
import com.astrog.firestorecommon.dto.MessageDto
import com.astrog.firestorecommon.dto.UserDto
import com.astrog.firestorecommon.toChatDto
import com.astrog.firestorecommon.toMessageDto
import com.astrog.firestorecommon.toUserDto
import com.google.firebase.firestore.*
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

        const val MESSAGE_CREATED = "created"
    }

    suspend fun getChatWithUsers(users: List<String>): List<String> {
        if(users.isEmpty()) return emptyList()
        return chatsCollectionReference
            .whereArrayContains(USERS_FIELD_IN_CHAT, users.first())
            .get()
            .suspend()
            .map(DocumentSnapshot::toChatDto)
            .filter { chatDto -> chatDto.users.containsAll(users.slice(1..users.lastIndex)) }
            .map(ChatDto::id)
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
            .orderBy(MESSAGE_CREATED, Query.Direction.ASCENDING)
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