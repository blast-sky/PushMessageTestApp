package com.astrog.login.presentation

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.astrog.common.Resource

@Composable
fun LoginScreen(
    viewModel: LoginScreenViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit,
) {
    val isLogin = viewModel.isLogin

    if (isLogin is Resource.Success && isLogin.value) {
        val context = LocalContext.current
        LaunchedEffect(context) { onLoginSuccess.invoke() }
        return
    }

    var isRegistration by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current
    LaunchedEffect(viewModel.loginState, viewModel.registerState) {
        var text = ""

        val login = viewModel.loginState
        if (login is Resource.Error) {
            text = login.message
        }

        val register = viewModel.registerState
        if (register is Resource.Error) {
            text = register.message
        }

        if (text.isNotBlank())
            Toast.makeText(context, text, Toast.LENGTH_LONG).show()
    }

    when (isLogin) {
        is Resource.Loading -> CircularProgressIndicator()
        is Resource.Error -> Text(text = isLogin.message)
        is Resource.Success -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center,
            ) {
                Card(
                    modifier = Modifier
                        .animateContentSize(spring(Spring.DampingRatioMediumBouncy))
                        .padding(16.dp),
                    elevation = 6.dp
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.padding(16.dp)
                    ) {
                        if (isRegistration) {
                            RegistrationColumn(
                                registerState = viewModel.registerState,
                                changeRegisterStateIfError = viewModel::changeRegisterStateIfError,
                                onRegisterClicked = viewModel::register,
                                onChangeLoginMode = { isRegistration = !isRegistration },
                            )
                        } else {
                            LoginColumn(
                                loginState = viewModel.loginState,
                                changeLoginStateIfError = viewModel::changeLoginStateIfError,
                                onLoginClicked = viewModel::login,
                                onChangeLoginMode = { isRegistration = !isRegistration },
                            )
                        }
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
    LoginScreen {}
}