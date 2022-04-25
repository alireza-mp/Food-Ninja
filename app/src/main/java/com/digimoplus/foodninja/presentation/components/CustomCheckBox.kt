package com.digimoplus.foodninja.presentation.components

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ProgressIndicatorDefaults.StrokeWidth
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.domain.util.Constants.Companion.TAG
import com.digimoplus.foodninja.presentation.theme.AppTheme
import kotlin.math.floor
import kotlin.math.log


@Composable
fun CustomCheckBox(modifier : Modifier= Modifier,text:String,selected: Boolean, onChecked: () -> Unit) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start
    ) {
        CircularCheckBox(selected = selected, onChecked = onChecked)
        Text(
            color = AppTheme.colors.titleText,
            style = AppTheme.typography.body1,
            text = text,
            modifier = Modifier
                .align(alignment = Alignment.CenterVertically)
                .padding(start = 8.dp)
        )
    }
}


@Composable
private fun CircularCheckBox(selected: Boolean, onChecked: () -> Unit) {

    IconButton(
        onClick = {
            onChecked()
        },
        modifier = Modifier
            .background(
                color = if (selected) AppTheme.colors.primary else AppTheme.colors.secondarySurface,
                shape = CircleShape,
            )
            .size(30.dp, 30.dp),
    ) {
        Icon(
            modifier = Modifier.size(18.dp, 18.dp),
            painter = painterResource(id = R.drawable.check_box),
            tint = if (selected) Color.White else Color.Transparent,
            contentDescription = "checkbox"
        )
    }
}