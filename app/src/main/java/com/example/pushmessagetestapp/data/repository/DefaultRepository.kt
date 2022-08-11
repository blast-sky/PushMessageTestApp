package com.example.pushmessagetestapp.data.repository

import com.example.pushmessagetestapp.data.dto.MessageDto
import com.example.pushmessagetestapp.data.dto.UserDto
import com.example.pushmessagetestapp.data.local.SharedPreferencesUserUtil
import com.example.pushmessagetestapp.data.mapper.dto.toChat
import com.example.pushmessagetestapp.data.mapper.dto.toDto
import com.example.pushmessagetestapp.data.mapper.dto.toMessage
import com.example.pushmessagetestapp.data.mapper.dto.toUser
import com.example.pushmessagetestapp.data.remote.MessagingUtil
import com.example.pushmessagetestapp.data.remote.StoreUtil
import com.example.pushmessagetestapp.domain.model.Chat
import com.example.pushmessagetestapp.domain.model.Message
import com.example.pushmessagetestapp.domain.model.User
import com.example.pushmessagetestapp.domain.repository.Repository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ActivityRetainedScoped
class DefaultRepository @Inject constructor(
    private val storeUtil: StoreUtil,
    private val messagingUtil: MessagingUtil,
    private val sharedPreferencesUserUtil: SharedPreferencesUserUtil,
) : Repository {

    override suspend fun getUserChats(userId: String): Flow<List<Chat>> =
        storeUtil
            .getChatsFlow(userId)
            .map { dtoList -> dtoList.map { chatDto -> chatDto.toChat(storeUtil) } }

    override suspend fun getChatMessages(chatId: String): Flow<List<Message>> =
        storeUtil
            .getMessagesFlow(chatId)
            .map { dtoList -> dtoList.map(MessageDto::toMessage) }

    override suspend fun getAvailableUsers(userId: String): List<User> =
        storeUtil
            .getAllUser()
            .map(UserDto::toUser)

    override suspend fun registerNewUser(user: User): String =
        storeUtil
            .addNewUser(user.toDto())
            .id.apply {
                if (isNotBlank()) sharedPreferencesUserUtil.register(this, user.name)
            }

    override suspend fun sendMessage(chatId: String, message: String, fromId: String): String =
        storeUtil
            .addNewMessage(chatId, MessageDto(message = message, from = fromId))
            .id

    override suspend fun createChat(chat: Chat): String =
        storeUtil
            .addNewChat(chat.toDto())
            .id

    override suspend fun getMessagingToken(): String = messagingUtil.getToken()

    override val userId: String
        get() = sharedPreferencesUserUtil.userId

    override val isLogin: Boolean
        get() = sharedPreferencesUserUtil.isLogin
}