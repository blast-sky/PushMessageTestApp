package com.example.pushmessagetestapp.data.mapper.dto

import com.example.pushmessagetestapp.data.dto.firestore.UserDto
import com.example.pushmessagetestapp.domain.model.User

fun User.toDto() = UserDto(
    name = name,
    messageToken = messageToken,
)

fun UserDto.toUser() = User(
    id = id,
    messageToken = messageToken,
    name = name.ifEmpty { "Empty name user" },
)
