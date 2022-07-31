package com.example.pushmessagetestapp.data.repository

import com.example.pushmessagetestapp.data.remote.StoreUtil
import com.example.pushmessagetestapp.data.dto.ChatDto
import com.example.pushmessagetestapp.data.dto.MessageDto
import com.example.pushmessagetestapp.data.dto.UserDto
import com.example.pushmessagetestapp.data.mapper.MessageMapper
import com.example.pushmessagetestapp.data.mapper.toMessage
import com.example.pushmessagetestapp.domain.model.Chat
import com.example.pushmessagetestapp.domain.model.Message
import com.example.pushmessagetestapp.domain.model.User
import com.example.pushmessagetestapp.domain.repository.Repository
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultRepository @Inject constructor(
    private val storeUtil: StoreUtil
) : Repository {

    override suspend fun getUserChats(userId: String): List<Chat> = storeUtil
        .getUserChats(userId)
        .map { queryDocumentSnapshot ->
            val chatDto = queryDocumentSnapshot.toObject<ChatDto>()
            val users = chatDto.users.map { userId ->
                val name = storeUtil.getUserById(userId).toObject<UserDto>()?.name
                    ?: "No User Name"
                User(id = userId, messageToken = userId, name = name)
            }
            val messages = storeUtil.getMessages(queryDocumentSnapshot.reference).map {
                it.toObject<MessageDto>()?.toMessage() ?: MessageDto().toMessage()
            }

            return@map Chat(id = chatDto.id, users = users, messages = messages)
        }

    override suspend fun addNewUser(user: User): String = storeUtil.addNewUser(user).id

    override suspend fun getChatMessages(chatId: String): Flow<List<Message>> =
        storeUtil.getChatMessagesFlow(chatId).map { it.map(MessageMapper::map) }

    override suspend fun sendMessage(chatId: String, message: String, fromId: String): String =
        storeUtil.addNewMessage(chatId, message, fromId).id

}