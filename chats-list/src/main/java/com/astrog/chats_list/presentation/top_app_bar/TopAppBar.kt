package com.astrog.chats_list.presentation.top_app_bar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private val height = 64.dp
typealias Action = () -> Unit

@Composable
private fun TopAppBarText(title: String, modifier: Modifier = Modifier) = Text(
    text = title,
    style = MaterialTheme.typography.h5,
    modifier = modifier,
    color = MaterialTheme.colors.onPrimary,
)

@Composable
fun TopAppBar(
    title: String = "",
    goBackButton: @Composable Action? = null,
    menu: @Composable Action? = null
) {
    val roundedCorner = 16.dp
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .clip(RoundedCornerShape(bottomStart = roundedCorner, bottomEnd = roundedCorner))
            .background(MaterialTheme.colors.primary),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val textPaddingValues = remember {
            derivedStateOf {
                PaddingValues(
                    horizontal = if (goBackButton == null) 16.dp else 6.dp,
                    vertical = 6.dp
                )
            }
        }

        goBackButton?.invoke()

        TopAppBarText(
            title = title,
            modifier = Modifier.padding(textPaddingValues.value)
        )

        Spacer(modifier = Modifier.weight(1f))

        menu?.invoke()
    }
}

@Composable
fun GoBackTopAppBar(title: String, goBack: () -> Unit) =
    TopAppBar(
        title = title,
        goBackButton = { TopAppBarGoBackButton(goBack) }
    )

@Preview("TopAppBar")
@Composable
fun TopAppBarPreview() {
    TopAppBar("test")
}