package com.abi.flagchallenge.ui.screen.timerScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavController
import com.abi.flagchallenge.R
import com.abi.flagchallenge.enums.TimerCurrentScreen
import com.abi.flagchallenge.extenstions.navigateWithPop
import com.abi.flagchallenge.navigation.Screens
import com.abi.flagchallenge.ui.common.CustomScaffold

@Composable
fun TimerScreen(
    navController: NavController,
    viewModel: TimerScreenViewModel
) {
    val currentScreen by viewModel.currentScreen.collectAsState()
    val remainingTime by viewModel.countDownTimer.collectAsState()

    LaunchedEffect(key1 = remainingTime) {
        if (remainingTime == 0L) {
            navController.navigateWithPop(
                route = Screens.QuestionScreen.route,
                popUpRoute = Screens.TimerScreen.route
            )
        }
    }

    CustomScaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(top = dimensionResource(id = R.dimen.margin_xl))
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (currentScreen) {
                TimerCurrentScreen.TimeSelectionScreen -> TimePickerView(viewModel = viewModel)
                TimerCurrentScreen.CountDownScreen -> CountDownView(viewModel = viewModel)
            }
        }
    }
}