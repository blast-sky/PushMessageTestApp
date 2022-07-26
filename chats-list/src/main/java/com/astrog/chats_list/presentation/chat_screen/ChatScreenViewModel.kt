package com.astrog.chats_list.presentation.chat_screen

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.astrog.chats_list.domain.model.Message
import com.astrog.chats_list.domain.repository.Repository
import com.astrog.chats_list.domain.repository.Resources
import com.astrog.chats_list.domain.use_case.GetChatMessagesUseCase
import com.astrog.chats_list.domain.use_case.SendMessageUseCase
import com.astrog.chats_list.presentation.loadFlowableResource
import com.astrog.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatScreenViewModel @Inject constructor(
    private val resources: Resources,
    private val getChatMessagesUseCase: GetChatMessagesUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    repository: Repository, // TODO remove
) : ViewModel() {

    var messages by mutableStateOf<Resource<List<Message>>>(Resource.Loading())
    val userId = repository.userId

    fun loadMessages(chatId: String) = loadFlowableResource(
        errorMessage = resources.loadChatError,
        loader = { getChatMessagesUseCase(chatId) },
    )
        .onEach { messages = it }
        .launchIn(viewModelScope)

    fun sendMessage(chatId: String, message: String, uri: Uri?) = viewModelScope.launch {
        sendMessageUseCase(
            chatId = chatId,
            message = message,
            uri = uri,
        )
    }
}