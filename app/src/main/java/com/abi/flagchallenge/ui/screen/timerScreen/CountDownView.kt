package com.abi.flagchallenge.ui.screen.timerScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.W700
import androidx.compose.ui.unit.sp
import com.abi.flagchallenge.R
import com.abi.flagchallenge.extenstions.formatCountDownTimerWithHour
import com.abi.flagchallenge.ui.theme.BackgroundColor

@Composable
fun CountDownView(viewModel: TimerScreenViewModel) {

    val remainingTime by viewModel.countDownTimer.collectAsState(initial = 0L)

    Column(
        modifier = Modifier
            .background(color = BackgroundColor)
            .padding(vertical = dimensionResource(id = R.dimen.margin_xl))
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.margin_xl))
    ) {
        Text(
            text = stringResource(id = R.string.challenge_will_start_in),
            style = TextStyle(fontSize = 18.sp)
        )

        Text(
            text = remainingTime.formatCountDownTimerWithHour(),
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = W700
            )
        )
    }
}