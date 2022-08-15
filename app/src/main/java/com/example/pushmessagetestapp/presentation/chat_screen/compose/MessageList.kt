package com.example.pushmessagetestapp.presentation.chat_screen.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.pushmessagetestapp.R
import com.example.pushmessagetestapp.domain.model.Message

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