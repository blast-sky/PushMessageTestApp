package com.example.pushmessagetestapp.presentation.chat_screen.compose


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pushmessagetestapp.domain.model.Message
import com.example.pushmessagetestapp.common.Resource

@Composable
fun ChatScreen(
    chatId: String,
    myUserId: String,
    messages: Resource<List<Message>>,
    loadMessage: (chatId: String) -> Unit,
    sendMessage: (chatId: String, message: String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        LaunchedEffect(Unit) {
            loadMessage(chatId)
        }

        val bottomSize = remember { mutableStateOf(20.dp) }

        when (messages) {
            is Resource.Created, is Resource.Loading -> ChatScreenLoading()
            is Resource.Error -> TODO()
            is Resource.Success -> MessageList(
                messages = messages.value,
                myUserId = myUserId,
                bottomSize.value
            )
        }

        BottomTextBar(bottomSize) { message -> sendMessage(chatId, message) }
    }
}

@Composable
private fun ChatScreenLoading() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}