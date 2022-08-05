package com.example.pushmessagetestapp.presentation.login_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pushmessagetestapp.common.Resource

@Composable
fun LoginScreen(
    isLogin: Resource<Boolean>,
    onLoginClicked: (String) -> Unit,
    onLoginSuccess: () -> Unit
) {
    when (isLogin) {
        is Resource.Created, is Resource.Loading -> CircularProgressIndicator()
        is Resource.Error -> TODO()
        is Resource.Success -> {
            if (isLogin.value) {
                onLoginSuccess.invoke()
            } else {
                var name by rememberSaveable { mutableStateOf("") }

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    TextField(
                        value = name, onValueChange = { name = it },
                        label = { Text("Enter name") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(onClick = { onLoginClicked.invoke(name) }) {
                        Text(text = "SignIn")
                    }
                }
            }
        }
    }
}

@Preview(
    name = "LoginScreen",
    showSystemUi = true,
    showBackground = true
)
@Composable
private fun LoginScreenPreview() {
    LoginScreen(Resource.Success(false), {}, {})
}