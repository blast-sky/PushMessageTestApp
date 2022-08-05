package com.example.pushmessagetestapp.data.mapper.dto

import com.example.pushmessagetestapp.data.dto.ChatDto
import com.example.pushmessagetestapp.data.dto.MessageDto
import com.example.pushmessagetestapp.data.remote.StoreUtil
import com.example.pushmessagetestapp.domain.model.Chat
import com.example.pushmessagetestapp.domain.model.User

suspend fun ChatDto.toChat(storeUtil: StoreUtil) = Chat(
    id = id,
    users = users.map { userId -> storeUtil.getUserById(userId).toUser() },
    messages = storeUtil.getChatMessages(id).map(MessageDto::toMessage),
)

fun Chat.toDto() = ChatDto(
    id = id,
    users = users.map(User::id),
)
