package com.digimoplus.foodninja.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.digimoplus.foodninja.presentation.theme.AppTheme


@Composable
fun MenuDetailAlertDialog(
    state: MutableState<Boolean>,
    onNoClick: () -> Unit,
    onYesClick: () -> Unit,
) {
    if (state.value)
        Dialog(onDismissRequest = { state.value = false }) {
            CustomAlertDialogUi(
                onNoClick = onNoClick,
                onYesClick = onYesClick
            )
        }
}

@Composable
private fun CustomAlertDialogUi(
    onNoClick: () -> Unit,
    onYesClick: () -> Unit,
) {
    Card(
        backgroundColor = AppTheme.colors.surface,
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp)
            ) {
                Text(
                    text = "You bought from another restaurant",
                    lineHeight = 20.sp,
                    color = AppTheme.colors.titleText,
                    style = AppTheme.typography.h7,
                )
                Spacer(modifier = Modifier.padding(top = 12.dp))
                Text(
                    text = "Do you want to remove the rest of the options?",
                    color = AppTheme.colors.titleText,
                    lineHeight = 20.sp,
                    style = AppTheme.typography.body1,
                )
                Spacer(modifier = Modifier.padding(top = 16.dp))


            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
                .background(color = AppTheme.colors.onPrimary),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(onClick = onNoClick, modifier = Modifier
                    .padding(0.dp)
                    .fillMaxHeight()
                    .weight(1f)) {
                    Text(
                        text = "No",
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White,
                        style = AppTheme.typography.body,
                        textAlign = TextAlign.Center,

                        )
                }
                TextButton(onClick = onYesClick, modifier = Modifier
                    .padding(0.dp)
                    .fillMaxHeight()
                    .weight(1f)) {
                    Text(
                        text = "Okay",
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White,
                        style = AppTheme.typography.body,
                        textAlign = TextAlign.Center,

                        )
                }
            }
        }
    }
}












