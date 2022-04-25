package com.digimoplus.foodninja.presentation.ui.on_boarding.sign_up_process.sign_up

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.presentation.components.*
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.digimoplus.foodninja.presentation.theme.buttonGradient
import com.digimoplus.foodninja.presentation.theme.isDark


class SignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme(colors = isDark(isSystemInDarkTheme())) {
                    SignUpPage(navController = findNavController())
                }
            }
        }
    }

}

@Composable
fun SignUpPage(navController: NavController) {

    val checkedOne = remember { mutableStateOf(false) }
    val checkedTwo = remember { mutableStateOf(false) }

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
                TextFieldType.Name,
                textFieldIcon = TextFieldIcon.Person
            )
            Spacer(modifier = Modifier.padding(AppTheme.dimensions.grid_1))
            CustomTextField(
                placeHolder = "Email",
                TextFieldType.Email,
                textFieldIcon = TextFieldIcon.Email
            )
            Spacer(modifier = Modifier.padding(AppTheme.dimensions.grid_1))
            CustomTextField(
                placeHolder = "Password",
                TextFieldType.SignUpPassword,
                textFieldIcon = TextFieldIcon.Password
            )

            Spacer(modifier = Modifier.padding(top = AppTheme.dimensions.grid_1_5))
            CustomCheckBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = AppTheme.dimensions.grid_3),
                selected = checkedOne.value,
                text = "Keep Me Signed In"
            ) {
                checkedOne.value = !checkedOne.value
            }
            Spacer(modifier = Modifier.padding(AppTheme.dimensions.grid_0_5))
            CustomCheckBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = AppTheme.dimensions.grid_3),
                selected = checkedTwo.value,
                text = "Email Me About Special Pricing"
            ) {
                checkedTwo.value = !checkedTwo.value
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
                        modifier = Modifier.padding(vertical = AppTheme.dimensions.grid_1_5),
                        gradient = buttonGradient(),
                        text = "Create Account",
                        textColor = Color.White
                    ) { // onClick

                    }
                    Spacer(modifier = Modifier.padding(AppTheme.dimensions.grid_0_5))
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