package com.abi.flagchallenge.ui.screen.questionScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.abi.flagchallenge.ui.common.CustomScaffold

@Composable
fun QuestionScreen(
    navController: NavController,
    viewModel: QuestionViewModel
) {
    CustomScaffold { paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues = paddingValues)
            .fillMaxSize()
        ) {

        }
    }
}