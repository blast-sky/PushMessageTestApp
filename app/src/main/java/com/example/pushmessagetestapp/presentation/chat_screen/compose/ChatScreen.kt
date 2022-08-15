package com.example.pushmessagetestapp.presentation.chat_screen.compose


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pushmessagetestapp.domain.model.Message
import com.example.pushmessagetestapp.common.Resource
import com.example.pushmessagetestapp.presentation.PreviewModels
import com.example.pushmessagetestapp.presentation.common.GoBackTopAppBar

@Composable
fun ChatScreen(
    chatId: String,
    chatTitle: String,
    myUserId: String,
    messages: Resource<List<Message>>,
    goBack: () -> Unit,
    loadMessage: (chatId: String) -> Unit,
    sendMessage: (chatId: String, message: String) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { GoBackTopAppBar(title = chatTitle, goBack = goBack) }, // TODO rename
        bottomBar = { BottomTextBar { message -> sendMessage(chatId, message) } }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            LaunchedEffect(Unit) {
                loadMessage(chatId)
            }

            when (messages) {
                is Resource.Loading -> ChatScreenLoading()
                is Resource.Error -> TODO()
                is Resource.Success -> MessageList(
                    messages = messages.value,
                    myUserId = myUserId,
                    modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding())
                )
            }
        }
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

@Preview
@Composable
private fun ChatScreenPreview() {
    ChatScreen(
        chatId = PreviewModels.chatId,
        chatTitle = "",
        myUserId = PreviewModels.userId,
        messages = Resource.Success(PreviewModels.messages),
        goBack = {},
        loadMessage = {},
        sendMessage = { _, _ -> },
    )
}
