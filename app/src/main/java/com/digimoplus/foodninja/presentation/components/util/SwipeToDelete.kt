package com.digimoplus.foodninja.presentation.components.util


import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.horizontalDrag
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.unit.IntOffset
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

/* from https://developer.android.com/codelabs/jetpack-compose-animation#7 */

@Composable
fun Modifier.swipeToDelete(
    onDelete: () -> Unit,
    onBack: () -> Unit,
): Modifier = composed {

    // call delete one just one time in pointer change
    var deleteCallBack: (() -> Unit)? = {
        onDelete()
    }

    // This Animatable stores the horizontal offset for the element.
    val offsetX = remember { Animatable(0f) }

    pointerInput(Unit) {

        coroutineScope {
            while (true) {
                // Wait for a touch down event.
                val pointerId = awaitPointerEventScope {
                    awaitFirstDown().id

                }
                offsetX.stop()
                // Wait for drag events.
                awaitPointerEventScope {
                    horizontalDrag(pointerId) { change ->

                        // Record the position after offset
                        val horizontalDragOffset = offsetX.value + change.positionChange().x

                        // check if drag from right to left
                        if (horizontalDragOffset > -195 && horizontalDragOffset < 0) {
                            launch {

                                // animate to new offset
                                offsetX.snapTo(horizontalDragOffset)
                                if (offsetX.value < -145) {
                                    // call delete fun
                                    deleteCallBack?.invoke()
                                    // disable call back after called one time
                                    deleteCallBack = null
                                }
                            }
                            change.consume()
                        }
                    }
                }

                launch {
                    // enable delete call back after user unDrag
                    deleteCallBack = {
                        onDelete()
                    }
                    // call on back because user unDrag
                    onBack()
                    // animate to offset  because user unDrag
                    offsetX.animateTo(0f)
                }
            }
        }
    }
        // Apply the horizontal offset to the element.
        .offset { IntOffset(offsetX.value.roundToInt(), 0) }
}