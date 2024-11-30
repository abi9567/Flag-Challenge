package com.abi.flagchallenge.navigation

sealed class Screens(val route: String) {
    data object SplashScreen : Screens(route = "splash_screen")
    data object TimerScreen : Screens(route = "timer_screen")
    data object QuestionScreen : Screens(route = "question_screen")
}