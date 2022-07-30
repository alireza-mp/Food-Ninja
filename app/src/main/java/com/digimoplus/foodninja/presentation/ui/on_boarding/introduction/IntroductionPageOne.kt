@file:OptIn(ExperimentalPagerApi::class)

package com.digimoplus.foodninja.presentation.ui.on_boarding.introduction

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.presentation.components.FloatingImageAnimation
import com.digimoplus.foodninja.presentation.components.GradientButton
import com.digimoplus.foodninja.presentation.components.util.buttonEnabledGradient
import com.digimoplus.foodninja.presentation.components.util.dps
import com.digimoplus.foodninja.presentation.components.util.dpw
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun IntroductionPageOne(pagerState: PagerState) {

    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = AppTheme.colors.background),
        contentAlignment = Alignment.Center) {

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

                    .size(175.dpw, 200.dps),
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
                    top.linkTo(backImage.top, 20.dps)
                    start.linkTo(backImage.start, 20.dpw)
                },
                randomRange = (10..20)
            ) { alpha ->
                Image(
                    alpha = alpha,
                    painter = painterResource(id = R.drawable.donate4),
                    contentDescription = "",
                    modifier = Modifier.size(70.dpw, 70.dps),
                    contentScale = ContentScale.Fit
                )
            }

            // donate image two
            FloatingImageAnimation(
                modifier = Modifier.constrainAs(imageTwo) {
                    top.linkTo(backImage.top, 35.dps)
                    end.linkTo(backImage.end, 28.dpw)
                },
                randomRange = (10..20)
            ) { alpha ->
                Image(
                    alpha = alpha,
                    painter = painterResource(id = R.drawable.donate1),
                    contentDescription = "",
                    modifier = Modifier.size(60.dpw, 60.dps),
                    contentScale = ContentScale.Fit
                )
            }

            // donate image three
            FloatingImageAnimation(
                modifier = Modifier.constrainAs(imageThree) {
                    top.linkTo(backImage.top, 97.dps)
                    start.linkTo(backImage.start, 28.dpw)
                },
                randomRange = (10..20)
            ) { alpha ->
                Image(
                    alpha = alpha,
                    painter = painterResource(id = R.drawable.donate3),
                    contentDescription = "",
                    modifier = Modifier.size(35.dpw, 35.dps),
                    contentScale = ContentScale.Fit
                )
            }

            // donate image four
            FloatingImageAnimation(
                modifier = Modifier.constrainAs(imageFour) {
                    top.linkTo(backImage.top, 90.dps)
                    end.linkTo(backImage.end, 42.dpw)
                },
                randomRange = (10..20)
            ) { alpha ->
                Image(
                    alpha = alpha,
                    painter = painterResource(id = R.drawable.donate2),
                    contentDescription = "",
                    modifier = Modifier.size(70.dpw, 70.dps),
                    contentScale = ContentScale.Fit
                )
            }

            Text(
                text = "Find your  Comfort Food here", modifier = Modifier
                    .constrainAs(titleText) {
                        top.linkTo(backImage.bottom, 0.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .width(120.dpw),
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
                        top.linkTo(titleText.bottom, 15.dps)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .width(120.dpw),
                style = AppTheme.typography.body2,
                color = AppTheme.colors.titleText,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal,
                lineHeight = 25.sp
            )
        }

        GradientButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp),
            textColor = Color.White,
            text = "Next",
            gradient = buttonEnabledGradient()) {
            coroutineScope.launch {
                delay(100)
                pagerState.animateScrollToPage(1)
            }
        }


    }
}

