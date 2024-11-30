package com.abi.flagchallenge.ui.screen.timerScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import com.abi.flagchallenge.R
import com.abi.flagchallenge.ui.common.HeightSpacer
import com.abi.flagchallenge.ui.theme.BackgroundColor

@Composable
fun CountdownPickerView(title: String, onValueChange: (Int) -> Unit) {
    var text by remember { mutableStateOf(value = "") }
    val focusManager = LocalFocusManager.current

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = title)
        HeightSpacer(height = dimensionResource(id = R.dimen.margin_normal))
        OutlinedTextField(value = text,
            shape = RoundedCornerShape(size = dimensionResource(id = R.dimen.margin_large)),
            textStyle = TextStyle(
                fontSize = 20.sp,
                fontWeight = W600,
                textAlign = TextAlign.Center
            ),
            singleLine = true,
            placeholder = {
                Text(
                    text = "00",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = W600,
                        textAlign = TextAlign.Center
                    ),
                    color = Color.Gray.copy(alpha = 0.45F)
                )
            },
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
            }),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.NumberPassword
            ), colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = BackgroundColor,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            ),
            modifier = Modifier
                .size(size = 65.dp)
                .clip(RoundedCornerShape(size = dimensionResource(id = R.dimen.margin_large)))
                .background(color = BackgroundColor),
            onValueChange = {
                if (it.length > 2) return@OutlinedTextField
                if (!it.isDigitsOnly()) return@OutlinedTextField
                if (it.toInt() > 60) return@OutlinedTextField
                text = it
                onValueChange(it.toInt())
            }
        )
    }
}