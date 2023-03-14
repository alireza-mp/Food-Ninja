@file:OptIn(ExperimentalMaterialApi::class)

package com.digimoplus.foodninja.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.presentation.components.util.buttonEnabledGradient
import com.digimoplus.foodninja.presentation.components.util.gradientText
import com.digimoplus.foodninja.presentation.components.util.textBrush
import com.digimoplus.foodninja.presentation.theme.AppTheme


@Composable
fun TitleChips(
    title: String,
) {
    Box(
        modifier = Modifier.background(
            brush = buttonEnabledGradient(),
            alpha = 0.2f,
            shape = RoundedCornerShape(19.dp)
        )
    ) {
        Text(
            text = title,
            modifier = Modifier
                .textBrush(gradientText())
                .padding(vertical = 8.dp, horizontal = 16.dp),
            style = AppTheme.typography.h7,
            fontSize = 12.sp,
            fontWeight = FontWeight.W400,
        )
    }
}


@Composable
fun InfoChips(
    title: String,
    @DrawableRes icon: Int,
    space: Dp,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.padding(start = space))
        Text(
            style = AppTheme.typography.body1,
            color = Color.LightGray,
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight.W400,
        )
    }
}

@Composable
fun RateChips(
    modifier: Modifier = Modifier,
    title: String,
) {
    Box(
        modifier = modifier
            .background(
                brush = buttonEnabledGradient(),
                shape = RoundedCornerShape(19.dp), alpha = 0.2f
            )
    ) {

        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(17.dp, 16.dp),
                painter = painterResource(id = R.drawable.ic_star),
                contentDescription = null
            )
            Spacer(modifier = Modifier.padding(start = 5.dp))
            Text(
                text = title,
                modifier = Modifier
                    .textBrush(gradientText()), style = AppTheme.typography.h7,
                fontSize = 16.sp
            )
        }

    }
}

@Composable
fun ClickableChips(
    @DrawableRes icon: Int,
    color: Color,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier.size(34.dp),
        backgroundColor = color.copy(alpha = 0.1f),
        shape = CircleShape,
        onClick = onClick,
        elevation = 0.dp,
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Image(painter = painterResource(id = icon), contentDescription = null)
        }
    }
}

@Composable
fun DragChips() {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .size(58.dp, 5.dp)
                .background(color = Color(0xFFFEF6ED), shape = RoundedCornerShape(12.dp))
        )
    }
}