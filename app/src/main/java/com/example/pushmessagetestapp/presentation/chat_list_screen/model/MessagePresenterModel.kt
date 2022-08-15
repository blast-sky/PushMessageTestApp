package com.example.pushmessagetestapp.presentation.chat_list_screen.model

data class MessagePresenterModel(
    val from: String = "",
    val message: String = "",
    val date: String = "",
    val isViewed: Boolean = true,
)
