package com.astrog.chats_list.presentation.top_app_bar

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.astrog.chats_list.R

@Composable
fun TopAppBarGoBackButton(action: Action) {
    IconButton(onClick = action) {
        Icon(
            imageVector = Icons.Default.KeyboardArrowLeft,
            contentDescription = stringResource(R.string.go_back),
            tint = MaterialTheme.colors.onPrimary,
        )
    }
}