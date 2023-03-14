@file:OptIn(ExperimentalMaterialApi::class)

package com.digimoplus.foodninja.presentation.ui.main.chat

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.digimoplus.foodninja.presentation.components.util.NetworkImage
import com.digimoplus.foodninja.presentation.components.util.coloredShadow
import com.digimoplus.foodninja.presentation.navigation.Screens
import com.digimoplus.foodninja.presentation.theme.AppTheme

@Composable
fun ChatPage(navController: NavController) {

    Column(modifier = Modifier.padding(top = 50.dp)) {

        Text(
            modifier = Modifier.padding(horizontal = 20.dp),
            text = "Chat",
            color = AppTheme.colors.titleText,
            style = AppTheme.typography.h4M,
            fontSize = 25.sp,
            fontWeight = FontWeight.W400,
        )

        Spacer(modifier = Modifier.padding(top = 12.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                ChatItemCard(
                    navController = navController,
                    name = "Dianne Russell",
                    description = "Your Order Just Arrived!",
                    time = "20:00",
                    imageUrl = "https://digimoplus.ir/chat_p1.png",
                    paddingValues = PaddingValues(
                        end = 20.dp, start = 20.dp, top = 20.dp
                    ),
                )
                ChatItemCard(
                    navController = navController,
                    name = "Guy Hawkins",
                    description = "Your Order Just Arrived!",
                    time = "18:12",
                    imageUrl = "https://digimoplus.ir/chat_p2.png",
                    paddingValues = PaddingValues(
                        end = 20.dp, start = 20.dp, top = 20.dp
                    ),
                )
                ChatItemCard(
                    navController = navController,
                    name = "Leslie Alexander",
                    description = "Your Order Just Arrived!",
                    time = "16:42",
                    imageUrl = "https://digimoplus.ir/chat_p3.png",
                    paddingValues = PaddingValues(
                        end = 20.dp, start = 20.dp, top = 20.dp
                    ),
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
    paddingValues: PaddingValues,
) {
    Card(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth()
            .height(82.dp)
            .coloredShadow(
                offsetX = 8.dp,
                offsetY = 10.dp,
            ),
        onClick = {
            navController.navigate(Screens.ChatDetail.route)
        },
        shape = RoundedCornerShape(22.dp),
        backgroundColor = AppTheme.colors.surface,
        elevation = 0.dp,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 8.dp),
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 10.dp, end = 4.dp),
                text = time,
                style = AppTheme.typography.body1,
                color = AppTheme.colors.onTitleText,
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
            )

            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

                NetworkImage(url = imageUrl, size = 62.dp)

                Column(modifier = Modifier.padding(start = 17.dp)) {
                    Text(
                        text = name,
                        style = AppTheme.typography.h7,
                        color = AppTheme.colors.titleText,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.W400,
                    )
                    Spacer(modifier = Modifier.padding(top = 8.dp))
                    Text(
                        text = description,
                        style = AppTheme.typography.body1,
                        color = AppTheme.colors.onTitleText,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W400,
                    )
                }
            }
        }


    }
}