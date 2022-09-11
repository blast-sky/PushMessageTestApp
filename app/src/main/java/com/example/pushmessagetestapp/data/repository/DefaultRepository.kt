package com.example.pushmessagetestapp.data.repository

import com.example.pushmessagetestapp.data.dto.firestore.MessageDto
import com.example.pushmessagetestapp.data.dto.firestore.UserDto
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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val storeUtil: StoreUtil,
    private val messagingUtil: MessagingUtil,
    private val sharedPreferencesUserUtil: SharedPreferencesUserUtil,
) : Repository {

    override suspend fun updateMessagingToken(newToken: String): Unit =
        storeUtil
            .updateMessagingToken(userId, newToken)

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
            .id.also { id ->
                if (id.isNotBlank()) sharedPreferencesUserUtil.register(id, user.name)
            }

    override suspend fun createMessage(chatId: String, message: String): String =
        storeUtil
            .addNewMessage(chatId, MessageDto(message = message, from = userId))
            .id

    override suspend fun sendMessage(userId: String, message: String): Unit =
        storeUtil
            .getUserById(userId)
            .messageToken
            .let { token ->
                messagingUtil.sendMessage(token, message, name)
            }

    override suspend fun getChat(chatId: String): Chat =
        storeUtil
            .getChat(chatId)
            .toChat(storeUtil)

    override suspend fun getOtherUserIdsInChat(chatId: String): List<String> =
        storeUtil
            .getChat(chatId)
            .users
            .filter { id -> id != userId }

    override suspend fun createChat(chat: Chat): String =
        storeUtil
            .addNewChat(chat.toDto())
            .id

    override suspend fun getMessagingToken(): String = messagingUtil.getToken()

    override val name: String
        get() = sharedPreferencesUserUtil.name

    override val userId: String
        get() = sharedPreferencesUserUtil.userId

    override val isLogin: Boolean
        get() = sharedPreferencesUserUtil.isLogin
}