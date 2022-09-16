package com.astrog.login.presentation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable (() -> Unit),
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    val lightBlue = Color(0xffd8e6ff)

    TextField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        keyboardOptions = keyboardOptions,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = if (!isSystemInDarkTheme()) lightBlue else MaterialTheme.colors.primary,
            cursorColor = MaterialTheme.colors.onSurface,
            disabledLabelColor = lightBlue,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            errorLabelColor = MaterialTheme.colors.error,
            errorIndicatorColor = Color.Transparent,
            focusedLabelColor = MaterialTheme.colors.onSurface,
        ),
        isError = isError,
        shape = MaterialTheme.shapes.medium,
        singleLine = true,
        visualTransformation = visualTransformation,
    )
}