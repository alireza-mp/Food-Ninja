package com.digimoplus.foodninja.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.presentation.theme.AppTheme

@Composable
fun BackButton(
    paddingValues: PaddingValues = PaddingValues(),
    onClick: () -> Unit,
) {
    Button(
        modifier = Modifier
            .padding(paddingValues)
            .size(45.dp),
        shape = RoundedCornerShape(15.dp),
        contentPadding = PaddingValues(0.dp),
        onClick = {
            onClick()
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = AppTheme.colors.onSecondary),
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Icon(
                modifier = Modifier.size(10.dp, 17.dp),
                tint = AppTheme.colors.secondary,
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "",
            )
        }
    }


}