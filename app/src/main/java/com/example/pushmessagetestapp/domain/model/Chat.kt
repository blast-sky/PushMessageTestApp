package com.example.pushmessagetestapp.domain.model

data class Chat(
    val users: List<User>,
    val messages: List<Message>
)
