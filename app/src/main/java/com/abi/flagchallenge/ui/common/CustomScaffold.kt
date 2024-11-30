package com.abi.flagchallenge.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.abi.flagchallenge.R
import com.abi.flagchallenge.ui.theme.AppBarColor

@Composable
fun CustomScaffold(
    content : @Composable (PaddingValues) -> Unit
) {
    Scaffold(topBar = {
        Box(modifier = Modifier
            .background(color = AppBarColor)
            .statusBarsPadding()
            .padding(bottom = dimensionResource(id = R.dimen.margin_large))
            .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
        }
    }) { paddingValues ->
        content(paddingValues)
    }
}