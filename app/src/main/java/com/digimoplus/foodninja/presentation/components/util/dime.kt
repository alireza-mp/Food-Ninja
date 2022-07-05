package com.digimoplus.foodninja.presentation.components.util

import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import com.digimoplus.foodninja.presentation.appHeight
import com.digimoplus.foodninja.presentation.appWidth

//app height is 1/1000 % of display height
// value  * 0.001
@Stable
inline val Int.dps: Dp get() = Dp(value = appHeight * this)


@Stable
inline val Int.dpw: Dp get() = Dp(value = appWidth * this)
