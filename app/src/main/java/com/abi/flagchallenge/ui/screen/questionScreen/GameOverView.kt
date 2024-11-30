package com.abi.flagchallenge.ui.screen.questionScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.abi.flagchallenge.R
import com.abi.flagchallenge.ui.common.HeightSpacer
import com.abi.flagchallenge.ui.theme.AppBarColor

@Composable
fun GameOverScreen(
    viewModel: QuestionViewModel,
    onRestartGame: () -> Unit
) {
    val score by viewModel.gameScore.collectAsState()

    Column(
        modifier = Modifier
            .padding(vertical = dimensionResource(id = R.dimen.margin_large))
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.game_over),
            style = MaterialTheme.typography.displaySmall
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.score),
                style = MaterialTheme.typography.titleLarge,
                color = AppBarColor
            )

            Text(
                text = " : $score/15",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.W700
            )
        }

        HeightSpacer(height = dimensionResource(id = R.dimen.margin_large))

        Button(
            onClick = onRestartGame,
            colors = ButtonDefaults.buttonColors(containerColor = AppBarColor)
        ) {
            Text(
                text = "Play Again",
                color = Color.White
            )
        }
    }
}