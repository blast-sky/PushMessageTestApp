package com.astrog.chats_list.presentation.top_app_bar

import androidx.compose.foundation.layout.Box
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import com.astrog.chats_list.R

data class MenuItem(val title: String, val action: Action)

@Composable
fun TopAppBarMenu(items: List<MenuItem>) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        IconButton(onClick = { expanded = true }) {
            Icon(
                Icons.Default.MoreVert,
                contentDescription = stringResource(R.string.show_menu),
                tint = MaterialTheme.colors.onPrimary,
            )
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            items.forEach { item ->
                DropdownMenuItem(onClick = item.action) {
                    Text(text = item.title)
                }
            }
        }
    }
}