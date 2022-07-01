package com.digimoplus.foodninja.presentation.ui.on_boarding.sign_in_process.sign_in

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.presentation.Screens
import com.digimoplus.foodninja.presentation.components.CustomTextField
import com.digimoplus.foodninja.presentation.components.GradientButton
import com.digimoplus.foodninja.presentation.components.TextFieldIcon
import com.digimoplus.foodninja.presentation.components.TextFieldType
import com.digimoplus.foodninja.presentation.components.main_pages.PageMainBackgroundImage
import com.digimoplus.foodninja.presentation.components.util.buttonEnabledGradient
import com.digimoplus.foodninja.presentation.theme.AppTheme

@Composable
fun SingInPage(navController: NavController) {

    val viewModel: SignInViewModel = hiltViewModel()
    val snackBarState = remember {
        SnackbarHostState()
    }

    PageMainBackgroundImage(
        snackBarState = snackBarState, paddingValues = PaddingValues(
            top = AppTheme.dimensions.grid_3,
            start = AppTheme.dimensions.grid_3,
            end = AppTheme.dimensions.grid_3
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                modifier = Modifier
                    .width(AppTheme.dimensions.logo_size)
                    .height(AppTheme.dimensions.logo_size),
                painter = painterResource(id = R.drawable.logo), contentDescription = ""
            )
            Spacer(modifier = Modifier.padding(top = AppTheme.dimensions.grid_6))
            Text(
                text = "Login To Your Account",
                style = AppTheme.typography.h5,
                color = AppTheme.colors.titleText,
                lineHeight = 25.sp,
            )

            Spacer(modifier = Modifier.padding(top = AppTheme.dimensions.grid_6))
            CustomTextField(
                placeHolder = "Email",
                textFieldType = TextFieldType.Email,
                textFieldIcon = TextFieldIcon.Email,
                value = viewModel.email
            )
            Spacer(modifier = Modifier.padding(AppTheme.dimensions.grid_1_5))
            CustomTextField(
                placeHolder = "Password",
                textFieldType = TextFieldType.SignInPassword,
                textFieldIcon = TextFieldIcon.Password,
                value = viewModel.password
            )
            Spacer(modifier = Modifier.padding(top = AppTheme.dimensions.grid_3))
            Text(
                text = "Or Continue With",
                style = AppTheme.typography.descBold,
                color = AppTheme.colors.titleText,
                lineHeight = 25.sp,
            )
            Spacer(modifier = Modifier.padding(top = AppTheme.dimensions.grid_3))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Card(
                    modifier = Modifier
                        .padding(
                            end = AppTheme.dimensions.grid_1_5
                        )
                        .fillMaxWidth(0.45f)
                        .clickable {
                            // navController.navigate(R.id.action_signInFragment_to_signUpFragment)
                        },
                    shape = RoundedCornerShape(15.dp),
                    elevation = 8.dp,
                    backgroundColor = AppTheme.colors.surface,
                ) {
                    Row(
                        modifier = Modifier.padding(
                            horizontal = AppTheme.dimensions.grid_0_5,
                            vertical = AppTheme.dimensions.grid_2
                        ),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(painterResource(id = R.drawable.facebook), contentDescription = "")
                        Text(
                            color = AppTheme.colors.titleText,
                            style = AppTheme.typography.h7,
                            modifier = Modifier
                                .padding(start = AppTheme.dimensions.grid_1)
                                .align(Alignment.CenterVertically), text = "Facebook"
                        )
                    }
                }
                Card(
                    modifier = Modifier
                        .padding(
                            start = AppTheme.dimensions.grid_1_5
                        )
                        .fillMaxWidth()
                        .clickable {
                            // navController.navigate(R.id.action_signInFragment_to_signUpFragment)
                        },
                    elevation = 8.dp,
                    backgroundColor = AppTheme.colors.surface,
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(
                            horizontal = AppTheme.dimensions.grid_0_5,
                            vertical = AppTheme.dimensions.grid_2
                        ),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(painterResource(id = R.drawable.google), contentDescription = "")
                        Text(
                            color = AppTheme.colors.titleText,
                            style = AppTheme.typography.h7,
                            modifier = Modifier
                                .padding(start = AppTheme.dimensions.grid_1)
                                .align(Alignment.CenterVertically), text = "Google"
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.padding(top = AppTheme.dimensions.grid_3_5))
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
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                GradientButton(
                    modifier = Modifier.padding(vertical = AppTheme.dimensions.grid_1_5),
                    gradient = buttonEnabledGradient(),
                    text = "LogIn",
                    textColor = Color.White,
                    loading = viewModel.loading.value
                ) { // onClick
                    viewModel.loginUser(snackBarState, navController)
                }
            }
        }
    }
}
