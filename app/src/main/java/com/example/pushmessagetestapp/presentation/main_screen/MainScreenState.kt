package com.example.pushmessagetestapp.presentation.main_screen

import com.example.pushmessagetestapp.domain.model.Chat
import com.example.pushmessagetestapp.util.Resource

data class MainScreenState(
    val isLogin: Boolean = false,
    val chats: Resource<List<Chat>> = Resource.Created(),
)
