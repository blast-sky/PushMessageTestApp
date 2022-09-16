package com.astrog.chats_list.data.remote.retrofit.mapper

import com.astrog.chats_list.data.remote.StoreUtil
import com.astrog.chats_list.domain.model.Chat
import com.astrog.chats_list.domain.model.User
import com.astrog.firestorecommon.dto.ChatDto
import com.astrog.firestorecommon.dto.MessageDto

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
