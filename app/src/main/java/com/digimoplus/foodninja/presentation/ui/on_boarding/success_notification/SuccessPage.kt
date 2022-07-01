package com.digimoplus.foodninja.presentation.ui.on_boarding.success_notification


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.airbnb.lottie.compose.*

import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.presentation.Screens
import com.digimoplus.foodninja.presentation.components.GradientButton
import com.digimoplus.foodninja.presentation.components.main_pages.PageMainBackgroundImage
import com.digimoplus.foodninja.presentation.components.util.buttonEnabledGradient
import com.digimoplus.foodninja.presentation.components.util.gradientText
import com.digimoplus.foodninja.presentation.components.util.textBrush
import com.digimoplus.foodninja.presentation.theme.AppTheme

/*class SuccessNotificationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {

            }
        }
    }

}*/

@Composable
fun SuccessPage(
    navController: NavController,
) {
    PageMainBackgroundImage(
        paddingValues = PaddingValues(
            top = AppTheme.dimensions.grid_3,
            start = AppTheme.dimensions.grid_3,
            end = AppTheme.dimensions.grid_3
        )
    ) {
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {

            Box(modifier = Modifier.padding(top = maxHeight * 0.07f)) {

                val icon by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.successful))
                val papers by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.successful_back))

                Box(modifier = Modifier.fillMaxWidth()) {
                    LottieAnimation(
                        modifier = Modifier
                            .height(380.dp),
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
                                .padding(top = 24.dp),
                            text = "Your Profile Is Ready To Use",
                            lineHeight = 40.sp,
                            color = AppTheme.colors.titleText,
                            style = AppTheme.typography.h6,
                            textAlign = TextAlign.Center
                        )

                    }
                }
                LottieAnimation(
                    modifier = Modifier.height(350.dp),
                    composition = papers,
                    iterations = LottieConstants.IterateForever,
                )
            }

            GradientButton(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = AppTheme.dimensions.grid_6),
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


/*  Box(modifier = Modifier
        .fillMaxSize()
        .background(color = AppTheme.colors.background),
        contentAlignment = Alignment.Center) {
        Button(onClick = {
            navController.navigate(Screens.Main.route) {
                //remove all last pages from back stack
                popUpTo(0)
            }
        }) {
            Text(text = "Goo")
        }
    }*/