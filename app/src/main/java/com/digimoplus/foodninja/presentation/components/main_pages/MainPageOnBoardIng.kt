package com.digimoplus.foodninja.presentation.components.main_pages

import androidx.compose.foundation.layout.*
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.presentation.components.BackButton
import com.digimoplus.foodninja.presentation.components.GradientButton
import com.digimoplus.foodninja.presentation.components.util.buttonEnabledGradient
import com.digimoplus.foodninja.presentation.theme.AppTheme

@Composable
fun OnBoardingMainPage(
    title: String,
    description: String,
    snackBarState: SnackbarHostState? = null,
    navController: NavController,
    onClick: () -> Unit,
    loading: Boolean = false,
    backButtonEnabled: Boolean = true,
    buttonTitle: String = "Next",
    content: @Composable () -> Unit,
) {

    PageMainBackgroundImage(
        darkBackground = R.drawable.main_page_background_dark,
        lightBackground = R.drawable.main_page_background_light,
        snackBarState = snackBarState,
        paddingValues = PaddingValues(
            start = 24.dp,
            end = 24.dp,
            top = 0.dp
        )
    ) {
        Column {

            if (backButtonEnabled) {
                Spacer(modifier = Modifier.fillMaxHeight(0.06f))
                BackButton {
                    navController.navigateUp()
                }
                Spacer(modifier = Modifier.padding(top = 20.dp))
            } else {
                Spacer(modifier = Modifier.fillMaxHeight(0.14f))
            }

            Text(
                modifier = Modifier.fillMaxWidth(0.8f),
                text = title,
                lineHeight = 40.sp,
                color = AppTheme.colors.titleText,
                style = AppTheme.typography.h4M,
                fontSize = 25.sp,
            )

            Spacer(modifier = Modifier.padding(top = 20.dp))
            Text(
                modifier = Modifier.fillMaxWidth(0.8f),
                text = description,
                lineHeight = 25.sp,
                color = AppTheme.colors.titleText,
                style = AppTheme.typography.body2,
                fontSize = 12.sp,
            )
            Spacer(modifier = Modifier.padding(top = 20.dp))

            content()

            Box(modifier = Modifier.fillMaxSize()) {
                GradientButton(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 20.dp),
                    gradient = buttonEnabledGradient(),
                    text = buttonTitle,
                    textColor = Color.White,
                    loading = loading
                ) {
                    onClick()
                }
            }
        }
    }
}