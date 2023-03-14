package com.digimoplus.foodninja.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.digimoplus.foodninja.presentation.components.util.NetworkImage
import com.digimoplus.foodninja.presentation.components.util.coloredShadow
import com.digimoplus.foodninja.presentation.theme.AppTheme

@Composable
fun CommentCardItem(
    imageUrl: String,
    name: String,
    date: String,
    rate: String,
    description: String,
    isLastItem: Boolean = false,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppTheme.colors.background),
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 1.dp,
                    start = 24.dp,
                    end = 24.dp,
                    bottom = if (isLastItem) 90.dp else 19.dp
                )
                .coloredShadow(
                    offsetY = 2.dp,
                    offsetX = 2.dp,
                ),
            backgroundColor = AppTheme.colors.surface,
            shape = RoundedCornerShape(22.dp),
            elevation = 0.dp,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp, start = 10.dp, end = 20.dp, bottom = 20.dp)
            ) {
                NetworkImage(url = imageUrl, size = 64.dp)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 21.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = name,
                                color = AppTheme.colors.titleText,
                                style = AppTheme.typography.h7,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.W400,
                            )
                            Text(
                                text = date,
                                modifier = Modifier.padding(top = 5.dp),
                                color = Color.LightGray,
                                style = AppTheme.typography.body1,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.W400,
                            )
                            Spacer(modifier = Modifier.padding(bottom = 20.dp))
                        }
                        RateChips(
                            title = rate,
                            modifier = Modifier.align(Alignment.CenterVertically),
                        )
                    }
                    Text(
                        style = AppTheme.typography.body1,
                        color = AppTheme.colors.titleText,
                        lineHeight = 25.sp,
                        text = description,
                        modifier = Modifier.padding(end = 5.dp),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W400,
                    )
                }
            }
        }
    }
}