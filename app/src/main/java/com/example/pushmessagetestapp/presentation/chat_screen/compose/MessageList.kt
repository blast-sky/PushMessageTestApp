package com.example.pushmessagetestapp.presentation.chat_screen.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.pushmessagetestapp.R
import com.example.pushmessagetestapp.domain.model.Message

@Composable
fun MessageList(messages: List<Message>, myUserId: String, bottomSize: Dp) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val lazyListState = rememberLazyListState()
    LaunchedEffect(messages) {
        lazyListState.animateScrollToItem(messages.lastIndex)
    }
    LazyColumn(
        state = lazyListState,
        modifier = Modifier.sizeIn(maxHeight = screenHeight - bottomSize)
    ) {
        if (messages.isEmpty())
            item { Text(text = stringResource(R.string.no_messages)) }

        items(messages) { message ->
            MessageListItem(message, myUserId)
        }
    }
}