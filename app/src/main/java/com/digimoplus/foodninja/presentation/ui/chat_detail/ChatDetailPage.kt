@file:OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)

package com.digimoplus.foodninja.presentation.ui.chat_detail

import android.view.ViewTreeObserver
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.domain.model.Message
import com.digimoplus.foodninja.network.soketio.MessageType
import com.digimoplus.foodninja.presentation.components.*
import com.digimoplus.foodninja.presentation.components.main_pages.PageMainBackgroundImage
import com.digimoplus.foodninja.presentation.components.util.animateAlpha
import com.digimoplus.foodninja.presentation.components.util.dps
import com.digimoplus.foodninja.presentation.components.util.gradientText
import com.digimoplus.foodninja.presentation.components.util.loadPicture
import com.digimoplus.foodninja.presentation.theme.AppTheme
import kotlinx.coroutines.launch

@Composable
fun ChatDetailPage(navController: NavController) {

    val viewModel: ChatDetailViewModel = hiltViewModel()
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val scope = rememberCoroutineScope()
    val view = LocalView.current

    DisposableEffect(view) {
        viewModel.registerTypeIng()
        viewModel.connect()
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            scope.launch { bringIntoViewRequester.bringIntoView() }
        }
        view.viewTreeObserver.addOnGlobalLayoutListener(listener)
        onDispose {
            viewModel.disconnect()
            view.viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
    }


    PageMainBackgroundImage(
        paddingValues = PaddingValues(
            start = 24.dp,
            end = 24.dp,
            top = 24.dps
        )
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.padding(bottom = 90.dp)) {
                BackButton {
                    navController.navigateUp()
                }
                Spacer(modifier = Modifier.padding(top = 8.dps))
                Text(
                    modifier = Modifier.width(180.dps),
                    text = "Chat",
                    color = AppTheme.colors.titleText,
                    style = AppTheme.typography.h4M
                )

                Spacer(modifier = Modifier.padding(top = 10.dps))

                ProfileCard()

                Spacer(modifier = Modifier.padding(top = 4.dps))

                LazyColumn(modifier = Modifier.fillMaxSize(),
                    state = viewModel.lazyListState) {
                    itemsIndexed(items = viewModel.messageList) { index, item ->
                        MessageCardItem(item)
                    }
                }

            }

            Box(modifier = Modifier.align(Alignment.BottomCenter)) {
                Column {
                    if (viewModel.visibleTypeIng.value) {
                        Text(
                            modifier = Modifier.padding(start = 8.dp),
                            text = "is typeing ...",
                            color = AppTheme.colors.titleText,
                            style = AppTheme.typography.body1,
                        )
                    }
                    Spacer(modifier = Modifier.padding(bottom = 4.dp))
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .bringIntoViewRequester(bringIntoViewRequester)
                        .padding(bottom = 10.dp), backgroundColor = AppTheme.colors.surface,
                        shape = RoundedCornerShape(25.dp)) {
                        Row(Modifier
                            .padding(vertical = 2.dp),
                            verticalAlignment = Alignment.CenterVertically) {
                            Box(modifier = Modifier.weight(1f)) {
                                ChatTextField(
                                    text = viewModel.text,
                                    onMessageChange = { viewModel.onChangeText(it) },
                                    placeHolder = "Type here ..."
                                )
                            }
                            IconButton(onClick = viewModel::sendMessage,
                                enabled = viewModel.text.value != "") {
                                Image(painter = painterResource(
                                    id = if (viewModel.text.value != "") R.drawable.ic_send else R.drawable.ic_send_disable),
                                    contentDescription = ""
                                )
                            }
                            Spacer(modifier = Modifier.padding(end = 16.dp))
                        }
                    }
                }
            }
        }

    }

}

@Composable
private fun MessageCardItem(item: Message) {
    when (item.messageType) {
        MessageType.SEND_MESSAGE -> {
            SendMessage(text = item.messageContent)
        }
        MessageType.RECEIVED_MESSAGE -> {
            ReceiveMessage(text = item.messageContent)
        }
        MessageType.NEW_USER -> {
            RoomMessage(text = "${item.userName} joined to chat")
        }
        MessageType.USER_LEFT -> {
            RoomMessage(text = "${item.userName} left the chat")
        }
    }
}

@Composable
private fun ProfileCard() {
    Card(
        modifier = Modifier
            .padding(),
        onClick = { },
        shape = RoundedCornerShape(20.dp),
        backgroundColor = AppTheme.colors.surface
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            val image = loadPicture(url = "https://digimoplus.ir/chat_p1.png").value
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
                    text = "Dianne Russell",
                    style = AppTheme.typography.h7,
                    color = AppTheme.colors.titleText
                )
                Spacer(modifier = Modifier.padding(top = 4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier
                        .size(6.dp)
                        .background(brush = gradientText(), shape = RoundedCornerShape(20.dp)))
                    Text(
                        text = " Online",
                        style = AppTheme.typography.body1,
                        color = AppTheme.colors.onTitleText)
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .size(45.dp)
                    .background(
                        color = AppTheme.colors.onPrimary.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(30.dp)
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_call),
                    modifier = Modifier
                        .width(18.dp),
                    contentDescription = null)

            }
            Spacer(modifier = Modifier.padding(end = 16.dp))
        }

    }
}
