package com.astrog.chats_list.presentation.chat_screen.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.astrog.chats_list.R

@Composable
fun BottomTextBar(onMessageButtonClick: (String) -> Unit) {
    var message by rememberSaveable { mutableStateOf("") }

    Card(elevation = 4.dp) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 46.dp),
            value = message,
            onValueChange = { message = it },
            placeholder = {
                Text(text = stringResource(R.string.enter_message))
            },
            maxLines = 3,
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colors.onPrimary,
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                backgroundColor = MaterialTheme.colors.surface,
                cursorColor = MaterialTheme.colors.onSurface,
            ),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "message button",
                    tint = MaterialTheme.colors.onSurface,
                    modifier = Modifier
                        .size(46.dp)
                        .clickable(onClick = {
                            onMessageButtonClick(message)
                            message = ""
                        })
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences,
            )
        )
    }
}

@Preview("BottomTextBarPreview")
@Composable
fun BottomTextBarPreview() {
    BottomTextBar {}
}

