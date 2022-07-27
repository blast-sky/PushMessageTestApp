package com.example.pushmessagetestapp.data.dto

import kotlinx.serialization.SerialName

data class ChatDto(
    @SerialName("users") val users: List<String> = emptyList(),
)
