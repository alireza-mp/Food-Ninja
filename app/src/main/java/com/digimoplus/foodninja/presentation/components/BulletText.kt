package com.digimoplus.foodninja.presentation.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

@Composable
fun BulletText(
    modifier: Modifier = Modifier,
    style: TextStyle,
    color: Color,
    texts: List<String>,
) {
    val bullet = "\u2022"
    val paragraphStyle = ParagraphStyle(textIndent = TextIndent(restLine = 14.sp))
    Text(
        modifier = modifier,
        style = style,
        color = color,
        text = buildAnnotatedString {
            texts.forEach {
                withStyle(style = paragraphStyle) {
                    append(bullet)
                    append("\t\t")
                    append(it)
                    append("\n")
                }
            }
        }
    )
}