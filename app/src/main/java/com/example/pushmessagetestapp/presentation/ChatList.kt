package com.example.pushmessagetestapp.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.pushmessagetestapp.R
import com.example.pushmessagetestapp.domain.model.Chat
import com.example.pushmessagetestapp.util.Resource

@Composable
fun ChatList(chats: Resource<List<Chat>>) {

    when(chats) {
        is Resource.Created, is Resource.Loading -> ChatListLoading()
        is Resource.Error -> ChatListError()
        is Resource.Success -> ChatListSuccess(chats.value)
    }
}

@Composable
fun ChatListLoading() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ChatListSuccess(chats: List<Chat>) {
    LazyColumn(modifier = Modifier) {
        if(chats.isEmpty())
            item {
                Text(text = stringResource(R.string.no_chats))
            }

        items(chats) { chat ->
            ChatListItem(chat = chat)
        }
    }
}

@Composable
private fun ChatListError() {
    TODO()
}