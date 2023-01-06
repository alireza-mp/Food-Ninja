@file:OptIn(ExperimentalComposeUiApi::class)

package com.digimoplus.foodninja.presentation.ui.on_boarding.login_process.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
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
import com.digimoplus.foodninja.presentation.navigation.Screens
import com.digimoplus.foodninja.presentation.components.GradientButton
import com.digimoplus.foodninja.presentation.components.IconTextField
import com.digimoplus.foodninja.presentation.components.SignInPasswordTextField
import com.digimoplus.foodninja.presentation.components.TEXT_FIELD_ICON_EMAIL
import com.digimoplus.foodninja.presentation.components.main_pages.PageMainBackgroundImage
import com.digimoplus.foodninja.presentation.components.util.buttonEnabledGradient
import com.digimoplus.foodninja.presentation.components.util.dps
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
                        .width(100.dps)
                        .height(100.dps),
                    painter = painterResource(id = R.drawable.logo), contentDescription = ""
                )

                Spacer(modifier = Modifier.padding(top = 10.dps))

                Text(
                    text = "Login To Your Account",
                    style = AppTheme.typography.h5,
                    color = AppTheme.colors.titleText,
                    lineHeight = 25.sp,
                )

                Spacer(modifier = Modifier.padding(top = 10.dps))

                IconTextField(
                    placeHolder = "Email",
                    value = viewModel.email,
                    focusRequester = emailFocus,
                    nextFocusRequester = passwordFocus,
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Email,
                    iconId = TEXT_FIELD_ICON_EMAIL
                )

                Spacer(modifier = Modifier.padding(4.dps))

                SignInPasswordTextField(
                    placeHolder = "Password",
                    value = viewModel.password,
                    focusRequester = passwordFocus,
                    onFocusDown = {
                        focusManager.clearFocus()
                        viewModel.loginUser(navController)
                    }
                )

                Spacer(modifier = Modifier.padding(4.dps))

                Text(
                    text = "Or Continue With",
                    style = AppTheme.typography.descBold,
                    color = AppTheme.colors.titleText,
                    lineHeight = 25.sp,
                )

                Spacer(modifier = Modifier.padding(4.dps))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Card(
                        modifier = Modifier
                            .padding(
                                end = 4.dps
                            )
                            .weight(1f)
                            .clickable {
                                // navController.navigate(R.id.action_signInFragment_to_signUpFragment)
                            },
                        shape = RoundedCornerShape(15.dp),
                        elevation = 8.dp,
                        backgroundColor = AppTheme.colors.surface,
                    ) {
                        Row(
                            modifier = Modifier.padding(
                                horizontal = 4.dps,
                                vertical = 8.dps
                            ),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Image(painterResource(id = R.drawable.facebook),
                                contentDescription = "")
                            Text(
                                color = AppTheme.colors.titleText,
                                style = AppTheme.typography.h7,
                                modifier = Modifier
                                    .padding(start = 3.dps)
                                    .align(Alignment.CenterVertically), text = "Facebook"
                            )
                        }
                    }
                    Card(
                        modifier = Modifier
                            .padding(
                                start = 4.dps
                            )
                            .weight(1f)
                            .clickable {
                                // navController.navigate(R.id.action_signInFragment_to_signUpFragment)
                            },
                        elevation = 8.dp,
                        backgroundColor = AppTheme.colors.surface,
                        shape = RoundedCornerShape(15.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(
                                horizontal = 4.dps,
                                vertical = 8.dps
                            ),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Image(painterResource(id = R.drawable.google), contentDescription = "")
                            Text(
                                color = AppTheme.colors.titleText,
                                style = AppTheme.typography.h7,
                                modifier = Modifier
                                    .padding(start = 3.dps)
                                    .align(Alignment.CenterVertically), text = "Google"
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.padding(top = 5.dps))

                TextButton(
                    enabled = !viewModel.loading.value,
                    onClick = {
                        navController.navigate(Screens.ForgetPassword.route)
                    }) {
                    Text(
                        text = "Forgot Your Password?",
                        style = AppTheme.typography.descBold,
                        color = AppTheme.colors.primaryText,
                        lineHeight = 25.sp,
                    )
                }

                GradientButton(
                    modifier = Modifier.padding(vertical = 5.dps),
                    gradient = buttonEnabledGradient(),
                    text = "LogIn",
                    textColor = Color.White,
                    loading = viewModel.loading.value
                ) { // onClick
                    viewModel.loginUser(navController)
                }

            }
        }
    }
}
