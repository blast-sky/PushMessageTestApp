package com.example.pushmessagetestapp.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private val height = 50.dp


@Composable
private fun TopAppBarRow(content: @Composable RowScope.() -> Unit) = Row(
    modifier = Modifier
        .fillMaxWidth()
        .height(height)
        .background(MaterialTheme.colors.primary),
    verticalAlignment = Alignment.CenterVertically,
    content = content,
)

@Composable
private fun TopAppBarText(title: String, modifier: Modifier = Modifier) = Text(
    text = title,
    style = MaterialTheme.typography.h5,
    modifier = modifier,
    color = MaterialTheme.colors.onPrimary,
)

@Composable
fun TopAppBar(title: String) {
    TopAppBarRow {
        TopAppBarText(
            title = title,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
        )
    }
}

@Composable
fun GoBackTopAppBar(title: String, goBack: () -> Unit) {
    TopAppBarRow {
        IconButton(onClick = goBack) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "Go back", // TODO extract resource
                tint = MaterialTheme.colors.onPrimary,
            )
        }
        TopAppBarText(
            title = title,
            modifier = Modifier.padding(6.dp)
        )
    }
}

@Preview("TopAppBar")
@Composable
fun TopAppBarPreview() {
    TopAppBar("test")
}