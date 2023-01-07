package com.digimoplus.foodninja.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.presentation.theme.AppTheme

@Composable
fun NotFoundContent() {
    Box(modifier = Modifier.padding(bottom = 100.dp)) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(
            R.raw.not_found))
        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever,
        )

        Text(
            modifier = Modifier.align(Alignment.BottomCenter),
            text = "Not Found",
            style = AppTheme.typography.body,
            color = AppTheme.colors.onTitleText
        )
    }
}