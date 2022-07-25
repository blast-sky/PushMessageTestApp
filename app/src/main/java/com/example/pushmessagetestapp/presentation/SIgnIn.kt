package com.example.pushmessagetestapp.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SignIn(onCLick: () -> Unit) {
    val updatedOnClick by rememberUpdatedState(newValue = onCLick)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = updatedOnClick) {
            Text(text = "SignIn")
        }
    }
}

@Preview(name = "SignIn")
@Composable
fun PreviewSignIn() {
    SignIn {}
}