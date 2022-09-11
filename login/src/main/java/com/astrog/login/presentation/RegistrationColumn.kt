package com.astrog.login.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.astrog.common.Resource
import com.astrog.login.R

@Composable
fun RegistrationColumn(
    registerState: Resource<String>,
    changeRegisterStateIfError: () -> Unit,
    onChangeLoginMode: () -> Unit,
    onRegisterClicked: (name: String, password: String, repeatPassword: String) -> Unit,
) {
    var name by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var repeatPassword by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.registration),
            style = MaterialTheme.typography.h4,
        )

        Spacer(modifier = Modifier.height(16.dp))

        val isError = registerState is Resource.Error
        CustomTextField(
            isError = isError,
            value = name,
            onValueChange = {
                name = it
                changeRegisterStateIfError.invoke()
            },
            label = { Text("Username") }
        )

        Spacer(modifier = Modifier.height(6.dp))

        CustomTextField(
            isError = isError,
            value = password,
            onValueChange = {
                password = it
                changeRegisterStateIfError.invoke()
            },
            label = { Text("Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(6.dp))

        CustomTextField(
            isError = isError,
            value = repeatPassword,
            onValueChange = {
                repeatPassword = it
                changeRegisterStateIfError.invoke()
            },
            label = { Text("Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(6.dp))

        LoginAndChangeModeButton(
            loginLabel = stringResource(R.string.registration),
            changeModeLabel = stringResource(R.string.login),
            state = registerState,
            onLoginClicked = { onRegisterClicked.invoke(name, password, repeatPassword) },
            onChangeLoginMode = {
                changeRegisterStateIfError.invoke()
                onChangeLoginMode.invoke()
            })
    }
}

@Preview
@Composable
private fun RegistrationColumnPreview() {
    RegistrationColumn(Resource.Success("true"), { }, onChangeLoginMode = {}) { _, _, _ -> }
}