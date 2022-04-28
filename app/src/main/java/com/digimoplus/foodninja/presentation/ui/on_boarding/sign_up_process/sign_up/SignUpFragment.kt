package com.digimoplus.foodninja.presentation.ui.on_boarding.sign_up_process.sign_up

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.presentation.components.*
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.digimoplus.foodninja.presentation.theme.buttonGradient
import com.digimoplus.foodninja.presentation.theme.isDark
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SignUpFragment : Fragment() {

    val viewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme(colors = isDark(isSystemInDarkTheme())) {
                    SignUpPage(viewModel = viewModel, navController = findNavController())
                }
            }
        }
    }

}

@Composable
fun SignUpPage(viewModel: SignUpViewModel, navController: NavController) {
    val snackBarState = remember {
        SnackbarHostState()
    }
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = if (AppTheme.colors.isLight) R.drawable.background_light else R.drawable.background_dark),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillWidth
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.padding(top = AppTheme.dimensions.grid_3))
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
                    .fillMaxWidth()
                    .padding(horizontal = AppTheme.dimensions.grid_3),
                selected = viewModel.checkOne.value,
                text = "Keep Me Signed In"
            ) {
                viewModel.checkOne.value = !viewModel.checkOne.value
            }
            Spacer(modifier = Modifier.padding(AppTheme.dimensions.grid_0_5))
            CustomCheckBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = AppTheme.dimensions.grid_3),
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
                        gradient = buttonGradient(),
                        text = "Create Account",
                        textColor = Color.White,
                    ) { // onClick
                        coroutineScope.launch {
                            viewModel.register(state = snackBarState)
                        }
                    }
                    Spacer(modifier = Modifier.padding(AppTheme.dimensions.grid_0_5))
                    TextButton(
                        enabled = !viewModel.loading.value,
                        onClick = {
                            navController.navigate(R.id.action_signUpFragment_to_signInFragment)
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

        CustomSnackBar(
            snackBarHostState = snackBarState,
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            snackBarState.currentSnackbarData?.dismiss()
        }
    }
}