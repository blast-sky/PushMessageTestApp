package com.astrog.chats_list.domain.model

import java.util.*

data class Message(
    val id: String = "",
    val created: Date = Date(),
    val from: String = "",
    val message: String = "",
)
