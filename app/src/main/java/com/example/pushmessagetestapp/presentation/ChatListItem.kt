package com.example.pushmessagetestapp.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pushmessagetestapp.domain.model.Chat

@Composable
fun ChatListItem(chat: Chat) {
    Card(
        modifier = Modifier.height(40.dp),
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(2.dp, MaterialTheme.colors.onSurface)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
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