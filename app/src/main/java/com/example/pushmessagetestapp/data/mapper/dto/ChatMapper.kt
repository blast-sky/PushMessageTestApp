package com.example.pushmessagetestapp.data.mapper.dto

import com.example.pushmessagetestapp.data.dto.firestore.ChatDto
import com.example.pushmessagetestapp.data.dto.firestore.MessageDto
import com.example.pushmessagetestapp.data.remote.StoreUtil
import com.example.pushmessagetestapp.domain.model.Chat
import com.example.pushmessagetestapp.domain.model.User

suspend fun ChatDto.toChat(storeUtil: StoreUtil) = Chat(
    id = id,
    users = users.map { userId -> storeUtil.getUserById(userId).toUser() },
    messages = storeUtil.getChatMessages(id).map(MessageDto::toMessage)
        .sortedBy { message -> message.created },
)

fun Chat.toDto() = ChatDto(
    id = id,
    users = users.map(User::id),
)
