package com.digimoplus.foodninja.presentation.components.base_dispalys

import androidx.compose.foundation.layout.*
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.presentation.components.BackButton
import com.digimoplus.foodninja.presentation.components.GradientButton
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.digimoplus.foodninja.presentation.components.util.buttonGradient

@Composable
fun OnBoardingDisplay(
    title: String,
    description: String,
    snackBarState: SnackbarHostState? = null,
    onBackPress: () -> Unit,
    onClick: () -> Unit,
    loading: Boolean = false,
    buttonTitle: String = "Next",
    content: @Composable () -> Unit,
) {

    DisplayBackgroundImage(
        darkBackground = R.drawable.display_background_dark,
        lightBackground = R.drawable.display_background_light,
        snackBarState = snackBarState,
        paddingValues = PaddingValues(
            start = AppTheme.dimensions.grid_3,
            end = AppTheme.dimensions.grid_3,
            top = AppTheme.dimensions.grid_4
        )
    ) {
        Column {
            BackButton {
                onBackPress()
            }
            Spacer(modifier = Modifier.padding(top = AppTheme.dimensions.grid_3))
            Text(
                modifier = Modifier.width(AppTheme.dimensions.infoUserTitle),
                text = title,
                lineHeight = 40.sp,
                color = AppTheme.colors.titleText,
                style = AppTheme.typography.h4M
            )

            Spacer(modifier = Modifier.padding(top = AppTheme.dimensions.grid_3))
            Text(
                modifier = Modifier.width(AppTheme.dimensions.infoUserTitle),
                text = description,
                lineHeight = 30.sp,
                color = AppTheme.colors.titleText,
                style = AppTheme.typography.body2
            )
            Spacer(modifier = Modifier.padding(top = AppTheme.dimensions.grid_3))

            content()

            Box(modifier = Modifier.fillMaxSize()) {
                GradientButton(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = AppTheme.dimensions.grid_6),
                    gradient = buttonGradient(),
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