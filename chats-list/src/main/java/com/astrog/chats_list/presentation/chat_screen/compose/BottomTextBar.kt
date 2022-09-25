package com.astrog.chats_list.presentation.chat_screen.compose

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.astrog.chats_list.R

@Composable
fun BottomTextBar(onMessageButtonClick: (String, Uri?) -> Unit) {
    var message by rememberSaveable { mutableStateOf(String()) }
    var selectedImage: Uri? by remember { mutableStateOf(null) }

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
                Box(
                    modifier = Modifier
                    .size(46.dp)
                    .clickable(onClick = {
                        onMessageButtonClick(message, selectedImage)
                        message = ""
                    }),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = stringResource(R.string.send_message),
                        tint = MaterialTheme.colors.onSurface,
                        modifier = Modifier
                            .fillMaxSize(0.75f)
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences,
            ),
            leadingIcon = {
                val launcher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.GetContent()
                ) { uri -> selectedImage = uri }
                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .clickable(onClick = {
                            launcher.launch("image/*")
                        }),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = stringResource(R.string.add_image),
                        tint = MaterialTheme.colors.onSurface,
                        modifier = Modifier
                            .fillMaxSize(0.75f)
                    )
                }
            }
        )
    }
}

@Preview("BottomTextBarPreview")
@Composable
fun BottomTextBarPreview() {
    BottomTextBar { _, _ -> }
}

