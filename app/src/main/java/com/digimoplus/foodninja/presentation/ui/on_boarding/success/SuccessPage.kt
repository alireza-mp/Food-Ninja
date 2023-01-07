package com.digimoplus.foodninja.presentation.ui.on_boarding.success


import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.presentation.components.GradientButton
import com.digimoplus.foodninja.presentation.components.main_pages.PageMainBackgroundImage
import com.digimoplus.foodninja.presentation.components.util.buttonEnabledGradient
import com.digimoplus.foodninja.presentation.components.util.dps
import com.digimoplus.foodninja.presentation.components.util.gradientText
import com.digimoplus.foodninja.presentation.components.util.textBrush
import com.digimoplus.foodninja.presentation.navigation.Screens
import com.digimoplus.foodninja.presentation.theme.AppTheme

@Composable
fun SuccessPage(
    navController: NavController,
) {
    PageMainBackgroundImage(
        paddingValues = PaddingValues(
            top = 24.dps,
            start = 24.dp,
            end = 24.dp
        )
    ) {
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {

            Box(modifier = Modifier.padding(top = 7.dps)) {

                val icon by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.successful))
                val papers by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.successful_back))

                Box(modifier = Modifier.fillMaxWidth()) {
                    LottieAnimation(
                        modifier = Modifier
                            .height(170.dps),
                        composition = icon,
                        iterations = 1,
                    )
                    Column(modifier = Modifier.align(Alignment.BottomCenter)) {

                        Text(
                            text = "Congrats!",
                            lineHeight = 40.sp,
                            color = AppTheme.colors.titleText,
                            style = AppTheme.typography.h4M,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .textBrush(gradientText())
                        )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 12.dps),
                            text = "Your Profile Is Ready To Use",
                            lineHeight = 40.sp,
                            color = AppTheme.colors.titleText,
                            style = AppTheme.typography.h6,
                            textAlign = TextAlign.Center
                        )

                    }
                }
                LottieAnimation(
                    modifier = Modifier.height(200.dps),
                    composition = papers,
                    iterations = LottieConstants.IterateForever,
                )
            }

            GradientButton(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 10.dps),
                gradient = buttonEnabledGradient(),
                text = "Try Order",
                textColor = Color.White,
            ) {
                navController.navigate(Screens.Main.route) {
                    //remove all last pages from back stack
                    popUpTo(0)
                }
            }
        }
    }
}