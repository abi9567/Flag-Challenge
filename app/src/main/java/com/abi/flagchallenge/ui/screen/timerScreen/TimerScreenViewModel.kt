package com.abi.flagchallenge.ui.screen.timerScreen

import androidx.lifecycle.ViewModel
import com.abi.flagchallenge.enums.TimerCurrentScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Timer
import java.util.TimerTask

class TimerScreenViewModel : ViewModel() {

    private val _currentScreen = MutableStateFlow(value = TimerCurrentScreen.TimeSelectionScreen)
    val currentScreen: StateFlow<TimerCurrentScreen> = _currentScreen.asStateFlow()

    private val _countDownTimer = MutableStateFlow<Long?>(value = null)
    val countDownTimer: StateFlow<Long?> = _countDownTimer.asStateFlow()

    private var timer: Timer? = null


    private fun setCurrentScreen(screen: TimerCurrentScreen) {
        _currentScreen.value = screen
    }

    fun setCountDownTimer(hour: Int, minute: Int, seconds: Int) {
        val totalTimeInLong =
            ((hour.toLong() * 3600) + (minute.toLong() * 60) + (seconds.toLong())) * 1000L
        _countDownTimer.value = totalTimeInLong
        setCurrentScreen(screen = TimerCurrentScreen.CountDownScreen)
        startTimer()
    }

    private fun startTimer() {
        if (timer != null) return
        timer = Timer()
        timer?.schedule(object : TimerTask() {
            override fun run() {
                if ((_countDownTimer.value ?: 0L) > 0) {
                    _countDownTimer.value = (_countDownTimer.value ?: 0L) - 1000L
                } else {
                    stopTimer()
                }
            }
        }, 0, 1000L)
    }

    fun stopTimer() {
        timer?.cancel()
        timer = null
    }
}