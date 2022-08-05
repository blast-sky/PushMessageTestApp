package com.example.pushmessagetestapp.presentation.chat_list_screen.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pushmessagetestapp.domain.model.Chat
import com.example.pushmessagetestapp.presentation.PreviewModels

@Suppress("OPT_IN_IS_NOT_ENABLED")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChatListItem(chat: Chat, onChatClicked: (String) -> Unit) {
    Card(
        modifier = Modifier
            .defaultMinSize(minHeight = 70.dp)
            .padding(6.dp),
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(2.dp, MaterialTheme.colors.onSurface),
        onClick = { onChatClicked(chat.id) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val user =
                if (chat.users.size == 1) chat.users[0] else chat.users.first { true } // TODO first not me
            Text(text = user.name)
            val lastMessage = chat.messages.lastOrNull()?.message ?: ""
            Text(text = lastMessage)
        }
    }
}

@Preview(name = "ChatListItem")
@Composable
private fun ChatListItemPreview() {
    val chat = PreviewModels.chat
    ChatListItem(chat = chat) {}
}