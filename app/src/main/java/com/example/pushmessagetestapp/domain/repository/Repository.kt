package com.example.pushmessagetestapp.domain.repository

import com.example.pushmessagetestapp.domain.model.Chat
import com.example.pushmessagetestapp.domain.model.User

interface Repository {

    suspend fun getUserChats(userId: String): List<Chat>

    suspend fun addNewUser(user: User): String

}