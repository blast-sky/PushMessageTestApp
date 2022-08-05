package com.example.pushmessagetestapp.presentation.chat_list_screen.compose


import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.pushmessagetestapp.domain.model.Chat
import com.example.pushmessagetestapp.common.Resource

@Composable
fun ChatListScreen(
    chats: Resource<List<Chat>>,
    onFloatingActionButtonClicked: () -> Unit,
    onChatClicked: (String) -> Unit,
) {
    Scaffold(
        floatingActionButton = { AddChatFloatingButton(onFloatingActionButtonClicked = onFloatingActionButtonClicked) },
    ) {
        ChatList(chats, onChatClicked)
    }
}

@Composable
private fun AddChatFloatingButton(onFloatingActionButtonClicked: () -> Unit) {
    FloatingActionButton(
        onClick = onFloatingActionButtonClicked,
        backgroundColor = MaterialTheme.colors.secondary,
    ) {
        Icon(
            modifier = Modifier.size(40.dp),
            imageVector = Icons.Default.Add,
            contentDescription = "",
            tint = Color.White
        )
    }
}


