package com.abi.flagchallenge.ui.screen.timerScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.abi.flagchallenge.R
import com.abi.flagchallenge.ui.common.HeightSpacer
import com.abi.flagchallenge.ui.common.WidthSpacer
import com.abi.flagchallenge.ui.theme.AppBarColor

@Composable
fun TimePickerView(
    viewModel: TimerScreenViewModel
) {
    var hour by remember { mutableIntStateOf(value = 0) }
    var minute by remember { mutableIntStateOf(value = 0) }
    var seconds by remember { mutableIntStateOf(value = 0) }

    Row(
        modifier = Modifier
            .padding(top = dimensionResource(id = R.dimen.margin_large))
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        CountdownPickerView(title = "Hour",
            onValueChange = { hour = it }
        )

        WidthSpacer(width = dimensionResource(id = R.dimen.margin_xl))

        CountdownPickerView(title = "Minute",
            onValueChange = { minute = it }
        )

        WidthSpacer(width = dimensionResource(id = R.dimen.margin_xl))

        CountdownPickerView(title = "Seconds",
            onValueChange = { seconds = it }
        )
    }

    HeightSpacer(height = dimensionResource(id = R.dimen.margin_large))

    Button(
        onClick = {
            if (hour == 0 && minute == 0 && seconds == 0) return@Button
            viewModel.setCountDownTimer(hour = hour, minute = minute, seconds = seconds)
        },
        colors = ButtonDefaults.buttonColors(containerColor = AppBarColor)
    ) {
        Text(text = "Save", color = Color.White)
    }
}