package com.digimoplus.foodninja.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.presentation.theme.AppTheme

@Composable
fun NoInternetContent(
    onRetry: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppTheme.colors.background),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val anim by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.no_internet))
            LottieAnimation(
                modifier = Modifier.fillMaxWidth(),
                composition = anim,
                iterations = LottieConstants.IterateForever,
            )
            Spacer(modifier = Modifier.padding(top = 24.dp))
            Text(
                text = "No internet Connection",
                color = AppTheme.colors.titleText,
                style = AppTheme.typography.h4M,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.padding(top = 10.dp))
            TextButton(onClick = onRetry) {
                Text(text = "tap to try again", color = AppTheme.colors.primaryText,
                    style = AppTheme.typography.body
                )
            }
        }
    }
}