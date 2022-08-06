package com.example.pushmessagetestapp.presentation.chat_list_screen

import android.content.res.Resources
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pushmessagetestapp.R
import com.example.pushmessagetestapp.common.Resource
import com.example.pushmessagetestapp.common.Result
import com.example.pushmessagetestapp.data.local.SharedPreferencesUserUtil
import com.example.pushmessagetestapp.domain.model.Chat
import com.example.pushmessagetestapp.domain.model.User
import com.example.pushmessagetestapp.domain.use_case.CreateChatWithMeUseCase
import com.example.pushmessagetestapp.domain.use_case.GetAvailableUsersUseCase
import com.example.pushmessagetestapp.domain.use_case.GetUserChatsUseCase
import com.example.pushmessagetestapp.presentation.loadFlowableResource
import com.example.pushmessagetestapp.presentation.loadResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatListScreenViewModel @Inject constructor(
    private val resources: Resources,
    private val sharedPreferencesUserUtil: SharedPreferencesUserUtil,
    private val createChatWithMeUseCase: CreateChatWithMeUseCase,
    private val getUserChatsUseCase: GetUserChatsUseCase,
    private val getAvailableUsersUseCase: GetAvailableUsersUseCase,
) : ViewModel() {

    var chats: Resource<List<Chat>> by mutableStateOf(Resource.Loading())
    var availableUsers: Resource<List<User>> by mutableStateOf(Resource.Loading())

    init {
        loadChats()
    }

    fun createChat(chat: Chat) = viewModelScope.launch {
        createChatWithMeUseCase(chat)
    }

    fun loadAvailableUsers() = loadResource(
        errorMessage = resources.getString(R.string.get_available_users_error),
        loader = { getAvailableUsersUseCase() }
    )
        .onEach { availableUsers = it }
        .launchIn(viewModelScope)

    private fun loadChats() = loadFlowableResource(
        errorMessage = "",
        loader = { getUserChatsUseCase(sharedPreferencesUserUtil.userId) },
    )
        .onEach { chats = it }
        .launchIn(viewModelScope)
}