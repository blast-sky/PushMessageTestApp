package com.example.pushmessagetestapp.presentation.chat_list_screen.model

data class ChatPresenterModel(
    val title: String,
    val lastMessage: MessagePresenterModel,
    val id: String,
)
