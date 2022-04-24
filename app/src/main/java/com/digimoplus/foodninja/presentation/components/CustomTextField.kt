package com.digimoplus.foodninja.presentation.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.digimoplus.foodninja.presentation.theme.AppTheme


@Composable
fun PasswordTextField(
    placeHolder: String,
) {

    var passwordVisibility: Boolean by remember { mutableStateOf(false) }
    var value by rememberSaveable { mutableStateOf("") }
    Card(
        modifier = Modifier.padding(horizontal = AppTheme.dimensions.grid_3),
        elevation = 5.dp,
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(1.dp, AppTheme.colors.onSurface)
    ) {
        TextField(
            textStyle = AppTheme.typography.body,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = AppTheme.dimensions.grid_1),
            value = value,
            placeholder = {
                Text(
                    text = placeHolder,
                    color = AppTheme.colors.onTitleText,
                    style = AppTheme.typography.body1
                )
            },
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                IconButton(onClick = {
                    passwordVisibility = !passwordVisibility
                }) {
                    if (passwordVisibility) {
                        Icon(
                            imageVector = Icons.Filled.VisibilityOff,
                            contentDescription = ""
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Filled.Visibility,
                            contentDescription = ""
                        )
                    }
                }
            },
            onValueChange = {
                value = it
            },
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                textColor = AppTheme.colors.titleText,
                cursorColor = AppTheme.colors.primaryText,
                errorCursorColor = AppTheme.colors.secondary,
                disabledTextColor = Color.Transparent,
                backgroundColor = AppTheme.colors.surface,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
        )
    }
}

@Composable
fun NameTextField(
    placeHolder: String,
) {
    var value by rememberSaveable { mutableStateOf("") }
    Card(
        modifier = Modifier.padding(horizontal = AppTheme.dimensions.grid_3),
        elevation = 5.dp,
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(1.dp, AppTheme.colors.onSurface)
    ) {
        TextField(
            textStyle = AppTheme.typography.body,
            modifier = Modifier
                .fillMaxWidth()
                .padding( horizontal = AppTheme.dimensions.grid_1),
            value = value,
            placeholder = {
                Text(
                    text = placeHolder,
                    color = AppTheme.colors.onTitleText,
                    style = AppTheme.typography.body1
                )
            },
            onValueChange = {
                value = it
            },
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                textColor = AppTheme.colors.titleText,
                cursorColor = AppTheme.colors.primaryText,
                errorCursorColor = AppTheme.colors.secondary,
                disabledTextColor = Color.Transparent,
                backgroundColor = AppTheme.colors.surface,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
        )
    }
}

@Composable
fun EmailTextField(
    placeHolder: String,
) {
    var value by rememberSaveable { mutableStateOf("") }
    Card(
        modifier = Modifier.padding(horizontal = AppTheme.dimensions.grid_3),
        elevation = 5.dp,
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(1.dp, AppTheme.colors.onSurface)
    ) {
        TextField(
            textStyle = AppTheme.typography.body,
            modifier = Modifier
                .fillMaxWidth()
                .padding( horizontal = AppTheme.dimensions.grid_1),
            value = value,
            placeholder = {
                Text(
                    text = placeHolder,
                    color = AppTheme.colors.onTitleText,
                    style = AppTheme.typography.body1
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = {
                value = it
            },
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                textColor = AppTheme.colors.titleText,
                cursorColor = AppTheme.colors.primaryText,
                errorCursorColor = AppTheme.colors.secondary,
                disabledTextColor = Color.Transparent,
                backgroundColor = AppTheme.colors.surface,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
        )
    }
}

