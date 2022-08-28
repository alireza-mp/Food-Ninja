package com.digimoplus.foodninja.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.presentation.theme.AppTheme

@Composable
fun BackButton(
    paddingValues: PaddingValues = PaddingValues(),
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .padding(paddingValues)
            .defaultMinSize(1.dp, 1.dp),
        shape = RoundedCornerShape(15.dp),
        contentPadding = PaddingValues(0.dp),
        onClick = {
            onClick()
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = AppTheme.colors.onSecondary),
    ) {
        Icon(
            modifier = Modifier.padding(
                horizontal = 20.dp,
                vertical = 16.dp
            ),
            tint = AppTheme.colors.secondary,
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = "",
        )
    }


}