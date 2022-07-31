package com.example.pushmessagetestapp.presentation.chat_screen.compose

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.pushmessagetestapp.presentation.PreviewModels
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun MessageDateStump(date: Date) {
    val formattedDate = SimpleDateFormat("dd MMM  HH:mm").format(date)
    Text(
        text = formattedDate,
        style = MaterialTheme.typography.subtitle2,
        color = Color.Gray
    ) // TODO different format
}

@Preview(name = "MessageDateStumpPreview", showBackground = true)
@Composable
private fun MessageDateStumpPreview() {
    val date = PreviewModels.date
    MessageDateStump(date = date)
}