package com.digimoplus.foodninja.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.digimoplus.foodninja.presentation.components.util.searchChipsGradient
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.digimoplus.foodninja.presentation.util.SearchCategory

@ExperimentalMaterialApi
@Composable
fun SearchChips(
    chipsValue: SearchCategory,
    selectedChips: SearchCategory,
    onClicked: (SearchCategory) -> Unit,
) {
    Card(
        modifier = Modifier.padding(end = 12.dp),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = AppTheme.colors.onSecondary,
        border = if (selectedChips == chipsValue)
            BorderStroke(
                1.dp,
                searchChipsGradient(AppTheme.colors.isLight)
            ) else null,
        onClick = {
            onClicked(chipsValue)
        }) {
        Text(modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
            text = chipsValue.title,
            color = AppTheme.colors.secondary,
            style = AppTheme.typography.body1)
    }

}