package com.example.pushmessagetestapp.data.repository

import com.example.pushmessagetestapp.data.StoreUtil
import com.example.pushmessagetestapp.data.dto.ChatDto
import com.example.pushmessagetestapp.data.dto.UserDto
import com.example.pushmessagetestapp.data.mapper.toMessage
import com.example.pushmessagetestapp.domain.model.Chat
import com.example.pushmessagetestapp.domain.model.User
import com.example.pushmessagetestapp.domain.repository.Repository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val storeUtil: StoreUtil
) : Repository {

    override suspend fun getUserChats(userId: String): List<Chat> = storeUtil
        .getUserChats(userId)
        .map { documentSnapshot ->
            val dto = documentSnapshot.toObject(ChatDto::class.java)
            val users = dto.users.map { userId ->
                val name = storeUtil.getUserById(userId).toObject(UserDto::class.java)?.name ?: "No User Name"
                User(token = userId, name = name)
            }
            val messages = dto.messages.map { it.toMessage() }
            Chat(users = users, messages = messages)
        }
}