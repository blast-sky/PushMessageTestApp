package com.example.pushmessagetestapp.presentation.chat_list_screen.mapper

import com.astrog.chats_list.domain.model.Chat
import com.astrog.chats_list.domain.model.User
import com.example.pushmessagetestapp.presentation.chat_list_screen.model.ChatPresenterModel
import com.example.pushmessagetestapp.presentation.chat_list_screen.model.MessagePresenterModel

fun Chat.toChatPresenterModel(me: User) = ChatPresenterModel(
    id = id,
    title = (users.firstOrNull { user -> user.id != me.id } ?: me).name,
    lastMessage = messages.lastOrNull()?.from?.let { lastMessageUserId ->
        val user = users.find { user -> user.id == lastMessageUserId }!!
        messages.last().toMessagePresenterModel(from = user.name)
    } ?: MessagePresenterModel()
)