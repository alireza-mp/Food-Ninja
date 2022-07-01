package com.digimoplus.foodninja.presentation.ui.on_boarding.sign_up_process.sign_up

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.presentation.Screens
import com.digimoplus.foodninja.presentation.components.*
import com.digimoplus.foodninja.presentation.components.main_pages.PageMainBackgroundImage
import com.digimoplus.foodninja.presentation.components.util.buttonEnabledGradient
import com.digimoplus.foodninja.presentation.theme.AppTheme

@Composable
fun SignUpPage(navController: NavController) {

    val viewModel: SignUpViewModel = hiltViewModel()
    val snackBarState = remember {
        SnackbarHostState()
    }
    PageMainBackgroundImage(
        snackBarState = snackBarState,
        paddingValues = PaddingValues(
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
                text = "Sign Up For Free",
                style = AppTheme.typography.h5,
                color = AppTheme.colors.titleText,
                lineHeight = 25.sp,
            )
            Spacer(modifier = Modifier.padding(top = AppTheme.dimensions.grid_6))
            CustomTextField(
                placeHolder = "Alireza Momenpour",
                textFieldType = TextFieldType.Name,
                textFieldIcon = TextFieldIcon.Person,
                value = viewModel.name
            )
            Spacer(modifier = Modifier.padding(AppTheme.dimensions.grid_1))
            CustomTextField(
                placeHolder = "Email",
                textFieldType = TextFieldType.Email,
                textFieldIcon = TextFieldIcon.Email,
                value = viewModel.email
            )
            Spacer(modifier = Modifier.padding(AppTheme.dimensions.grid_1))
            CustomTextField(
                placeHolder = "Password",
                textFieldType = TextFieldType.SignUpPassword,
                textFieldIcon = TextFieldIcon.Password,
                value = viewModel.password
            )

            Spacer(modifier = Modifier.padding(top = AppTheme.dimensions.grid_1_5))
            CustomCheckBox(
                modifier = Modifier
                    .fillMaxWidth(),
                selected = viewModel.checkOne.value,
                text = "Keep Me Signed In"
            ) {
                viewModel.checkOne.value = !viewModel.checkOne.value
            }
            Spacer(modifier = Modifier.padding(AppTheme.dimensions.grid_0_5))
            CustomCheckBox(
                modifier = Modifier
                    .fillMaxWidth(),
                selected = viewModel.checkTwo.value,
                text = "Email Me About Special Pricing"
            ) {
                viewModel.checkTwo.value = !viewModel.checkTwo.value
            }
            Spacer(modifier = Modifier.padding(top = AppTheme.dimensions.grid_1))
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    GradientButton(
                        modifier = Modifier.padding(top = AppTheme.dimensions.grid_1_5),
                        loading = viewModel.loading.value,
                        gradient = buttonEnabledGradient(),
                        text = "Create Account",
                        textColor = Color.White,
                    ) { // onClick
                        viewModel.register(state = snackBarState, navController = navController)
                        //navController.navigate(Screens.Main.route)
                    }
                    Spacer(modifier = Modifier.padding(AppTheme.dimensions.grid_0_5))
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
}