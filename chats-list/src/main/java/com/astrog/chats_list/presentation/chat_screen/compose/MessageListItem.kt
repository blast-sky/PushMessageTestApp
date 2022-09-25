package com.astrog.chats_list.presentation.chat_screen.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.astrog.chats_list.domain.model.Message
import com.astrog.chats_list.presentation.PreviewModels
import com.bumptech.glide.Glide
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun MessageListItem(message: Message, myUserId: String) {
    val arrangement =
        if (message.from == myUserId)
            Arrangement.End
        else
            Arrangement.Start
    val startPadding = 40.dp
    val paddingModifier =
        if (message.from == myUserId)
            Modifier.padding(start = startPadding)
        else
            Modifier.padding(end = startPadding)
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = arrangement) {
        Card(
            modifier = Modifier
                .padding(6.dp)
                .then(paddingModifier),
            elevation = 6.dp,
            backgroundColor = MaterialTheme.colors.surface
        ) {
            Column(modifier = Modifier.padding(6.dp)) {
                Text(text = message.message, color = MaterialTheme.colors.onSurface)
                if (message.image.isNotBlank()) {
                    GlideImage(
                        modifier = Modifier.size(256.dp),
                        imageModel = message.image,
                        loading = {
                            Box(modifier = Modifier.matchParentSize()) {
                                CircularProgressIndicator(
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        },
                    )
                }
                MessageDateStump(date = message.created)
            }
        }
    }
}

@Preview(name = "MessageListItemPreview", showBackground = true)
@Composable
private fun MessageListItemPreview() {
    val message = PreviewModels.message
    val longMessage = message.copy(message = "1231231212".repeat(30))
    MessageListItem(message = longMessage, myUserId = message.from + "1")
}