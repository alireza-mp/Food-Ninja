package com.digimoplus.foodninja.presentation.ui.on_boarding.introduction

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.presentation.components.FloatingImageAnimation
import com.digimoplus.foodninja.presentation.components.GradientButton
import com.digimoplus.foodninja.presentation.components.util.buttonEnabledGradient
import com.digimoplus.foodninja.presentation.navigation.Screens
import com.digimoplus.foodninja.presentation.theme.AppTheme
import kotlinx.coroutines.launch
import navigateAndClean

@Composable
fun IntroductionPageTwo(
    viewModel: IntroductionViewModel,
    navController: NavController,
) {

    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        ConstraintLayout {

            // constraint references
            val (
                imageOne, imageTwo, imageThree, backImage, titleText, descriptionText,
            ) = createRefs()

            // background image
            Image(
                modifier = Modifier
                    .constrainAs(backImage) {
                        start.linkTo(parent.start, 0.dp)
                        top.linkTo(parent.top, 0.dp)
                        end.linkTo(parent.end, 0.dp)
                    }
                    .size(389.dp, 383.dp),
                painter = painterResource(
                    id = if (AppTheme.colors.isLight)
                        R.drawable.berger_light_background
                    else
                        R.drawable.berger_dark_background
                ),
                contentDescription = "",
                contentScale = ContentScale.Fit
            )

            // donate image one
            FloatingImageAnimation(
                modifier = Modifier.constrainAs(imageOne) {
                    top.linkTo(backImage.top, 45.dp)
                    start.linkTo(backImage.start, 55.dp)
                },
                randomRange = (10..20)
            ) { alpha ->
                Image(
                    alpha = alpha,
                    painter = painterResource(id = R.drawable.berger3),
                    modifier = Modifier.size(137.dp, 149.dp),
                    contentDescription = ""
                )
            }

            // donate image two
            FloatingImageAnimation(
                modifier = Modifier.constrainAs(imageTwo) {
                    top.linkTo(backImage.top, 150.dp)
                    end.linkTo(backImage.end, 100.dp)
                },
                randomRange = (10..20)
            ) { alpha ->
                Image(
                    alpha = alpha,
                    painter = painterResource(id = R.drawable.berger1),
                    contentDescription = "",
                    modifier = Modifier.size(110.dp, 102.dp),
                )
            }

            // donate image three
            FloatingImageAnimation(
                modifier = Modifier.constrainAs(imageThree) {
                    bottom.linkTo(backImage.bottom, 80.dp)
                    start.linkTo(backImage.start, 70.dp)
                },
                randomRange = (10..20)
            ) { alpha ->
                Image(
                    alpha = alpha,
                    painter = painterResource(id = R.drawable.berger2),
                    modifier = Modifier.size(116.dp, 86.dp),
                    contentDescription = "",
                )
            }

            Text(
                text = "Food Ninja is Where Your Comfort Food Lives",
                modifier = Modifier
                    .constrainAs(titleText) {
                        top.linkTo(backImage.bottom, 0.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .width(320.dp),
                style = AppTheme.typography.h5,
                color = AppTheme.colors.titleText,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                lineHeight = 35.sp
            )

            Text(
                text = "Enjoy a fast and smooth food delivery at your doorstep",
                modifier = Modifier
                    .constrainAs(descriptionText) {
                        top.linkTo(titleText.bottom, 25.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .width(250.dp),
                style = AppTheme.typography.body2,
                color = AppTheme.colors.titleText,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal,
                lineHeight = 25.sp
            )


        }

        GradientButton(
            gradient = buttonEnabledGradient(),
            textColor = Color.White,
            text = "Next",
            modifier = Modifier
                .padding(bottom = 20.dp)
                .align(Alignment.BottomCenter)
        ) {
            // onClick
            coroutineScope.launch {
                viewModel.saveIntroduction()
                // remove introduction page from backstack
                navController.navigateAndClean(
                    route = Screens.Register.route,
                    popUpRoute = Screens.Introduction.route
                )
            }
        }

    }
}
