package com.example.pushmessagetestapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.pushmessagetestapp.domain.model.Chat
import com.example.pushmessagetestapp.domain.model.User
import com.example.pushmessagetestapp.presentation.ui.theme.lightBlue

@Composable
fun ChatCreateDialog(
    users: List<User>,
    closeSelf: () -> Unit,
    createChat: (chat: Chat) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(-1) }

    Dialog(onDismissRequest = closeSelf) {
        Column {
            Text(text = users.getOrNull(selectedIndex)?.name ?: "")

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                users.forEachIndexed { index, user ->
                    val backgroundColor = if (index == selectedIndex)
                        MaterialTheme.colors.lightBlue
                    else
                        MaterialTheme.colors.onSurface

                    DropdownMenuItem(
                        onClick = {
                            selectedIndex = index
                            expanded = false
                        },
                        modifier = Modifier.background(backgroundColor)
                    ) {
                        Text(text = user.name)
                    }
                }
            }

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(onClick = closeSelf) {
                    Text(text = "CANCEL")
                }
                Spacer(modifier = Modifier.width(4.dp))
                TextButton(onClick = {
                    val selectedUser = users[selectedIndex]
                    val chat = Chat(users = listOf(selectedUser))
                    createChat.invoke(chat)
                    closeSelf.invoke()
                }) {
                    Text(text = "OK")
                }
            }
        }
    }
}