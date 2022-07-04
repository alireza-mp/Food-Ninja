package com.digimoplus.foodninja.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.ehsanmsz.mszprogressindicator.progressindicator.BallPulseProgressIndicator
import com.ehsanmsz.mszprogressindicator.progressindicator.BallSpinFadeLoaderProgressIndicator

@Composable
fun CircleBallProgress(modifier: Modifier = Modifier) {
    BallSpinFadeLoaderProgressIndicator(
        modifier = modifier,
        color = AppTheme.colors.primary,
        diameter = 50.dp,
    )
}

@Composable
fun BallProgress(modifier: Modifier=Modifier){
    
    BallPulseProgressIndicator(
        modifier = modifier,
        color = AppTheme.colors.primary,
        ballCount = 4,
        spacing = 6.dp
    )
    
    
}
