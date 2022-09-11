package com.example.pushmessagetestapp.presentation.chat_list_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.astrog.common.Resource
import com.astrog.common.map
import com.example.pushmessagetestapp.domain.model.Chat
import com.example.pushmessagetestapp.domain.model.User
import com.example.pushmessagetestapp.domain.repository.Repository
import com.example.pushmessagetestapp.domain.repository.Resources
import com.example.pushmessagetestapp.domain.use_case.CreateChatWithMeUseCase
import com.example.pushmessagetestapp.domain.use_case.GetAvailableUsersForNewChatUseCase
import com.example.pushmessagetestapp.domain.use_case.GetUserChatsUseCase
import com.example.pushmessagetestapp.presentation.chat_list_screen.mapper.toChatPresenterModel
import com.example.pushmessagetestapp.presentation.chat_list_screen.model.ChatPresenterModel
import com.example.pushmessagetestapp.presentation.loadFlowableResource
import com.example.pushmessagetestapp.presentation.loadResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatListScreenViewModel @Inject constructor(
    private val resources: Resources,
    private val repository: Repository,
    private val createChatWithMeUseCase: CreateChatWithMeUseCase,
    private val getUserChatsUseCase: GetUserChatsUseCase,
    private val getAvailableUsersForNewChatUseCase: GetAvailableUsersForNewChatUseCase,
) : ViewModel() {

    var chats: Resource<List<ChatPresenterModel>> by mutableStateOf(Resource.Loading())
    var availableUsers: Resource<List<User>> by mutableStateOf(Resource.Loading())

    init {
        loadChats()
    }

    fun createChat(chat: Chat) = viewModelScope.launch {
        createChatWithMeUseCase.invoke(chat)
    }

    fun loadAvailableUsers() = loadResource(
        errorMessage = resources.loadAvailableUsersError,
        loader = getAvailableUsersForNewChatUseCase::invoke
    )
        .onEach { availableUsers = it }
        .launchIn(viewModelScope)

    private fun loadChats() = loadFlowableResource(
        errorMessage = resources.loadChatError,
        loader = { getUserChatsUseCase.invoke() },
    )
        .onEach { chatResource ->
            chats = chatResource.map { chatList ->
                chatList.map { chat ->
                    chat.toChatPresenterModel(repository.myUser)
                }
            }
        }
        .launchIn(viewModelScope)
}