package com.example.pushmessagetestapp.presentation.chat_screen.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pushmessagetestapp.R

@Composable
fun BottomTextBar(onMessageButtonClick: (String) -> Unit) {
    var message by rememberSaveable { mutableStateOf("") }
    Card(
        shape = MaterialTheme.shapes.large,
        backgroundColor = MaterialTheme.colors.background,
        elevation = 10.dp,
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 46.dp)
                .background(color = MaterialTheme.colors.surface),
            value = message,
            onValueChange = { message = it },
            placeholder = {
                Text(text = stringResource(R.string.enter_message))
            },
            maxLines = 3,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Gray,
                disabledTextColor = Color.Transparent,
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
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
            }
        )
    }
}

@Preview("BottomTextBarPreview")
@Composable
fun BottomTextBarPreview() {
    BottomTextBar {}
}

