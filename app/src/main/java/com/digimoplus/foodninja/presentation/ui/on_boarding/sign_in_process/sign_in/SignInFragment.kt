package com.digimoplus.foodninja.presentation.ui.on_boarding.sign_in_process.sign_in

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.presentation.components.CustomTextField
import com.digimoplus.foodninja.presentation.components.GradientButton
import com.digimoplus.foodninja.presentation.components.TextFieldType
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.digimoplus.foodninja.presentation.theme.buttonGradient
import com.digimoplus.foodninja.presentation.theme.isDark


class SignInFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme(isDark(isSystemInDarkTheme())) {
                    SingInPage(findNavController())
                }
            }
        }
    }
}


@Composable
fun SingInPage(navController: NavController) {

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
                text = "Login To Your Account",
                style = AppTheme.typography.h5,
                color = AppTheme.colors.titleText,
                lineHeight = 25.sp,
            )
            Spacer(modifier = Modifier.padding(top = AppTheme.dimensions.grid_6))
            CustomTextField(placeHolder = "Email", textFieldType = TextFieldType.Email)
            Spacer(modifier = Modifier.padding(AppTheme.dimensions.grid_1_5))
            CustomTextField(placeHolder = "Password", textFieldType = TextFieldType.SignInPassword)
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
                            start = AppTheme.dimensions.grid_3,
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
                            end = AppTheme.dimensions.grid_3,
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
            Text(
                text = "Forgot Your Password?",
                style = AppTheme.typography.descBold,
                color = AppTheme.colors.primaryText,
                lineHeight = 25.sp,
            )

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                GradientButton(
                    modifier = Modifier.padding(vertical = AppTheme.dimensions.grid_1_5),
                    gradient = buttonGradient(),
                    text = "LogIn",
                    textColor = Color.White
                ) { // onClick

                }
            }
        }
    }
}
