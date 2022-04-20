package com.digimoplus.foodninja.presentation.ui.on_boarding.introduction

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
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
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.digimoplus.foodninja.presentation.theme.buttonGradient

@Composable
fun IntroductionPageTwo(navController: NavController) {
    Surface(color = AppTheme.colors.background) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize(),
        ) {

            val (
                imageOne, imageTwo, imageThree, backImage, titleText, descriptionText, nextButton
            ) = createRefs()

            // background image
            Image(
                modifier = Modifier
                    .constrainAs(backImage) {
                        start.linkTo(parent.start, 16.dp)
                        top.linkTo(parent.top, 45.dp)
                        end.linkTo(parent.end, 16.dp)
                    }
                    .fillMaxHeight(0.6f),
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
                    top.linkTo(backImage.top, 80.dp)
                    start.linkTo(parent.start, 45.dp)
                },
                randomRange = (10..20)
            ) { alpha ->
                Image(
                    alpha = alpha,
                    painter = painterResource(id = R.drawable.berger3),
                    contentDescription = ""
                )
            }

            // donate image two
            FloatingImageAnimation(
                modifier = Modifier.constrainAs(imageTwo) {
                    top.linkTo(imageOne.top, 115.dp)
                    start.linkTo(imageOne.start, 125.dp)
                },
                randomRange = (10..20)
            ) { alpha ->
                Image(
                    alpha = alpha,
                    painter = painterResource(id = R.drawable.berger1),
                    contentDescription = "",
                    modifier = Modifier.width(150.dp),
                )
            }

            // donate image three
            FloatingImageAnimation(
                modifier = Modifier.constrainAs(imageThree) {
                    top.linkTo(imageOne.bottom, 20.dp)
                    start.linkTo(imageOne.start, 20.dp)
                },
                randomRange = (10..20)
            ) { alpha ->
                Image(
                    alpha = alpha,
                    painter = painterResource(id = R.drawable.berger2),
                    contentDescription = "",
                )
            }

            Text(
                text = "Food Ninja is Where Your Comfort Food Lives", modifier = Modifier
                    .constrainAs(titleText) {
                        top.linkTo(backImage.bottom, 8.dp)
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
                        top.linkTo(titleText.bottom, 32.dp)
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

            GradientButton(
                modifier = Modifier.constrainAs(nextButton) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    top.linkTo(descriptionText.bottom)
                },
                gradient = buttonGradient(),
                textColor = Color.White,
                text = "Next"
            ) { // onClick
                navController.navigate(R.id.action_introductionFragment_to_signInFragment)
            }
        }
    }
}
