@file:OptIn(ExperimentalFoundationApi::class)

package com.digimoplus.foodninja.presentation.ui.on_boarding.introduction

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.PagerState
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
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.presentation.components.FloatingImageAnimation
import com.digimoplus.foodninja.presentation.components.GradientButton
import com.digimoplus.foodninja.presentation.components.util.buttonEnabledGradient
import com.digimoplus.foodninja.presentation.theme.AppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun IntroductionPageOne(pagerState: PagerState) {

    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {

        ConstraintLayout {

            // constraint references
            val (
                imageOne, imageTwo, imageThree, imageFour, backImage, titleText, descriptionText,
            ) = createRefs()

            // background image
            Image(
                modifier = Modifier
                    .constrainAs(backImage) {
                        start.linkTo(parent.start, 0.dp)
                        top.linkTo(parent.top, 0.dp)
                        end.linkTo(parent.end, 0.dp)
                    }
                    .size(350.dp, 400.dp),
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
                    top.linkTo(backImage.top, 25.dp)
                    start.linkTo(backImage.start, 35.dp)
                },
                randomRange = (10..20)
            ) { alpha ->
                Image(
                    alpha = alpha,
                    painter = painterResource(id = R.drawable.donate4),
                    contentDescription = "",
                    modifier = Modifier.size(138.dp, 145.dp),
                    contentScale = ContentScale.Fit
                )
            }

            // donate image two
            FloatingImageAnimation(
                modifier = Modifier.constrainAs(imageTwo) {
                    top.linkTo(backImage.top, 80.dp)
                    end.linkTo(backImage.end, 50.dp)
                },
                randomRange = (10..20)
            ) { alpha ->
                Image(
                    alpha = alpha,
                    painter = painterResource(id = R.drawable.donate1),
                    contentDescription = "",
                    modifier = Modifier.size(121.dp, 106.dp),
                    contentScale = ContentScale.Fit
                )
            }

            // donate image three
            FloatingImageAnimation(
                modifier = Modifier.constrainAs(imageThree) {
                    bottom.linkTo(backImage.bottom, 135.dp)
                    start.linkTo(backImage.start, 60.dp)
                },
                randomRange = (10..20)
            ) { alpha ->
                Image(
                    alpha = alpha,
                    painter = painterResource(id = R.drawable.donate3),
                    contentDescription = "",
                    modifier = Modifier.size(52.dp, 66.dp),
                    contentScale = ContentScale.Fit
                )
            }

            // donate image four
            FloatingImageAnimation(
                modifier = Modifier.constrainAs(imageFour) {
                    bottom.linkTo(backImage.bottom, 95.dp)
                    end.linkTo(backImage.end, 115.dp)
                },
                randomRange = (10..20)
            ) { alpha ->
                Image(
                    alpha = alpha,
                    painter = painterResource(id = R.drawable.donate2),
                    contentDescription = "",
                    modifier = Modifier.size(112.dp, 112.dp),
                    contentScale = ContentScale.Fit
                )
            }

            Text(
                text = "Find your Comfort Food here",
                modifier = Modifier
                    .constrainAs(titleText) {
                        top.linkTo(backImage.bottom, 0.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .width(211.dp),
                style = AppTheme.typography.h5,
                color = AppTheme.colors.titleText,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                lineHeight = 35.sp,
                fontSize = 22.sp,
            )

            Text(
                text = "Here You Can find a chef or dish for every taste and color. Enjoy!",
                modifier = Modifier
                    .constrainAs(descriptionText) {
                        top.linkTo(titleText.bottom, 25.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .width(244.dp),
                style = AppTheme.typography.body2,
                color = AppTheme.colors.titleText,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal,
                lineHeight = 25.sp,
                fontSize = 12.sp,
            )
        }

        GradientButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp),
            textColor = Color.White,
            text = "Next",
            gradient = buttonEnabledGradient()
        ) {
            coroutineScope.launch {
                delay(100)
                pagerState.animateScrollToPage(1)
            }
        }


    }
}

