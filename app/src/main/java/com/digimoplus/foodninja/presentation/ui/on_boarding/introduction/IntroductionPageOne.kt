package com.digimoplus.foodninja.presentation.ui.on_boarding.introduction

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import com.digimoplus.foodninja.presentation.theme.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@ExperimentalPagerApi
@Composable
fun IntroductionPageOne(pagerState: PagerState, dimensions: AppDimensions) {

    Surface(color = AppTheme.colors.background) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize(),
        ) {

            val coroutineScope = rememberCoroutineScope()
            val (
                imageOne, imageTwo, imageThree, imageFour, backImage, titleText, descriptionText, nextButton,
            ) = createRefs()

            // background image
            Image(
                modifier = Modifier
                    .constrainAs(backImage) {
                        start.linkTo(parent.start, dimensions.grid_2)
                        top.linkTo(parent.top, dimensions.grid_5_5)
                        end.linkTo(parent.end, dimensions.grid_2)

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
                    top.linkTo(backImage.top, dimensions.grid_6)
                    start.linkTo(parent.start, dimensions.grid_6)
                },
                randomRange = (10..20)
            ) { alpha ->
                Image(
                    alpha = alpha,
                    painter = painterResource(id = R.drawable.donate4),
                    contentDescription = "",
                    modifier = Modifier.width(dimensions.img_donate_4)
                )
            }

            // donate image two
            FloatingImageAnimation(
                modifier = Modifier.constrainAs(imageTwo) {
                    top.linkTo(imageOne.top, dimensions.grid_6)
                    start.linkTo(imageOne.end, 0.dp)
                },
                randomRange = (10..20)
            ) { alpha ->
                Image(
                    alpha = alpha,
                    painter = painterResource(id = R.drawable.donate1),
                    contentDescription = "",
                    modifier = Modifier.width(dimensions.img_donate_1)
                )
            }

            // donate image three
            FloatingImageAnimation(
                modifier = Modifier.constrainAs(imageThree) {
                    top.linkTo(imageOne.bottom, dimensions.grid_3)
                    start.linkTo(imageOne.start, dimensions.grid_3)
                },
                randomRange = (10..20)
            ) { alpha ->
                Image(
                    alpha = alpha,
                    painter = painterResource(id = R.drawable.donate3),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .width(dimensions.img_donate_3)
                        .height(dimensions.img_donate_3)
                )
            }

            // donate image four
            FloatingImageAnimation(
                modifier = Modifier.constrainAs(imageFour) {
                    top.linkTo(imageOne.bottom, dimensions.grid_2)
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
                        .width(dimensions.img_donate_2)
                        .height(dimensions.img_donate_2)
                )
            }

            Text(
                text = "Find your  Comfort Food here", modifier = Modifier
                    .constrainAs(titleText) {
                        top.linkTo(backImage.bottom, dimensions.grid_1)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .width(dimensions.title_page_one),
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
                        top.linkTo(titleText.bottom, dimensions.grid_4)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .width(dimensions.description_page_one),
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
                gradient = buttonEnabledGradient(),
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
