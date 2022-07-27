package com.example.pushmessagetestapp.data.dto

import kotlinx.serialization.SerialName

data class UserDto(
    @SerialName("name") val name: String = "",
    @SerialName("messageToken") val messageToken: String = "",
)
