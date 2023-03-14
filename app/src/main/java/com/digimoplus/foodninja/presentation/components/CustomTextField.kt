package com.digimoplus.foodninja.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.presentation.theme.AppTheme

const val TEXT_FIELD_ICON_PASSWORD = R.drawable.ic_lock
const val TEXT_FIELD_ICON_PERSON = R.drawable.ic_profile
const val TEXT_FIELD_ICON_EMAIL = R.drawable.ic_message


// no icon
@Composable
fun NoneTextField(
    modifier: Modifier = Modifier,
    placeHolder: String,
    value: MutableState<String>,
    focusRequester: FocusRequester,
    nextFocusRequester: FocusRequester,
    onFocusDown: () -> Unit = {},
    keyboardType: KeyboardType,
    imeAction: ImeAction,
) {

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        backgroundColor = AppTheme.colors.surface,
        elevation = 0.dp,
    ) {
        TextField(
            textStyle = AppTheme.typography.body1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .focusRequester(focusRequester)
                .focusProperties { next = nextFocusRequester },
            value = value.value,
            placeholder = {
                Text(
                    text = placeHolder,
                    color = AppTheme.colors.onTitleText,
                    style = AppTheme.typography.body1,
                    fontSize = 14.sp,
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
            keyboardActions = KeyboardActions(onDone = { onFocusDown() }),
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

// It has an icon on the left
@Composable
fun IconTextField(
    modifier: Modifier = Modifier,
    placeHolder: String,
    value: MutableState<String>,
    focusRequester: FocusRequester,
    nextFocusRequester: FocusRequester,
    onFocusDown: () -> Unit = {},
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    iconId: Int,
) {

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        backgroundColor = AppTheme.colors.surface,
        elevation = 0.dp,
    ) {
        TextField(
            textStyle = AppTheme.typography.body1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .focusRequester(focusRequester)
                .focusProperties { next = nextFocusRequester },
            value = value.value,
            placeholder = {
                Text(
                    text = placeHolder,
                    color = AppTheme.colors.onTitleText,
                    style = AppTheme.typography.body1,
                    fontSize = 14.sp,
                )
            },
            leadingIcon = {
                Image(painter = painterResource(id = iconId), contentDescription = null)
            },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
            keyboardActions = KeyboardActions(onDone = { onFocusDown() }),
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

// It has an icon on the left and eye icon in right
@Composable
fun SignInPasswordTextField(
    modifier: Modifier = Modifier,
    placeHolder: String,
    value: MutableState<String>,
    focusRequester: FocusRequester,
    onFocusDown: () -> Unit,
) {

    var passwordVisibility: Boolean by remember { mutableStateOf(false) }

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        backgroundColor = AppTheme.colors.surface,
        elevation = 0.dp,
    ) {
        TextField(
            textStyle = AppTheme.typography.body1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .focusRequester(focusRequester),
            value = value.value,
            leadingIcon = {
                Image(
                    painter = painterResource(id = TEXT_FIELD_ICON_PASSWORD),
                    contentDescription = null
                )
            },
            placeholder = {
                Text(
                    text = placeHolder,
                    color = AppTheme.colors.onTitleText,
                    style = AppTheme.typography.body1,
                    fontSize = 14.sp,
                )
            },
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(onDone = { onFocusDown() }),
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


// no icon // no focus
@Composable
fun ChatTextField(
    text: MutableState<String>,
    onMessageChange: (String) -> Unit,
    placeHolder: String,
) {

    TextField(
        textStyle = AppTheme.typography.body,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 8.dp,
                vertical = 4.dp
            ),
        value = text.value,
        onValueChange = {
            onMessageChange(it)
        },
        placeholder = {
            Text(
                text = placeHolder,
                color = AppTheme.colors.onTitleText,
                style = AppTheme.typography.body
            )
        },
        singleLine = false,
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

