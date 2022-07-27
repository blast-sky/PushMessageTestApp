package com.example.pushmessagetestapp.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SignIn(onCLick: (String) -> Unit) {
    val updatedOnClick by rememberUpdatedState(newValue = onCLick)
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

        Button(onClick = { updatedOnClick.invoke(name) }) {
            Text(text = "SignIn")
        }
    }
}

@Preview(
    name = "SignIn",
    showSystemUi = true,
    showBackground = true
)
@Composable
fun PreviewSignIn() {
    SignIn {}
}