package com.astrog.chats_list.domain.model

data class Chat(
    val id: String = "",
    val users: List<User> = emptyList(),
    val messages: List<Message> = emptyList(),
)
