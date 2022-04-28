package com.digimoplus.foodninja.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.digimoplus.foodninja.presentation.theme.buttonGradient
import okhttp3.internal.wait


@Composable
fun CustomSnackBar(
    snackBarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit
) {
    SnackbarHost(
        hostState = snackBarHostState,
        snackbar = { data ->
            Snackbar(
                modifier = Modifier.padding(AppTheme.dimensions.grid_2),
                backgroundColor = if (AppTheme.colors.isLight) Color.DarkGray else AppTheme.colors.surface,
                action = {
                    data.actionLabel?.let {
                        TextButton(onClick = onDismiss) {
                            Text(
                                text = it,
                                style = AppTheme.typography.body1,
                                color = Color.White
                            )
                        }
                    }
                }
            ) {
                Text(
                    text = data.message,
                    style = AppTheme.typography.body1,
                    color = Color.White
                )
            }

        },
        modifier = modifier
    )
}