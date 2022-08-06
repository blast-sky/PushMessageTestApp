package com.example.pushmessagetestapp.presentation.chat_list_screen.compose


import android.icu.util.UniversalTimeScale
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.pushmessagetestapp.domain.model.Chat
import com.example.pushmessagetestapp.common.Resource
import com.example.pushmessagetestapp.domain.model.User

@Composable
fun ChatListScreen(
    availableUsers: Resource<List<User>>,
    chats: Resource<List<Chat>>,
    loadAvailableUsers: () -> Unit,
    onChatClicked: (String) -> Unit,
    createChat: (chat: Chat) -> Unit
) {
    var chatCreateDialogOpened by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = { AddChatFloatingButton {
            chatCreateDialogOpened = true
            loadAvailableUsers.invoke()
        } },
    ) {
        if (chatCreateDialogOpened) {
            ChatCreateDialog(
                users = availableUsers,
                closeSelf = { chatCreateDialogOpened = false },
                createChat = createChat,
            )
        }
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


