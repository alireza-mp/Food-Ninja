package com.digimoplus.foodninja.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.presentation.theme.AppTheme
import javax.annotation.meta.When


@Composable
fun CustomTextField(
    placeHolder: String,
    textFieldType: TextFieldType =TextFieldType.Name,
    textFieldIcon: TextFieldIcon = TextFieldIcon.Person,
    value: MutableState<String>
) {
    when (textFieldType) {
        is TextFieldType.Email -> {
            EmailTextField(
                placeHolder = placeHolder,
                textFieldIcon = textFieldIcon,
                value = value
            )
        }
        is TextFieldType.Name -> {
            NameTextField(
                placeHolder = placeHolder,
                textFieldIcon = textFieldIcon,
                value = value
            )
        }
        is TextFieldType.SignInPassword -> {
            SignInPasswordTextField(
                placeHolder = placeHolder,
                textFieldIcon = textFieldIcon,
                value = value
            )
        }
        is TextFieldType.SignUpPassword -> {
            SignUpPasswordTextField(
                placeHolder = placeHolder,
                textFieldIcon = textFieldIcon,
                value = value
            )
        }
    }
}


@Composable
private fun SignInPasswordTextField(
    placeHolder: String,
    textFieldIcon: TextFieldIcon,
    value: MutableState<String>
) {

    var passwordVisibility: Boolean by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.padding(horizontal = AppTheme.dimensions.grid_3),
        shape = RoundedCornerShape(15.dp),
        backgroundColor = AppTheme.colors.surface,
        border = BorderStroke(1.dp, AppTheme.colors.onSurface)
    ) {
        TextField(
            textStyle = AppTheme.typography.body,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = AppTheme.dimensions.grid_1),
            value = value.value,
            leadingIcon = {
                SetTextFieldIcon(textFieldIcon = textFieldIcon)
            },
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
                value.value = it
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
private fun SignUpPasswordTextField(
    placeHolder: String,
    textFieldIcon: TextFieldIcon,
    value: MutableState<String>
) {

    Card(
        modifier = Modifier.padding(horizontal = AppTheme.dimensions.grid_3),
        shape = RoundedCornerShape(15.dp),
        backgroundColor = AppTheme.colors.surface,
        border = BorderStroke(1.dp, AppTheme.colors.onSurface)
    ) {
        TextField(
            textStyle = AppTheme.typography.body,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = AppTheme.dimensions.grid_1),
            value = value.value,
            leadingIcon = {
                SetTextFieldIcon(textFieldIcon = textFieldIcon)
            },
            placeholder = {
                Text(
                    text = placeHolder,
                    color = AppTheme.colors.onTitleText,
                    style = AppTheme.typography.body1
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = {
                value.value = it
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
private fun NameTextField(
    placeHolder: String,
    textFieldIcon: TextFieldIcon,
    value: MutableState<String>
) {

    Card(
        modifier = Modifier.padding(horizontal = AppTheme.dimensions.grid_3),
        shape = RoundedCornerShape(15.dp),
        backgroundColor = AppTheme.colors.surface,
        border = BorderStroke(1.dp, AppTheme.colors.onSurface)
    ) {
        TextField(
            textStyle = AppTheme.typography.body,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = AppTheme.dimensions.grid_1),
            value = value.value,
            placeholder = {
                Text(
                    text = placeHolder,
                    color = AppTheme.colors.onTitleText,
                    style = AppTheme.typography.body1
                )
            },
            leadingIcon = {
                SetTextFieldIcon(textFieldIcon = textFieldIcon)
            },
            onValueChange = {
                value.value = it
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
private fun EmailTextField(
    placeHolder: String,
    textFieldIcon: TextFieldIcon,
    value: MutableState<String>
) {

    Card(
        modifier = Modifier.padding(horizontal = AppTheme.dimensions.grid_3),
        shape = RoundedCornerShape(15.dp),
        backgroundColor = AppTheme.colors.surface,
        border = BorderStroke(1.dp, AppTheme.colors.onSurface)
    ) {
        TextField(
            textStyle = AppTheme.typography.body,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = AppTheme.dimensions.grid_1),
            value = value.value,
            leadingIcon = {
                SetTextFieldIcon(textFieldIcon = textFieldIcon)
            },
            placeholder = {
                Text(
                    text = placeHolder,
                    color = AppTheme.colors.onTitleText,
                    style = AppTheme.typography.body1
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = {
                value.value = it
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
private fun SetTextFieldIcon(textFieldIcon: TextFieldIcon) {
    when (textFieldIcon) {
        is TextFieldIcon.Email -> {
            Image(painter = painterResource(id = R.drawable.message), contentDescription = "")
        }

        is TextFieldIcon.Password -> {
            Image(painter = painterResource(id = R.drawable.lock), contentDescription = "")
        }

        is TextFieldIcon.Person -> {
            Image(painter = painterResource(id = R.drawable.profile), contentDescription = "")
        }
    }

}

sealed class TextFieldType {
    object SignInPassword : TextFieldType()
    object SignUpPassword : TextFieldType()
    object Email : TextFieldType()
    object Name : TextFieldType()
}

sealed class TextFieldIcon {
    object Password : TextFieldIcon()
    object Person : TextFieldIcon()
    object Email : TextFieldIcon()
}

