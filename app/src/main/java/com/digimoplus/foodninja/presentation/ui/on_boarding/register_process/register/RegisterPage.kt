@file:OptIn(ExperimentalComposeUiApi::class)

package com.digimoplus.foodninja.presentation.ui.on_boarding.register_process.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.domain.util.UiState
import com.digimoplus.foodninja.presentation.components.*
import com.digimoplus.foodninja.presentation.components.main_pages.PageMainBackgroundImage
import com.digimoplus.foodninja.presentation.components.util.buttonEnabledGradient
import com.digimoplus.foodninja.presentation.components.util.coloredShadow
import com.digimoplus.foodninja.presentation.navigation.Screens
import com.digimoplus.foodninja.presentation.theme.AppTheme

@Composable
fun RegisterPage(navController: NavController) {

    val viewModel: RegisterViewModel = hiltViewModel()
    // focus request references
    val (nameFocus, emailFocus, passwordFocus) = remember { FocusRequester.createRefs() }
    // focus manager
    val focusManager = LocalFocusManager.current

    PageMainBackgroundImage(
        snackBarState = viewModel.snackBarState,
        paddingValues = PaddingValues(
            top = 0.dp,
            start = 24.dp,
            end = 24.dp
        )
    ) {
        BoxWithConstraints(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter,
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth(),
            ) {

                Image(
                    modifier = Modifier
                        .width(188.dp)
                        .height(203.dp),
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = ""
                )

                Spacer(modifier = Modifier.padding(top = 30.dp))

                Text(
                    text = "Sign Up For Free",
                    style = AppTheme.typography.h5,
                    color = AppTheme.colors.titleText,
                    fontSize = 20.sp,
                )

                Spacer(modifier = Modifier.padding(top = 30.dp))

                IconTextField(
                    modifier = Modifier.coloredShadow(
                        offsetX = 8.dp,
                        offsetY = 10.dp,
                    ),
                    placeHolder = "Alireza Momenpour",
                    value = viewModel.name,
                    focusRequester = nameFocus,
                    nextFocusRequester = emailFocus,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                    iconId = TEXT_FIELD_ICON_PERSON,
                )

                Spacer(modifier = Modifier.padding(12.dp))

                IconTextField(
                    modifier = Modifier.coloredShadow(
                        offsetX = 8.dp,
                        offsetY = 10.dp,
                    ),
                    placeHolder = "Email",
                    value = viewModel.email,
                    focusRequester = emailFocus,
                    nextFocusRequester = passwordFocus,
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next,
                    iconId = TEXT_FIELD_ICON_EMAIL,
                )

                Spacer(modifier = Modifier.padding(12.dp))

                IconTextField(
                    modifier = Modifier.coloredShadow(
                        offsetX = 8.dp,
                        offsetY = 10.dp,
                    ),
                    placeHolder = "Password",
                    value = viewModel.password,
                    focusRequester = passwordFocus,
                    nextFocusRequester = FocusRequester(),
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password,
                    iconId = TEXT_FIELD_ICON_PASSWORD,
                    onFocusDown = {
                        focusManager.clearFocus()
                        viewModel.register(navController = navController)
                    },
                )

                Spacer(modifier = Modifier.padding(top = 20.dp))

                CustomCheckBox(
                    modifier = Modifier
                        .fillMaxWidth(),
                    selected = viewModel.checkOne.value,
                    text = "Keep Me Signed In",
                ) {
                    viewModel.checkOne.value = !viewModel.checkOne.value
                }

                Spacer(modifier = Modifier.padding(top = 12.dp))

                CustomCheckBox(
                    modifier = Modifier
                        .fillMaxWidth(),
                    selected = viewModel.checkTwo.value,
                    text = "Email Me About Special Pricing",
                ) {
                    viewModel.checkTwo.value = !viewModel.checkTwo.value
                }

                Spacer(modifier = Modifier.padding(top = 20.dp))

                GradientButton(
                    loading = viewModel.uiState == UiState.Loading,
                    gradient = buttonEnabledGradient(),
                    text = "Create Account",
                    textColor = Color.White,
                ) { // onClick
                    viewModel.register(navController = navController)
                }

                TextButton(
                    enabled = viewModel.uiState != UiState.Loading,
                    onClick = {
                        navController.navigate(Screens.Login.route)
                    },
                ) {
                    Text(
                        text = "already have an account?",
                        style = AppTheme.typography.descBold,
                        color = AppTheme.colors.primaryText,
                        lineHeight = 25.sp,
                    )
                }

            }
        }
    }
}