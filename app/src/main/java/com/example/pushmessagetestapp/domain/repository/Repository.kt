package com.example.pushmessagetestapp.domain.repository

import com.example.pushmessagetestapp.domain.model.Chat

interface Repository {

    suspend fun getUserChats(userId: String): List<Chat>

}