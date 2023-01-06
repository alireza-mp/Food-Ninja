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
import com.digimoplus.foodninja.presentation.navigation.Screens
import com.digimoplus.foodninja.presentation.components.*
import com.digimoplus.foodninja.presentation.components.main_pages.PageMainBackgroundImage
import com.digimoplus.foodninja.presentation.components.util.buttonEnabledGradient
import com.digimoplus.foodninja.presentation.components.util.dps
import com.digimoplus.foodninja.presentation.theme.AppTheme

@Composable
fun SignUpPage(navController: NavController) {

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
        BoxWithConstraints(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()

            ) {

                Image(
                    modifier = Modifier
                        .width(100.dps)
                        .height(100.dps),
                    painter = painterResource(id = R.drawable.logo), contentDescription = ""
                )

                Spacer(modifier = Modifier.padding(top = 10.dps))

                Text(
                    text = "Sign Up For Free",
                    style = AppTheme.typography.h5,
                    color = AppTheme.colors.titleText,
                )

                Spacer(modifier = Modifier.padding(top = 10.dps))

                IconTextField(
                    placeHolder = "Alireza Momenpour",
                    value = viewModel.name,
                    focusRequester = nameFocus,
                    nextFocusRequester = emailFocus,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                    iconId = TEXT_FIELD_ICON_PERSON
                )

                Spacer(modifier = Modifier.padding(4.dps))

                IconTextField(
                    placeHolder = "Email",
                    value = viewModel.email,
                    focusRequester = emailFocus,
                    nextFocusRequester = passwordFocus,
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next,
                    iconId = TEXT_FIELD_ICON_EMAIL,
                )

                Spacer(modifier = Modifier.padding(4.dps))

                IconTextField(
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
                    }
                )

                Spacer(modifier = Modifier.padding(top = 8.dps))

                CustomCheckBox(
                    modifier = Modifier
                        .fillMaxWidth(),
                    selected = viewModel.checkOne.value,
                    text = "Keep Me Signed In"
                ) {
                    viewModel.checkOne.value = !viewModel.checkOne.value
                }

                Spacer(modifier = Modifier.padding(1.dps))

                CustomCheckBox(
                    modifier = Modifier
                        .fillMaxWidth(),
                    selected = viewModel.checkTwo.value,
                    text = "Email Me About Special Pricing"
                ) {
                    viewModel.checkTwo.value = !viewModel.checkTwo.value
                }

                Spacer(modifier = Modifier.padding(top = 4.dps))

                GradientButton(
                    loading = viewModel.loading.value,
                    gradient = buttonEnabledGradient(),
                    text = "Create Account",
                    textColor = Color.White,
                ) { // onClick
                    viewModel.register(navController = navController)
                    //navController.navigate(Screens.Main.route)
                }

                TextButton(
                    enabled = !viewModel.loading.value,
                    onClick = {
                        navController.navigate(Screens.SignIn.route)
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