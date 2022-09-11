package com.example.pushmessagetestapp.presentation.chat_list_screen.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.astrog.common.Resource
import com.example.pushmessagetestapp.R
import com.example.pushmessagetestapp.domain.model.Chat
import com.example.pushmessagetestapp.domain.model.User
import com.example.pushmessagetestapp.presentation.PreviewModels
import com.example.pushmessagetestapp.presentation.ui.theme.lightBlue

@Composable
fun ChatCreateDialog(
    users: Resource<List<User>>,
    closeSelf: () -> Unit,
    createChat: (chat: Chat) -> Unit,
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    var selectedIndex by rememberSaveable { mutableStateOf(-1) }

    Dialog(onDismissRequest = closeSelf) {
        Card(modifier = Modifier) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(18.dp),
            ) {
                when (users) {
                    is Resource.Error -> TODO()
                    is Resource.Loading -> CircularProgressIndicator(modifier = Modifier.padding(6.dp))
                    is Resource.Success -> ChatCreateDialogSuccess(
                        users = users.value,
                        selectedIndex = selectedIndex,
                        expanded = expanded,
                        changeSelectedIndex = { selectedIndex = it },
                        setExpanded = { expanded = it }
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 70.dp),
                ) {
                    TextButton(onClick = closeSelf) {
                        Text(
                            text = "Cancel",
                            style = MaterialTheme.typography.subtitle1
                        )
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
                        Text(
                            text = "Ok",
                            style = MaterialTheme.typography.subtitle1
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
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
        Card(backgroundColor = colors.primary, onClick = { setExpanded(true) }) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp)
                    .background(color = colors.primary),
                text = users.getOrNull(selectedIndex)?.name
                    ?: "Select user", // TODO extract resource
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.subtitle1,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
        }


        DropdownMenu(
            modifier = Modifier,
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
                    colors.lightBlue
                else
                    colors.surface

                DropdownMenuItem(
                    onClick = {
                        changeSelectedIndex.invoke(index)
                        setExpanded.invoke(false)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(backgroundColor)
                ) {
                    Text(text = user.name)
                }
            }
        }
    }
}

@Preview("ChatCreateDialogLoading")
@Composable
fun ChatCreateDialogLoadingPreview() {
    ChatCreateDialog(Resource.Loading(), {}, {})
}

@Preview("ChatCreateDialogSuccessClosed")
@Composable
fun ChatCreateDialogSuccessClosedPreview() {
    val users = PreviewModels.userList
    ChatCreateDialog(Resource.Success(users), {}, {})
}

@Preview("ChatCreateDialogSuccessOpened")
@Composable
fun ChatCreateDialogSuccessOpenedPreview() {
    val users = PreviewModels.userList
    ChatCreateDialogSuccess(
        users = users,
        selectedIndex = -1,
        expanded = true,
        changeSelectedIndex = {},
        setExpanded = {},
    )
}