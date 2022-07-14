package com.digimoplus.foodninja.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.domain.model.Basket
import com.digimoplus.foodninja.presentation.components.util.*
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.digimoplus.foodninja.presentation.util.vibrator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun AnimatableBasketCardItem(
    item: Basket,
    coroutineScope: CoroutineScope,
    enableBottomPadding: Boolean,
    onDelete: (id: Int) -> Unit,
    onItemCount: (id: Int, count: Int) -> Unit,
) {
    // animate height card
    val heightState = remember {
        mutableStateOf(100.dp)
    }
    // animate alpha card
    val alphaState = remember {
        mutableStateOf(1f)
    }

    BasketCardItem(
        modifier = Modifier
            // space from last item in list (Pay Card)
            .padding(
                if (enableBottomPadding) PaddingValues(vertical = 10.dp)
                else PaddingValues(top = 10.dp, bottom = 260.dp))
            // animate height and alpha item
            .removeAnimItem(
                alphaState = alphaState,
                heightState = heightState
            ),
        item = item,
        onDelete = { id ->
            coroutineScope.launch {
                alphaState.value = 0f
                heightState.value = 0.dp
                // wait animations end
                delay(501)
                //call delete
                onDelete(id)
            }
        },
        onItemCount = { id, count -> onItemCount(id, count) },
    )
}

@Composable
private fun BasketCardItem(
    item: Basket,
    modifier: Modifier = Modifier,
    onDelete: (id: Int) -> Unit,
    onItemCount: (id: Int, count: Int) -> Unit,
) {
    val context = LocalContext.current
    // in onBack check if onDelete called or not
    var deleteCallBack = false
    // item count state
    val itemCount = remember {
        mutableStateOf(item.count)
    }
    // animate trash icon
    val trashIcon by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.trash))
    val trashIconProgress = remember {
        mutableStateOf(0f)
    }
    val trashIconVisibility = remember {
        mutableStateOf(false)
    }


    Card(
        modifier = modifier
            .fillMaxWidth(),
        backgroundColor = AppTheme.colors.primaryVariant,
        shape = RoundedCornerShape(25.dp)
    ) {

        // Background orange card
        Box(modifier = Modifier.fillMaxSize()) {

            Box(modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 28.dp)
            ) {
                if (trashIconVisibility.value)
                    LottieAnimation(
                        trashIcon,
                        clipSpec = LottieClipSpec.Progress(0f, trashIconProgress.value),
                    )
            }

        }

        // Main card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .swipeToDelete(
                    onBack = {
                        trashIconProgress.value = 0.1f
                        trashIconVisibility.value = false
                        if (deleteCallBack)
                            onDelete(item.id)
                    },
                    onDelete = {
                        trashIconVisibility.value = true
                        trashIconProgress.value = 1f
                        vibrator(context, 150)
                        deleteCallBack = true
                    }
                ),
            backgroundColor = AppTheme.colors.surface,
            shape = RoundedCornerShape(25.dp),
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically) {

                val image = loadPictureNoneDefault(url = item.imageUrl).value
                image?.let { img ->
                    Image(
                        bitmap = img.asImageBitmap(),
                        contentDescription = "",
                        modifier = Modifier
                            .size(70.dp)
                            .animateAlpha(
                                delayMillis = 500,
                                durationMillis = 750)
                            .clip(RoundedCornerShape(15.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(modifier = Modifier.padding(start = 8.dp))
                Column {
                    Text(
                        text = item.name,
                        color = AppTheme.colors.titleText,
                        style = AppTheme.typography.h7,
                        fontSize = 15.sp
                    )
                    Spacer(modifier = Modifier.padding(top = 4.dp))
                    Text(
                        text = item.restoName,
                        color = Color.LightGray,
                        style = AppTheme.typography.h7,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.padding(top = 4.dp))
                    Text(
                        text = "${item.price}$",
                        modifier = Modifier
                            .textBrush(gradientText()),
                        style = AppTheme.typography.h7,
                        fontSize = 20.sp
                    )
                }

                Spacer(modifier = Modifier.weight(1f))
                BasketNumbers(
                    text = itemCount.value.toString(),
                    onPlus = {
                        // update count form item and database
                        itemCount.value = itemCount.value + 1
                        onItemCount(item.id, itemCount.value)
                    },
                    onMinus = {
                        if (itemCount.value != 1) {
                            // update count form item and database
                            itemCount.value = itemCount.value - 1
                            onItemCount(item.id, itemCount.value)
                        } else {
                            // delete item when count == 0
                            onDelete(item.id)
                        }
                    })
            }
        }
    }
}

