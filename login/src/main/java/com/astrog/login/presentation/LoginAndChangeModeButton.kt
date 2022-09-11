package com.astrog.login.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.astrog.common.Resource

@Composable
private fun LoginButton(loginLabel: String, isActive: Boolean, onLoginClicked: () -> Unit) = Button(
    modifier = Modifier,
    enabled = isActive,
    onClick = onLoginClicked
) {
    Text(text = loginLabel)
}

@Composable
fun LoginAndChangeModeButton(
    loginLabel: String,
    changeModeLabel: String,
    state: Resource<String>,
    onLoginClicked: () -> Unit,
    onChangeLoginMode: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .width(280.dp)
    ) {
        when (state) {
            is Resource.Loading -> CircularProgressIndicator()
            is Resource.Error -> LoginButton(loginLabel, false, onLoginClicked)
            is Resource.Success -> LoginButton(loginLabel, true, onLoginClicked)
        }

        Button(
            onClick = onChangeLoginMode,
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface),
            elevation = ButtonDefaults.elevation(defaultElevation = 0.dp)
        ) {
            Text(
                text = changeModeLabel,
                color = Color.Blue,
                style = MaterialTheme.typography.caption,
                textAlign = TextAlign.Center,
            )
        }
    }
}