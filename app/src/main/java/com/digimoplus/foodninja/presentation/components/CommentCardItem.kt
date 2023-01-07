package com.digimoplus.foodninja.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.domain.model.MenuDetailComments
import com.digimoplus.foodninja.domain.model.RestaurantDetailComment
import com.digimoplus.foodninja.presentation.components.util.*
import com.digimoplus.foodninja.presentation.theme.AppTheme

@Composable
fun CommentCardItem(
    model: RestaurantDetailComment,
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = AppTheme.colors.background)) {
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 10.dp),
            backgroundColor = AppTheme.colors.surface,
            shape = RoundedCornerShape(20.dp)
        ) {
            Row(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)) {
                val image = loadPictureNoneDefault(url = model.imageUrl).value
                image?.let { img ->
                    Image(
                        bitmap = img.asImageBitmap(),
                        contentDescription = "",
                        modifier = Modifier
                            .size(80.dp)
                            .animateAlpha(
                                delayMillis = 500,
                                durationMillis = 750)
                            .clip(RoundedCornerShape(15.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = model.name,
                                color = AppTheme.colors.titleText,
                                style = AppTheme.typography.h7,
                            )
                            Text(
                                text = model.date,
                                modifier = Modifier.padding(bottom = 15.dp, top = 5.dp),
                                color = Color.LightGray,
                                style = AppTheme.typography.body1
                            )
                        }
                        Box(
                            modifier = Modifier
                                .background(brush = buttonEnabledGradient(),
                                    shape = RoundedCornerShape(20.dp), alpha = 0.2f
                                )
                                .align(Alignment.CenterVertically)) {

                            Row(modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(painter = painterResource(id = R.drawable.ic_star),
                                    contentDescription = null)
                                Spacer(modifier = Modifier.padding(start = 5.dp))
                                Text(
                                    text = model.rate,
                                    modifier = Modifier
                                        .textBrush(gradientText()), style = AppTheme.typography.h7,
                                    fontSize = 20.sp
                                )
                            }

                        }
                    }
                    Text(
                        style = AppTheme.typography.body1,
                        color = AppTheme.colors.titleText,
                        lineHeight = 25.sp,
                        text = model.description,
                        modifier = Modifier.padding(end = 5.dp)
                    )
                }
            }
        }
    }

}

@Composable
fun CommentCardItem(
    model: MenuDetailComments,
    isLastItem: Boolean,
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = AppTheme.colors.background)) {
        Card(modifier = Modifier
            .fillMaxWidth()
            // set padding for last item list for button space
            .padding(if (!isLastItem) PaddingValues(horizontal = 8.dp,
                vertical = 10.dp) else PaddingValues(start = 8.dp, end = 8.dp, top = 10.dp,
                bottom = 90.dp)),
            backgroundColor = AppTheme.colors.surface,
            shape = RoundedCornerShape(20.dp)
        ) {
            Row(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)) {

                val image = loadPictureNoneDefault(url = model.imageUrl).value
                image?.let { img ->
                    Image(
                        bitmap = img.asImageBitmap(),
                        contentDescription = "",
                        modifier = Modifier
                            .size(80.dp)
                            .animateAlpha(
                                delayMillis = 500,
                                durationMillis = 750)
                            .clip(RoundedCornerShape(15.dp)),
                        contentScale = ContentScale.Crop
                    )
                }

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = model.name,
                                color = AppTheme.colors.titleText,
                                style = AppTheme.typography.h7,
                            )
                            Text(
                                text = model.date,
                                modifier = Modifier.padding(bottom = 15.dp, top = 5.dp),
                                color = Color.LightGray,
                                style = AppTheme.typography.body1
                            )
                        }
                        Box(
                            modifier = Modifier
                                .background(brush = buttonEnabledGradient(),
                                    shape = RoundedCornerShape(20.dp), alpha = 0.2f
                                )
                                .align(Alignment.CenterVertically)) {

                            Row(modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(painter = painterResource(id = R.drawable.ic_star),
                                    contentDescription = null)
                                Spacer(modifier = Modifier.padding(start = 5.dp))
                                Text(
                                    text = model.rate,
                                    modifier = Modifier
                                        .textBrush(gradientText()), style = AppTheme.typography.h7,
                                    fontSize = 20.sp
                                )
                            }

                        }
                    }
                    Text(
                        style = AppTheme.typography.body1,
                        color = AppTheme.colors.titleText,
                        lineHeight = 25.sp,
                        text = model.description,
                        modifier = Modifier.padding(end = 5.dp)
                    )
                }
            }
        }
    }

}
