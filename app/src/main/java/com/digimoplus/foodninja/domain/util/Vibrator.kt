package com.digimoplus.foodninja.domain.util

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.VibratorManager

fun vibrator(context: Context, milliseconds: Long) {

    if (Build.VERSION.SDK_INT >= 31) {
        val vibratorManager =
            context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        val vibrator = vibratorManager.defaultVibrator
        vibrator.vibrate(VibrationEffect.createOneShot(milliseconds,
            VibrationEffect.DEFAULT_AMPLITUDE))
    }
}