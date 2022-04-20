package com.digimoplus.foodninja.presentation.ui.on_boarding.introduction

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.presentation.components.FloatingImageAnimation
import com.digimoplus.foodninja.presentation.components.GradientButton
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.digimoplus.foodninja.presentation.theme.buttonGradient
import com.digimoplus.foodninja.presentation.theme.darkTheme
import com.digimoplus.foodninja.presentation.theme.isDark
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@ExperimentalPagerApi
@Composable
fun IntroductionPageOne(pagerState: PagerState) {

    Surface(color = AppTheme.colors.background) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize(),
        ) {

            val coroutineScope = rememberCoroutineScope()
            val (
                imageOne, imageTwo, imageThree, imageFour, backImage, titleText, descriptionText, nextButton
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
                        R.drawable.donate_light_background
                    else
                        R.drawable.donate_dark_background

                ),
                contentDescription = "",
                contentScale = ContentScale.Fit
            )

            // donate image one
            FloatingImageAnimation(
                modifier = Modifier.constrainAs(imageOne) {
                    top.linkTo(backImage.top, 50.dp)
                    start.linkTo(parent.start, 50.dp)
                },
                randomRange = (10..20)
            ) { alpha ->
                Image(
                    alpha = alpha,
                    painter = painterResource(id = R.drawable.donate4),
                    contentDescription = ""
                )
            }

            // donate image two
            FloatingImageAnimation(
                modifier = Modifier.constrainAs(imageTwo) {
                    top.linkTo(imageOne.top, 50.dp)
                    start.linkTo(imageOne.end, 0.dp)
                },
                randomRange = (10..20)
            ) { alpha ->
                Image(
                    alpha = alpha,
                    painter = painterResource(id = R.drawable.donate1),
                    contentDescription = "",
                    modifier = Modifier.width(135.dp)
                )
            }

            // donate image three
            FloatingImageAnimation(
                modifier = Modifier.constrainAs(imageThree) {
                    top.linkTo(imageOne.bottom, 10.dp)
                    start.linkTo(imageOne.start, 30.dp)
                },
                randomRange = (10..20)
            ) { alpha ->
                Image(
                    alpha = alpha,
                    painter = painterResource(id = R.drawable.donate3),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .width(60.dp)
                        .height(60.dp)
                )
            }

            // donate image four
            FloatingImageAnimation(
                modifier = Modifier.constrainAs(imageFour) {
                    top.linkTo(imageOne.bottom, 0.dp)
                    start.linkTo(imageThree.end, 0.dp)
                },
                randomRange = (10..20)
            ) { alpha ->
                Image(
                    alpha = alpha,
                    painter = painterResource(id = R.drawable.donate2),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .width(150.dp)
                        .height(150.dp)
                )
            }

            Text(
                text = "Find your  Comfort Food here", modifier = Modifier
                    .constrainAs(titleText) {
                        top.linkTo(backImage.bottom, 8.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .width(250.dp),
                style = AppTheme.typography.h5,
                color = AppTheme.colors.titleText,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                lineHeight = 35.sp
            )

            Text(
                text = "Here You Can find a chef or dish for every taste and color. Enjoy!",
                modifier = Modifier
                    .constrainAs(descriptionText) {
                        top.linkTo(titleText.bottom, 32.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .width(260.dp),
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
                coroutineScope.launch {
                    delay(100)
                    pagerState.animateScrollToPage(1)
                }
            }
        }
    }
}
