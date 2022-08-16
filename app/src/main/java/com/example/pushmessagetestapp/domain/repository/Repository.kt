package com.example.pushmessagetestapp.domain.repository

import com.example.pushmessagetestapp.domain.model.Chat
import com.example.pushmessagetestapp.domain.model.Message
import com.example.pushmessagetestapp.domain.model.User
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun sendMessage(userId: String, message: String)

    suspend fun registerNewUser(user: User): String

    suspend fun createChat(chat: Chat): String

    suspend fun createMessage(chatId: String, message: String): String

    suspend fun getChat(chatId: String): Chat

    suspend fun getUserChats(userId: String): Flow<List<Chat>>

    suspend fun getChatMessages(chatId: String): Flow<List<Message>>

    suspend fun getAvailableUsers(userId: String): List<User>

    suspend fun getMessagingToken(): String

    suspend fun getOtherUserIdsInChat(chatId: String): List<String>

    suspend fun updateMessagingToken(newToken: String)

    val myUser: User
        get() = User(name = name, id = userId)

    val name: String

    val userId: String

    val isLogin: Boolean
}