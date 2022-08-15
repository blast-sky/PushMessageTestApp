package com.example.pushmessagetestapp.presentation.chat_list_screen.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pushmessagetestapp.presentation.PreviewModels
import com.example.pushmessagetestapp.presentation.chat_list_screen.model.ChatPresenterModel
import com.example.pushmessagetestapp.presentation.ui.theme.lightGray

@Suppress("OPT_IN_IS_NOT_ENABLED")
@Composable
fun ChatListItem(chat: ChatPresenterModel, onChatClicked: (String, String) -> Unit) {
    Column(
        modifier = Modifier
            .defaultMinSize(minHeight = 70.dp)
            .clickable { onChatClicked(chat.id, chat.title) }
            .padding(6.dp),
    ) {
        val lastMessage = chat.lastMessage
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = chat.title, style = MaterialTheme.typography.h6)
            Text(
                text = lastMessage.date,
                style = MaterialTheme.typography.subtitle2,
                color = MaterialTheme.colors.lightGray
            )
        }
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("${lastMessage.from}: ")
                }
                append(lastMessage.message)
            },
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.lightGray,
            modifier = Modifier.padding(bottom = 6.dp)
        )
    }
    Divider(
        color = MaterialTheme.colors.lightGray,
        thickness = 1.dp,
        startIndent = 0.dp,
        modifier = Modifier.height(1.dp)
    )
}

@Preview(name = "ChatListItem", showSystemUi = true)
@Composable
private fun ChatListItemPreview() {
    val chat = PreviewModels.chatPresenterModel
    ChatListItem(chat = chat) { _, _ -> }
}