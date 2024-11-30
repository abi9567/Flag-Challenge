package com.abi.flagchallenge.ui.screen.questionScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.abi.flagchallenge.R
import com.abi.flagchallenge.extenstions.formatCountdownTimer
import com.abi.flagchallenge.ui.common.HeightSpacer

@Composable
fun QuestionView(viewModel: QuestionViewModel) {

    val currentQuestion by viewModel.currentQuestion.collectAsState()
    val currentQuestionNumber by viewModel.currentSelectedIndex.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val timeLeftForAnswer by viewModel.timeRemainingForAnswer.collectAsState()
    val nextQuestionCountdownTimer by viewModel.nextQuestionCountdownTimer.collectAsState(initial = 10)
    val selectedOptionId by viewModel.selectedOptionId.collectAsState()

    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        QuestionComponent(questionNumber = currentQuestionNumber.plus(1),
            questions = currentQuestion,
            selectedOptionId = selectedOptionId,
            isTimerStopped = timeLeftForAnswer == 0L,
            selectedAnswer = viewModel::setSelectedOption
        )

        HeightSpacer(height = dimensionResource(id = R.dimen.margin_xl))

        AnimatedVisibility(visible = !isLoading) {
            RemainingTime(remainingTime = timeLeftForAnswer.formatCountdownTimer())
        }

        AnimatedVisibility (visible = isLoading) {
            LoadingNextQuestion(
                remainingTime = nextQuestionCountdownTimer
            )
        }
    }
}