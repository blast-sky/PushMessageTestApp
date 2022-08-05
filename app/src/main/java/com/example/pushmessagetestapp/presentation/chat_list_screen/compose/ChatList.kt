package com.example.pushmessagetestapp.presentation.chat_list_screen.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.pushmessagetestapp.R
import com.example.pushmessagetestapp.domain.model.Chat
import com.example.pushmessagetestapp.common.Resource

@Composable
fun ChatList(chats: Resource<List<Chat>>, onChatClicked: (String) -> Unit) {

    when (chats) {
        is Resource.Created, is Resource.Loading -> ChatListLoading()
        is Resource.Error -> ChatListError()
        is Resource.Success -> ChatListSuccess(chats.value, onChatClicked)
    }
}

@Composable
private fun ChatListLoading() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ChatListSuccess(chats: List<Chat>, onChatClicked: (String) -> Unit) {
    LazyColumn(modifier = Modifier) {
        if (chats.isEmpty())
            item {
                Text(
                    text = stringResource(R.string.no_chats),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }

        items(chats) { chat ->
            ChatListItem(chat = chat, onChatClicked)
        }
    }
}

@Composable
private fun ChatListError() {
    TODO()
}