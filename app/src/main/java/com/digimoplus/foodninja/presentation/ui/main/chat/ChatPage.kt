@file:OptIn(ExperimentalMaterialApi::class)

package com.digimoplus.foodninja.presentation.ui.main.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.digimoplus.foodninja.presentation.Screens
import com.digimoplus.foodninja.presentation.components.util.animateAlpha
import com.digimoplus.foodninja.presentation.components.util.dps
import com.digimoplus.foodninja.presentation.components.util.loadPicture
import com.digimoplus.foodninja.presentation.theme.AppTheme

@Composable
fun ChatPage(navController: NavController) {

    Column() {

        Spacer(modifier = Modifier.padding(top = 25.dps))

        Text(
            modifier = Modifier.width(180.dps),
            text = "Chat",
            color = AppTheme.colors.titleText,
            style = AppTheme.typography.h4M
        )

        Spacer(modifier = Modifier.padding(top = 12.dps))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                ChatItemCard(
                    navController = navController,
                    name = "Dianne Russell",
                    description = "Your Order Just Arrived!",
                    time = "20:00",
                    imageUrl = "https://digimoplus.ir/chat_p1.png"
                )
                ChatItemCard(
                    navController = navController,
                    name = "Guy Hawkins",
                    description = "Your Order Just Arrived!",
                    time = "18:12",
                    imageUrl = "https://digimoplus.ir/chat_p2.png"
                )
                ChatItemCard(
                    navController = navController,
                    name = "Leslie Alexander",
                    description = "Your Order Just Arrived!",
                    time = "16:42",
                    imageUrl = "https://digimoplus.ir/chat_p3.png"
                )
            }
        }

    }
}

@Composable
fun ChatItemCard(
    navController: NavController,
    name: String,
    description: String,
    time: String,
    imageUrl: String,
) {
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 1.dp),
        onClick = {
            navController.navigate(Screens.ChatDetail.route)
        },
        shape = RoundedCornerShape(20.dp),
        backgroundColor = AppTheme.colors.surface
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 18.dp, end = 16.dp),
                text = time,
                style = AppTheme.typography.body1,
                color = AppTheme.colors.onTitleText)

            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                val image = loadPicture(url = imageUrl).value
                image?.let { img ->
                    Card(modifier = Modifier.padding(
                        start = 12.dp,
                        top = 16.dp,
                        bottom = 16.dp
                    ),
                        elevation = 0.dp,
                        backgroundColor = Color.Transparent) {
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
                }

                Column(modifier = Modifier.padding(start = 15.dp)) {
                    Text(
                        text = name,
                        style = AppTheme.typography.h7,
                        color = AppTheme.colors.titleText
                    )
                    Spacer(modifier = Modifier.padding(top = 4.dp))
                    Text(
                        text = description,
                        style = AppTheme.typography.body1,
                        color = AppTheme.colors.onTitleText)
                }
                Spacer(modifier = Modifier.padding(end = 16.dp))
            }
        }


    }
}