package com.abi.flagchallenge.ui.screen.questionScreen

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abi.flagchallenge.data.GameSettings
import com.abi.flagchallenge.data.Questions
import com.abi.flagchallenge.enums.CurrentScreen
import com.abi.flagchallenge.extenstions.showToast
import com.abi.flagchallenge.utils.DataStoreUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Timer
import java.util.TimerTask
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val dataStoreUtil: DataStoreUtil
) : ViewModel() {

    private val tag = "QuestionViewModel"
    private val questions = Questions.QUESTIONS
    private val singleQuestionTimeInSeconds = 30
    private val questionsIntervalInSeconds = 10
    private val singleQuestionTimeInLong = singleQuestionTimeInSeconds * 1000L

    private var timer: Timer? = null
    private lateinit var calendar: Calendar

    private val _currentSelectedIndex = MutableStateFlow(value = 0)
    val currentSelectedIndex: StateFlow<Int> = _currentSelectedIndex.asStateFlow()

    private val _isLoading = MutableStateFlow(value = false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _currentQuestion = MutableStateFlow<Questions?>(value = null)
    val currentQuestion: StateFlow<Questions?> = _currentQuestion.asStateFlow()

    private val _timeRemainingForAnswer = MutableStateFlow(value = singleQuestionTimeInLong)
    val timeRemainingForAnswer: StateFlow<Long> = _timeRemainingForAnswer.asStateFlow()

    private val _selectedOptionId = MutableStateFlow<Int?>(value = null)
    val selectedOptionId: StateFlow<Int?> = _selectedOptionId.asStateFlow()

    private val _nextQuestionCountdownTimer = MutableStateFlow<Int?>(value = null)
    val nextQuestionCountdownTimer: StateFlow<Int?> = _nextQuestionCountdownTimer.asStateFlow()

    private val _isGameFinished = MutableStateFlow(value = false)
    val isGameFinished: StateFlow<Boolean> = _isGameFinished.asStateFlow()

    private val _gameScore = MutableStateFlow(value = 0)
    val gameScore: StateFlow<Int> = _gameScore

    private val _currentScreen = MutableStateFlow(value = CurrentScreen.QuestionScreen)
    val currentScreen: StateFlow<CurrentScreen> = _currentScreen.asStateFlow()

    val gameSettings = dataStoreUtil.getGameSettings

    init {
//        setNextQuestion()
        Log.d(tag, "Init Block")
    }

    fun setSelectedOption(selectedOptionId: Int?) {
        _selectedOptionId.value = selectedOptionId
    }

    private fun setCurrentScreen(screen: CurrentScreen) {
        _currentScreen.value = screen
    }

    private fun increaseGameScore() {
        _gameScore.value += 1
    }

    private fun isGameFinished(): Boolean {
        _isGameFinished.value = _currentSelectedIndex.value.plus(1) == questions.size
        return _isGameFinished.value
    }

    private suspend fun setNextQuestionCountdown() {
        var timer = questionsIntervalInSeconds
        while (timer >= 0) {
            _nextQuestionCountdownTimer.value = timer
            if (timer == 0) {
                setLoading()
                if (isGameFinished()) {
                    setCurrentScreen(screen = CurrentScreen.GameOverScreen)
                    context.showToast(message = "Game Over")
                    return
                }
                setNextQuestionIndex()
                setNextQuestion()
            }
            delay(timeMillis = 1000)
            timer--
        }
    }

    fun checkAnswer() {
        viewModelScope.launch {
            if (_currentQuestion.value?.answerId == _selectedOptionId.value) {
                increaseGameScore()
            }
            setLoading()
            setNextQuestionCountdown()
        }
    }

    private fun setLoading() {
        _isLoading.value = !(_isLoading.value)
    }

    private fun setNextQuestionIndex() {
        _currentSelectedIndex.value += 1
    }

    fun setNextQuestion() {
        resetTimer()
        _currentQuestion.value = questions.getOrNull(_currentSelectedIndex.value)
        setSelectedOption(selectedOptionId = null)
        startTimer()
    }

    private fun startTimer() {
        if (timer != null) return
        timer = Timer()
        timer?.schedule(object : TimerTask() {
            override fun run() {
                if (_timeRemainingForAnswer.value > 0) {
                    _timeRemainingForAnswer.value -= 1000L
                } else {
                    stopTimer()
                    checkAnswer()
                }
            }
        }, 1000, 1000L)
    }

    fun stopTimer() {
        timer?.cancel()
        timer = null
    }

    private fun resetTimer() {
        stopTimer()
        _timeRemainingForAnswer.value = singleQuestionTimeInLong
    }

    fun calculateAppNotRunningTimeAndContinueGame() {
        viewModelScope.launch {
            val gameSettings = dataStoreUtil.getGameSettings.first()
            if (gameSettings?.isGamePending != true) return@launch
            val calendar = Calendar.getInstance()
            val currentReEnterTime = calendar.timeInMillis
            val differenceInSeconds =
                ((currentReEnterTime - (gameSettings.exitTime ?: 0L)) / 1000L).toInt()
            val numberOfQuestionsSkipped = differenceInSeconds / singleQuestionTimeInSeconds
            val nextIndexToStart = (gameSettings.currentIndex ?: 0) + numberOfQuestionsSkipped
            val isQuestionsCompletedWhileUserIsAway = nextIndexToStart > (questions.size - 1)
            _gameScore.value = gameSettings.currentScore ?: 0

            if (isQuestionsCompletedWhileUserIsAway) {
                dataStoreUtil.storeGameSettings(gameSettings = null)
                return@launch
            }
            _currentSelectedIndex.value = nextIndexToStart
//            setNextQuestion()
            Log.d(
                tag,
                "Difference -> $differenceInSeconds, Skipped -> $numberOfQuestionsSkipped, last index -> ${gameSettings.currentIndex}, new index -> ${_currentSelectedIndex.value}"
            )
        }
    }


    fun saveQuestionDetailsToDataStore() {
        viewModelScope.launch {
            if (_currentScreen.value == CurrentScreen.GameOverScreen) {
                dataStoreUtil.storeGameSettings(gameSettings = null)
                Log.d(tag, "Settings -> null")
                return@launch
            }
            calendar = Calendar.getInstance()
            val gameSettings = GameSettings(
                exitTime = calendar.timeInMillis,
                isGamePending = true,
                currentIndex = _currentSelectedIndex.value,
                currentScore = _gameScore.value
            )
            Log.d(tag, "Settings -> $gameSettings")
            dataStoreUtil.storeGameSettings(gameSettings)
        }
    }
}