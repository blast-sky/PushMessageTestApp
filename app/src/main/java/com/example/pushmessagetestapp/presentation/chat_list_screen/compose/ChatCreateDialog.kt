package com.example.pushmessagetestapp.presentation.chat_list_screen.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.pushmessagetestapp.R
import com.example.pushmessagetestapp.common.Resource
import com.example.pushmessagetestapp.domain.model.Chat
import com.example.pushmessagetestapp.domain.model.User

@Composable
fun ChatCreateDialog(
    users: Resource<List<User>>,
    closeSelf: () -> Unit,
    createChat: (chat: Chat) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(-1) }

    Dialog(onDismissRequest = closeSelf) {
        Card(modifier = Modifier.height(300.dp)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(6.dp),
            ) {
                when (users) {
                    is Resource.Error -> TODO()
                    is Resource.Loading -> CircularProgressIndicator()
                    is Resource.Success -> ChatCreateDialogSuccess(
                        users = users.value,
                        selectedIndex = selectedIndex,
                        expanded = expanded,
                        changeSelectedIndex = { selectedIndex = it },
                        setExpanded = { expanded = it }
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(onClick = closeSelf) {
                        Text(text = "CANCEL")
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    TextButton(onClick = {
                        (users as? Resource.Success)
                            ?.value
                            ?.getOrNull(selectedIndex)
                            ?.let { selectedUser ->
                                val chat = Chat(users = listOf(selectedUser))
                                createChat.invoke(chat)
                            }

                        closeSelf.invoke()
                    }) {
                        Text(text = "OK")
                    }
                }
            }
        }
    }
}

@Composable
private fun ChatCreateDialogSuccess(
    users: List<User>,
    selectedIndex: Int,
    expanded: Boolean,
    changeSelectedIndex: (Int) -> Unit,
    setExpanded: (Boolean) -> Unit,
) {
    Column(
        modifier = Modifier.padding(6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { setExpanded(true) }
                .background(color = Color.LightGray),
            text = users.getOrNull(selectedIndex)?.name ?: "Select user\nPress there",
            textAlign = TextAlign.Center,
        )

        DropdownMenu(
            modifier = Modifier.fillMaxWidth(),
            expanded = expanded,
            onDismissRequest = { setExpanded.invoke(false) },
        ) {
            if (users.isEmpty()) {
                DropdownMenuItem(onClick = { }) {
                    Text(text = stringResource(R.string.no_available_users))
                }
            }

            users.forEachIndexed { index, user ->
                val backgroundColor = if (index == selectedIndex)
                    MaterialTheme.colors.secondaryVariant
                else
                    MaterialTheme.colors.surface

                DropdownMenuItem(
                    onClick = {
                        changeSelectedIndex.invoke(index)
                        setExpanded.invoke(false)
                    },
                    modifier = Modifier.background(backgroundColor)
                ) {
                    Text(text = user.name)
                }
            }
        }
    }
}