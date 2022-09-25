package com.astrog.chats_list.presentation.chat_screen.compose


import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.astrog.chats_list.domain.model.Message
import com.astrog.chats_list.presentation.PreviewModels
import com.astrog.chats_list.presentation.chat_screen.ChatScreenViewModel
import com.astrog.chats_list.presentation.top_app_bar.GoBackTopAppBar
import com.astrog.common.Resource

@Composable
fun ChatScreen(
    viewModel: ChatScreenViewModel = hiltViewModel(),
    chatId: String,
    chatTitle: String,
    goBack: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { GoBackTopAppBar(title = chatTitle, goBack = goBack) },
        bottomBar = { BottomTextBar { message, uri -> viewModel.sendMessage(chatId, message, uri) } }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            LaunchedEffect(Unit) {
                viewModel.loadMessages(chatId)
            }

            when (val messages = viewModel.messages) {
                is Resource.Loading -> ChatScreenLoading()
                is Resource.Error -> TODO()
                is Resource.Success -> MessageList(
                    messages = messages.value,
                    myUserId = viewModel.userId,
                    modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding())
                )
            }
        }
    }
}

@Composable
private fun ChatScreenLoading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
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
        goBack = {},
    )
}
