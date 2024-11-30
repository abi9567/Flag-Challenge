package com.abi.flagchallenge.ui.screen.splashScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.abi.flagchallenge.extenstions.navigateWithPop
import com.abi.flagchallenge.navigation.Screens
import com.abi.flagchallenge.ui.screen.questionScreen.QuestionViewModel
import com.abi.flagchallenge.ui.theme.AppBarColor
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    viewModel: QuestionViewModel,
    navController: NavController
) {
    val gameSettings by viewModel.gameSettings.collectAsState(initial = null)

    LaunchedEffect(key1 = Unit) {
        delay(timeMillis = 2000)
        if (gameSettings?.isGamePending == true) {
            navController.navigateWithPop(route = Screens.QuestionScreen.route,
                popUpRoute = Screens.SplashScreen.route
            )
            return@LaunchedEffect
        }

        navController.navigateWithPop(route = Screens.TimerScreen.route,
            popUpRoute = Screens.SplashScreen.route
        )
    }

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = AppBarColor)
    }
}