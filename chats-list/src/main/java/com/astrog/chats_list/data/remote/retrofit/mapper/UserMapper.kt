package com.astrog.chats_list.data.remote.retrofit.mapper

import com.astrog.chats_list.domain.model.User
import com.astrog.firestorecommon.dto.UserDto

fun User.toDto() = UserDto(
    name = name,
    messageToken = messageToken,
)

fun UserDto.toUser() = User(
    id = id,
    messageToken = messageToken,
    name = name.ifEmpty { "Empty name user" },
)
