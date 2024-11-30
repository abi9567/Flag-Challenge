package com.abi.flagchallenge.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.abi.flagchallenge.navigation.NavigationGraph
import com.abi.flagchallenge.navigation.Screens
import com.abi.flagchallenge.ui.screen.questionScreen.QuestionViewModel
import com.abi.flagchallenge.ui.theme.FlagChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val questionViewModel: QuestionViewModel by viewModels()
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            navController = rememberNavController()
            FlagChallengeTheme {
                NavigationGraph(
                    navController = navController,
                    questionViewModel = questionViewModel
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivityAbi", "OnResume")
        questionViewModel.calculateAppNotRunningTimeAndContinueGame()
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivityAbi", "OnStop")
        if (navController.currentBackStackEntry?.destination?.route == Screens.QuestionScreen.route) {
            questionViewModel.saveQuestionDetailsToDataStore()
        }
    }
}