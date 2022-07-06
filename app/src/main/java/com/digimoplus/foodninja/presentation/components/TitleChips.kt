package com.digimoplus.foodninja.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.digimoplus.foodninja.presentation.components.util.buttonEnabledGradient
import com.digimoplus.foodninja.presentation.components.util.gradientText
import com.digimoplus.foodninja.presentation.components.util.textBrush


@Composable
fun TitleChips(
    title: String,
) {
    Box(modifier = Modifier.background(
        brush = buttonEnabledGradient(),
        alpha = 0.2f,
        shape = RoundedCornerShape(15.dp)
    )) {
        Text(text = title,
            modifier = Modifier
                .textBrush(gradientText())
                .padding(vertical = 8.dp, horizontal = 16.dp)
        )
    }
}