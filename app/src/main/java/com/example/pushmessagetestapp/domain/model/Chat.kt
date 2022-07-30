package com.example.pushmessagetestapp.domain.model

data class Chat(
    val id: String,
    val users: List<User>,
    val messages: List<Message>
)
