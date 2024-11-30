package com.abi.flagchallenge.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.abi.flagchallenge.ui.screen.questionScreen.QuestionScreen
import com.abi.flagchallenge.ui.screen.questionScreen.QuestionViewModel
import com.abi.flagchallenge.ui.screen.splashScreen.SplashScreen
import com.abi.flagchallenge.ui.screen.timerScreen.TimerScreen
import com.abi.flagchallenge.ui.screen.timerScreen.TimerScreenViewModel

@Composable
fun NavigationGraph(
    navController: NavHostController,
    questionViewModel: QuestionViewModel
) {
    NavHost(navController = navController, startDestination = Screens.SplashScreen.route) {

        composable(route = Screens.SplashScreen.route) {
            SplashScreen(viewModel = questionViewModel, navController = navController)
        }

        composable(route = Screens.TimerScreen.route) {
            val viewModel: TimerScreenViewModel = viewModel()
            TimerScreen(viewModel = viewModel, navController = navController)
        }

        composable(route = Screens.QuestionScreen.route) {
//            val viewModel : QuestionViewModel = hiltViewModel()
            QuestionScreen(viewModel = questionViewModel, navController = navController)
        }
    }
}