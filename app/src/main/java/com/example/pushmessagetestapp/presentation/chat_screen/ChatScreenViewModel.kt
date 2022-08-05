package com.example.pushmessagetestapp.presentation.chat_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pushmessagetestapp.data.local.SharedPreferencesUserUtil
import com.example.pushmessagetestapp.domain.interactor.GetChatMessagesInteractor
import com.example.pushmessagetestapp.domain.interactor.SendMessageInteractor
import com.example.pushmessagetestapp.domain.model.Message
import com.example.pushmessagetestapp.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatScreenViewModel @Inject constructor(
    private val getChatMessagesInteractor: GetChatMessagesInteractor,
    private val sendMessageInteractor: SendMessageInteractor,
    sharedPreferencesUtils: SharedPreferencesUserUtil,
) : ViewModel() {

    var messages by mutableStateOf<Resource<List<Message>>>(Resource.Created())

    val userId = sharedPreferencesUtils.userId

    fun loadMessages(chatId: String) = viewModelScope.launch {
        messages = Resource.Loading()
        try {
            getChatMessagesInteractor(chatId).collect { loadedMessages ->
                messages = Resource.Success(loadedMessages.sortedBy { message -> message.created })
            }
        } catch (e: Exception) {
            messages = Resource.Error(message = e.stackTraceToString())
        }
    }

    fun sendMessage(chatId: String, message: String) = viewModelScope.launch {
        sendMessageInteractor(
            chatId = chatId,
            message = message,
            fromId = userId
        )
    }
}