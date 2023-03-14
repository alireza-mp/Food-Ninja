package com.digimoplus.foodninja.presentation.components.util

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.digimoplus.foodninja.R
import com.skydoves.landscapist.glide.GlideImage

const val DEFAULT_RESTAURANT_CARD_ITEM_IMAGE = R.drawable.default_image

@SuppressLint("UnrememberedMutableState")
@Composable
fun loadPicture(url: String): MutableState<Bitmap?> {

    val bitmapState: MutableState<Bitmap?> = mutableStateOf(null)

    // get local default image
    Glide.with(LocalContext.current)
        .asBitmap()
        .load(DEFAULT_RESTAURANT_CARD_ITEM_IMAGE)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmapState.value = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {}
        })


    //get network image
    Glide.with(LocalContext.current)
        .asBitmap()
        .load(url)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmapState.value = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {}
        })

    return bitmapState
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun loadPictureNoneDefault(url: String): MutableState<Bitmap?> {

    val bitmapState: MutableState<Bitmap?> = mutableStateOf(null)

    //get network image
    Glide.with(LocalContext.current)
        .asBitmap()
        .load(url)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmapState.value = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {}
        })

    return bitmapState
}


@Composable
fun NetworkImage(url: String, size: Dp) {
    GlideImage(
        imageModel = url,
        modifier = Modifier
            .size(size)
            .clip(RoundedCornerShape(15.dp)),
    )
}

@Composable
fun NetworkImage(url: String, modifier: Modifier = Modifier, cornerRadius: Dp = 15.dp) {
    GlideImage(
        imageModel = url,
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius)),
    )
}