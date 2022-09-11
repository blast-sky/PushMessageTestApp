package com.example.pushmessagetestapp.presentation.chat_list_screen.compose


import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.astrog.common.Resource
import com.example.pushmessagetestapp.domain.model.Chat
import com.example.pushmessagetestapp.domain.model.User
import com.example.pushmessagetestapp.presentation.chat_list_screen.model.ChatPresenterModel
import com.example.pushmessagetestapp.presentation.common.TopAppBar

@Composable
fun ChatListScreen(
    availableUsers: Resource<List<User>>,
    chats: Resource<List<ChatPresenterModel>>,
    loadAvailableUsers: () -> Unit,
    onChatClicked: (String, String) -> Unit,
    createChat: (chat: Chat) -> Unit
) {
    var chatCreateDialogOpened by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            AddChatFloatingButton {
                chatCreateDialogOpened = true
                loadAvailableUsers.invoke()
            }
        },
        topBar = { TopAppBar(title = "Chats") } // TODO extract resource
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


