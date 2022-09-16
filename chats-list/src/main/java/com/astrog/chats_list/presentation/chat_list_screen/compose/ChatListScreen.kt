package com.astrog.chats_list.presentation.chat_list_screen.compose


import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.astrog.chats_list.R
import com.astrog.chats_list.domain.model.Chat
import com.astrog.chats_list.domain.model.User
import com.astrog.chats_list.presentation.chat_list_screen.ChatListScreenViewModel
import com.astrog.chats_list.presentation.top_app_bar.Action
import com.astrog.chats_list.presentation.top_app_bar.TopAppBar
import com.astrog.chats_list.presentation.top_app_bar.MenuItem
import com.astrog.chats_list.presentation.top_app_bar.TopAppBarMenu
import com.astrog.common.Resource
import com.example.pushmessagetestapp.presentation.chat_list_screen.model.ChatPresenterModel

@Composable
fun ChatListScreen(
    viewModel: ChatListScreenViewModel = hiltViewModel(),
    goToLoginScreen: Action,
    onChatClicked: (String, String) -> Unit,
) {
    var chatCreateDialogOpened by rememberSaveable { mutableStateOf(false) }
    val menuItems = listOf(MenuItem(title = "Logout", action = {
        viewModel.unLogin()
        goToLoginScreen.invoke()
    }))

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                viewModel.loadChats()
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Scaffold(
        floatingActionButton = {
            AddChatFloatingButton {
                chatCreateDialogOpened = true
                viewModel.loadAvailableUsers()
            }
        },
        topBar = {
            TopAppBar(
                title = stringResource(R.string.chats),
                menu = { TopAppBarMenu(items = menuItems) })
        }
    ) {
        if (chatCreateDialogOpened) {
            ChatCreateDialog(
                users = viewModel.availableUsers,
                closeSelf = { chatCreateDialogOpened = false },
                createChat = viewModel::createChat,
            )
        }
        ChatList(viewModel.chats, onChatClicked)
    }
}

@Composable
private fun AddChatFloatingButton(onFloatingActionButtonClicked: () -> Unit) {
    FloatingActionButton(
        onClick = onFloatingActionButtonClicked,
        backgroundColor = MaterialTheme.colors.primary,
    ) {
        Icon(
            modifier = Modifier.size(40.dp),
            imageVector = Icons.Default.Add,
            contentDescription = "",
            tint = Color.White
        )
    }
}


