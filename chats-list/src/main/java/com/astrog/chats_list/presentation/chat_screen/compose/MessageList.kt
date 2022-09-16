package com.astrog.chats_list.presentation.chat_screen.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.astrog.chats_list.R
import com.astrog.chats_list.domain.model.Message

@Composable
fun MessageList(messages: List<Message>, myUserId: String, modifier: Modifier = Modifier) {
    val lazyListState = rememberLazyListState()

    LaunchedEffect(messages) {
        messages.lastIndex.takeIf { it != -1 }?.let { index ->
            lazyListState.animateScrollToItem(index)
        }
    }

    LazyColumn(
        state = lazyListState,
        modifier = modifier.fillMaxSize()
    ) {
        if (messages.isEmpty()) {
            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp)
                ) {
                    Text(text = stringResource(R.string.no_messages))
                }
            }
        }

        items(messages) { message ->
            MessageListItem(message, myUserId)
        }
    }
}