package com.abi.flagchallenge.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.abi.flagchallenge.ui.screen.questionScreen.QuestionScreen
import com.abi.flagchallenge.ui.screen.questionScreen.QuestionViewModel
import com.abi.flagchallenge.ui.screen.timerScreen.TimerScreen

@Composable
fun NavigationGraph(navController: NavHostController,
                    startScreen : String = Screens.TimerScreen.route
) {
    NavHost(navController = navController, startDestination = startScreen) {
        composable(route = Screens.TimerScreen.route) {
            TimerScreen()
        }

        composable(route = Screens.QuestionScreen.route) {
            val viewModel : QuestionViewModel = hiltViewModel()
            QuestionScreen(navController = navController, viewModel = viewModel)
        }
    }
}