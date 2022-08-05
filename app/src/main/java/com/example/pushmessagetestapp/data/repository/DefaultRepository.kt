package com.example.pushmessagetestapp.data.repository

import com.example.pushmessagetestapp.data.dto.MessageDto
import com.example.pushmessagetestapp.data.mapper.dto.toChat
import com.example.pushmessagetestapp.data.mapper.dto.toMessage
import com.example.pushmessagetestapp.data.mapper.dto.toDto
import com.example.pushmessagetestapp.data.remote.StoreUtil
import com.example.pushmessagetestapp.domain.model.Chat
import com.example.pushmessagetestapp.domain.model.Message
import com.example.pushmessagetestapp.domain.model.User
import com.example.pushmessagetestapp.domain.repository.Repository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class DefaultRepository @Inject constructor(
    private val storeUtil: StoreUtil
) : Repository {

    override suspend fun getUserChats(userId: String): List<Chat> =
        storeUtil
            .getUserChats(userId)
            .map { chatDto -> chatDto.toChat(storeUtil) }

    override suspend fun getChatMessages(chatId: String): Flow<List<Message>> =
        storeUtil.getMessagesFlow(chatId).map { it.map(MessageDto::toMessage) }

    override suspend fun addNewUser(user: User): String =
        storeUtil.addNewUser(user.toDto()).id

    override suspend fun sendMessage(chatId: String, message: String, fromId: String): String =
        storeUtil.addNewMessage(chatId, MessageDto(message = message, from = fromId)).id

    override suspend fun createChat(chat: Chat): String =
        storeUtil.addNewChat(chat.toDto()).id
}