package com.example.pushmessagetestapp.data.repository

import com.example.pushmessagetestapp.data.StoreUtil
import com.example.pushmessagetestapp.data.dto.ChatDto
import com.example.pushmessagetestapp.data.dto.MessageDto
import com.example.pushmessagetestapp.data.dto.UserDto
import com.example.pushmessagetestapp.data.mapper.toMessage
import com.example.pushmessagetestapp.domain.model.Chat
import com.example.pushmessagetestapp.domain.model.Message
import com.example.pushmessagetestapp.domain.model.User
import com.example.pushmessagetestapp.domain.repository.Repository
import com.google.firebase.firestore.ktx.toObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val storeUtil: StoreUtil
) : Repository {

    override suspend fun getUserChats(userId: String): List<Chat> = storeUtil
        .getUserChats(userId)
        .map { queryDocumentSnapshot ->
            val chatDto = queryDocumentSnapshot.toObject<ChatDto>()
            val users = chatDto.users.map { userId ->
                val name = storeUtil.getUserById(userId).toObject<UserDto>()?.name
                    ?: "No User Name"
                User(messageToken = userId, name = name)
            }
            val messages = storeUtil.getMessages(queryDocumentSnapshot.reference).map {
                it.toObject<MessageDto>()?.toMessage() ?: MessageDto().toMessage()
            }

            return@map Chat(users = users, messages = messages)
        }

    override suspend fun addNewUser(user: User): String = storeUtil.addNewUser(user).id

}