package com.example.pushmessagetestapp.domain.repository

import com.example.pushmessagetestapp.domain.model.Chat
import com.example.pushmessagetestapp.domain.model.Message
import com.example.pushmessagetestapp.domain.model.User
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getUserChats(userId: String): List<Chat>

    suspend fun addNewUser(user: User): String

    suspend fun getChatMessages(chatId: String): Flow<List<Message>>

    suspend fun sendMessage(chatId: String, message: String, fromId: String): String
}