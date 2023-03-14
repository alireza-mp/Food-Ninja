@file:OptIn(ExperimentalComposeUiApi::class)

package com.digimoplus.foodninja.presentation.ui.on_boarding.login_process.login

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
import androidx.compose.ui.text.font.FontWeight
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
fun LoginPage(navController: NavController) {

    val viewModel: LoginViewModel = hiltViewModel()
    // focus request references
    val (emailFocus, passwordFocus) = remember { FocusRequester.createRefs() }
    // focus manager
    val focusManager = LocalFocusManager.current


    PageMainBackgroundImage(
        snackBarState = viewModel.snackBarState, paddingValues = PaddingValues(
            top = 0.dp,
            start = 24.dp,
            end = 24.dp
        )
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
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
                    text = "Login To Your Account",
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
                    placeHolder = "Email",
                    value = viewModel.email,
                    focusRequester = emailFocus,
                    nextFocusRequester = passwordFocus,
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Email,
                    iconId = TEXT_FIELD_ICON_EMAIL
                )

                Spacer(modifier = Modifier.padding(12.dp))

                SignInPasswordTextField(
                    modifier = Modifier.coloredShadow(
                        offsetX = 8.dp,
                        offsetY = 10.dp,
                    ),
                    placeHolder = "Password",
                    value = viewModel.password,
                    focusRequester = passwordFocus,
                    onFocusDown = {
                        focusManager.clearFocus()
                        viewModel.loginUser(navController)
                    }
                )

                Spacer(modifier = Modifier.padding(12.dp))

                Text(
                    text = "Or Continue With",
                    style = AppTheme.typography.descBold,
                    color = AppTheme.colors.titleText,
                    lineHeight = 25.sp,
                )

                Spacer(modifier = Modifier.padding(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    LoginWithCard(
                        modifier = Modifier
                            .weight(1f)
                            .height(57.dp),
                        iconId = R.drawable.facebook,
                        title = "Facebook",
                    ) {

                    }
                    Spacer(modifier = Modifier.padding(end = 20.dp))
                    LoginWithCard(
                        modifier = Modifier
                            .weight(1f)
                            .height(57.dp),
                        iconId = R.drawable.google,
                        title = "Google",
                    ) {

                    }
                }

                Spacer(modifier = Modifier.padding(top = 14.dp))

                TextButton(
                    enabled = viewModel.uiState != UiState.Loading,
                    onClick = {
                        navController.navigate(Screens.ForgetPassword.route)
                    }) {
                    Text(
                        text = "Forgot Your Password?",
                        style = AppTheme.typography.descBold,
                        color = AppTheme.colors.primaryText,
                        lineHeight = 25.sp,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W300,
                    )
                }

                GradientButton(
                    modifier = Modifier.padding(vertical = 20.dp),
                    gradient = buttonEnabledGradient(),
                    text = "LogIn",
                    textColor = Color.White,
                    loading = viewModel.uiState == UiState.Loading,
                ) { // onClick
                    viewModel.loginUser(navController)
                }

            }
        }
    }
}
