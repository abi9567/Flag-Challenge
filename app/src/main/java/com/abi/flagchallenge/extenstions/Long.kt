package com.abi.flagchallenge.extenstions

import java.util.Locale

fun Long.formatCountdownTimer(): String {
    val seconds = (this / 1000) % 60
    return String.format(Locale.ROOT, format = "00:%02d", seconds)
}

fun Long?.formatCountDownTimerWithHour(): String {
    val hours = ((this ?: 0L) / 1000) / 3600
    val minutes = (((this ?: 0L) / 1000) % 3600) / 60
    val seconds = ((this ?: 0L) / 1000) % 60
    return String.format(Locale.ROOT, "%02d:%02d:%02d", hours, minutes, seconds)
}