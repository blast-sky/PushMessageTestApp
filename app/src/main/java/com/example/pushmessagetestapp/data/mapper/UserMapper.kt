package com.example.pushmessagetestapp.data.mapper

import com.example.pushmessagetestapp.data.dto.UserDto
import com.example.pushmessagetestapp.domain.model.User

fun User.toUserDto() = UserDto(
    name = name,
    messageToken = messageToken
)
