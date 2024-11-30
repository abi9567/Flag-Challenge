package com.abi.flagchallenge.ui.screen.questionScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.abi.flagchallenge.enums.CurrentScreen
import com.abi.flagchallenge.extenstions.navigateWithPop
import com.abi.flagchallenge.navigation.Screens
import com.abi.flagchallenge.ui.common.CustomScaffold
import kotlinx.coroutines.launch

@Composable
fun QuestionScreen(
    viewModel: QuestionViewModel,
    navController: NavController
) {
    val scope = rememberCoroutineScope()
    val currentScreen by viewModel.currentScreen.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.setNextQuestion()
    }

    CustomScaffold { paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues = paddingValues)
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when(currentScreen) {
                CurrentScreen.QuestionScreen -> {
                    QuestionView(viewModel = viewModel)
                }
                CurrentScreen.GameOverScreen -> {
                    GameOverScreen(viewModel = viewModel,
                        onRestartGame = {
                            scope.launch {
                                viewModel.saveQuestionDetailsToDataStore()
                                navController.navigateWithPop(route = Screens.TimerScreen.route,
                                    popUpRoute = Screens.QuestionScreen.route
                                )
                            }
                    })
                }
            }
        }
    }
}