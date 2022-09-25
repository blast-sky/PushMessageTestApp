package com.astrog.chats_list.data.repository

import android.net.Uri
import com.astrog.chats_list.data.remote.CloudStorage
import com.astrog.chats_list.data.remote.MessagingUtil
import com.astrog.chats_list.data.remote.StoreUtil
import com.astrog.chats_list.data.remote.UploadResult
import com.astrog.chats_list.data.remote.retrofit.mapper.toChat
import com.astrog.chats_list.data.remote.retrofit.mapper.toDto
import com.astrog.chats_list.data.remote.retrofit.mapper.toMessage
import com.astrog.chats_list.data.remote.retrofit.mapper.toUser
import com.astrog.chats_list.domain.model.Chat
import com.astrog.chats_list.domain.model.Message
import com.astrog.chats_list.domain.model.User
import com.astrog.chats_list.domain.repository.Repository
import com.astrog.firestorecommon.dto.MessageDto
import com.astrog.firestorecommon.dto.UserDto
import com.astrog.shared_preference.SharedPreferencesUserUtil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val storeUtil: StoreUtil,
    private val storage: CloudStorage,
    private val messagingUtil: MessagingUtil,
    private val sharedPreferencesUserUtil: SharedPreferencesUserUtil,
) : Repository {

    override fun unLogin() = sharedPreferencesUserUtil.unLogin()

    override suspend fun getChatsWithUsers(userIds: List<String>): List<String> =
        storeUtil
            .getChatWithUsers(userIds)

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

    override suspend fun getAvailableUsers(): List<User> =
        storeUtil
            .getAllUser()
            .map(UserDto::toUser)
            .filter { user ->
                val chatsWithThatUsers = storeUtil.getChatWithUsers(listOf(user.id, this.userId))
                return@filter chatsWithThatUsers.isEmpty()
            }

    override suspend fun registerNewUser(user: User): String =
        storeUtil
            .addNewUser(user.toDto())
            .id.also { id ->
                if (id.isNotBlank()) sharedPreferencesUserUtil.login(id, user.name)
            }

    override suspend fun createMessage(chatId: String, message: String, image: String): String =
        storeUtil
            .addNewMessage(
                chatId,
                MessageDto(message = message, from = userId, image = image)
            )
            .id

    override suspend fun uploadImages(image: Uri): Flow<UploadResult> = // TODO
        storage.uploadImage(image)

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