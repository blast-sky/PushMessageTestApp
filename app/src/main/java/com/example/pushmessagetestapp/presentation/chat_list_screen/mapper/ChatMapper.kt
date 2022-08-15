package com.example.pushmessagetestapp.presentation.chat_list_screen.mapper

import com.example.pushmessagetestapp.domain.model.Chat
import com.example.pushmessagetestapp.domain.model.User
import com.example.pushmessagetestapp.presentation.chat_list_screen.model.ChatPresenterModel
import com.example.pushmessagetestapp.presentation.chat_list_screen.model.MessagePresenterModel

fun Chat.toChatPresenterModel(me: User) = ChatPresenterModel(
    id = id,
    title = (users.firstOrNull { user -> user != me } ?: me).name,
    lastMessage = messages.lastOrNull()?.from?.let { lastMessageUserId ->
        val user = users.find { user -> user.id == lastMessageUserId }!!
        messages.last().toMessagePresenterModel(from = user.name)
    } ?: MessagePresenterModel()
)