package com.digimoplus.foodninja.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.digimoplus.foodninja.presentation.theme.AppTheme

@Composable
fun ListNoInternetItem(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        TextButton(
            onClick = onClick
        ) {
            Text(
                text = "Failed! Tap to try Again",
                color = AppTheme.colors.titleText,
                style = AppTheme.typography.body1
            )
        }
    }
}
