package com.digimoplus.foodninja.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.presentation.theme.AppTheme

@Composable
fun BackButton(
    paddingValues: PaddingValues = PaddingValues(),
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .clickable { onClick() }
            .padding(paddingValues),
        shape = RoundedCornerShape(15.dp),
        backgroundColor = AppTheme.colors.onSecondary,
    ) {
        Icon(
            modifier = Modifier.padding(
                horizontal = AppTheme.dimensions.grid_2_5,
                vertical = AppTheme.dimensions.grid_2
            ),
            tint = AppTheme.colors.secondary,
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = "",
        )
    }


}