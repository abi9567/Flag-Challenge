package com.abi.flagchallenge.ui.screen.questionScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abi.flagchallenge.R
import com.abi.flagchallenge.data.Questions
import com.abi.flagchallenge.ui.common.HeightSpacer
import com.abi.flagchallenge.ui.common.WidthSpacer
import com.abi.flagchallenge.ui.theme.AppBarColor
import com.abi.flagchallenge.ui.theme.BackgroundColor
import com.abi.flagchallenge.ui.theme.BorderColor
import com.abi.flagchallenge.ui.theme.CorrectAnswerColor
import com.abi.flagchallenge.ui.theme.QuestionNumberBackgroundColors
import com.abi.flagchallenge.ui.theme.WrongAnswerColor

@Composable
fun OptionSelectionButton(
    modifier: Modifier = Modifier,
    isSelected : Boolean,
    isRightAnswer : Boolean?,
    isWrongAnswer : Boolean?,
    onClick : () -> Unit,
    countryName : String?
) {
    Column(modifier = modifier
        .fillMaxWidth()
        .animateContentSize()) {
        Row(modifier = Modifier
            .clip(shape = RoundedCornerShape(size = dimensionResource(id = R.dimen.margin_normal)))
            .clickable { onClick() }
            .border(
                width = 1.2.dp, color = if (isSelected) Color.Transparent else BorderColor,
                shape = RoundedCornerShape(size = dimensionResource(id = R.dimen.margin_normal))
            )
            .background(color = if (isSelected) AppBarColor else Color.Transparent)
            .padding(all = dimensionResource(id = R.dimen.margin_normal))
            .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = countryName ?: "",
                style = MaterialTheme.typography.labelLarge,
                color = if (isSelected) Color.White else BorderColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        AnimatedVisibility(visible = isRightAnswer == true) {
            Text(text = stringResource(id = R.string.right),
                style = TextStyle(fontSize = 14.sp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = CorrectAnswerColor
            )
        }

        AnimatedVisibility(visible = isWrongAnswer == true) {
            Text(text = stringResource(id = R.string.wrong),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(fontSize = 14.sp),
                color = WrongAnswerColor
            )
        }
    }
}

@Composable
fun QuestionNumber(questionNumber : Int?) {
    Box(modifier = Modifier
        .clip(shape = RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp))
        .background(brush = Brush.linearGradient(colors = QuestionNumberBackgroundColors))
        .padding(
            horizontal = dimensionResource(id = R.dimen.margin_large),
            vertical = dimensionResource(id = R.dimen.margin_normal)
        ),
        contentAlignment = Alignment.Center
    ) {
        Box(modifier = Modifier
            .clip(shape = CircleShape)
            .background(color = AppBarColor)
            .size(size = 40.dp), contentAlignment = Alignment.Center
        ) {
            Text(text = "${questionNumber ?: 0}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
        }
    }
}

@Composable
fun QuestionComponent(questionNumber: Int?,
                      questions: Questions?,
                      isTimerStopped : Boolean,
                      selectedOptionId : Int?,
                      selectedAnswer : (id : Int?) -> Unit) {

    val firstOption = questions?.countries?.getOrNull(index = 0)
    val secondOption = questions?.countries?.getOrNull(index = 1)
    val thirdOption = questions?.countries?.getOrNull(index = 2)
    val fourthOption = questions?.countries?.getOrNull(index = 3)

    Column(modifier = Modifier
        .background(
            color = BackgroundColor,
            shape = RoundedCornerShape(size = dimensionResource(id = R.dimen.margin_normal))
        )
        .padding(vertical = dimensionResource(id = R.dimen.margin_large))
        .fillMaxWidth()
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            QuestionNumber(questionNumber = questionNumber)
            WidthSpacer(width = dimensionResource(id = R.dimen.margin_large))

            Text(text = stringResource(id = R.string.guess_the_flag),
                style = MaterialTheme.typography.titleMedium
            )
        }
        HeightSpacer(height = dimensionResource(id = R.dimen.margin_large))

        Row(modifier = Modifier
            .padding(horizontal = dimensionResource(id = R.dimen.margin_large))
            .fillMaxWidth()
            .animateContentSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AnimatedVisibility(visibleState = remember(questionNumber) {
                MutableTransitionState(initialState = false).apply { targetState = true } },
                enter = slideInHorizontally { -it }, exit = slideOutHorizontally { -it }
            ) {
                Box(modifier = Modifier
                    .background(
                        color = BackgroundColor, shape = RoundedCornerShape(
                            size = dimensionResource(
                                id = R.dimen.margin_normal
                            )
                        )
                    )
                    .padding(all = dimensionResource(id = R.dimen.margin_normal))
                ) {
                    Image(painter = painterResource(id = questions?.questionFlag ?: R.drawable.ic_launcher_background),
                        modifier = Modifier
                            .width(width = 70.dp)
                            .height(height = 50.dp),
                        contentDescription = null
                    )
                }
            }

            AnimatedVisibility(visibleState = remember(questionNumber) {
                MutableTransitionState(initialState = false).apply { targetState = true } },
                enter = slideInHorizontally { it }, exit = slideOutHorizontally { it }
            ) {
                Column(modifier = Modifier
                    .padding(horizontal = dimensionResource(id = R.dimen.margin_large))
                    .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.margin_large))
                ) {
                    Row(modifier = Modifier
                        .height(intrinsicSize = IntrinsicSize.Max)
                        .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.margin_normal))
                    ) {
                        OptionSelectionButton(modifier = Modifier
                            .fillMaxHeight()
                            .weight(weight = 1F),
                            countryName = firstOption?.countryName,
                            isSelected = selectedOptionId == firstOption?.id,
                            isRightAnswer = if (isTimerStopped) {
                                when (selectedOptionId) {
                                    null -> questions?.answerId == firstOption?.id
                                    firstOption?.id -> firstOption.id == questions.answerId
                                    else -> firstOption?.id == questions?.answerId
                                }
                            } else false,
                            isWrongAnswer = if (isTimerStopped) {
                                selectedOptionId == firstOption?.id && firstOption?.id != questions?.answerId
                            } else false,
                            onClick = {
                                if (isTimerStopped) return@OptionSelectionButton
                                if (selectedOptionId != null) return@OptionSelectionButton
                                selectedAnswer(firstOption?.id)
                            }
                        )

                        OptionSelectionButton(modifier = Modifier
                            .fillMaxHeight()
                            .weight(weight = 1F),
                            countryName = secondOption?.countryName,
                            isSelected = selectedOptionId == secondOption?.id,
                            isRightAnswer = if (isTimerStopped) {
                                when (selectedOptionId) {
                                    null -> questions?.answerId == secondOption?.id
                                    secondOption?.id -> secondOption.id == questions.answerId
                                    else -> secondOption?.id == questions?.answerId
                                }
                            } else false,
                            isWrongAnswer = if (isTimerStopped) {
                                selectedOptionId == secondOption?.id && secondOption?.id != questions?.answerId
                            } else false,
                            onClick = {
                                if (isTimerStopped) return@OptionSelectionButton
                                if (selectedOptionId != null) return@OptionSelectionButton
                                selectedAnswer(secondOption?.id)
                            }
                        )

                    }

                    Row(modifier = Modifier
                        .height(intrinsicSize = IntrinsicSize.Max)
                        .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.margin_normal))
                    ) {
                        OptionSelectionButton(modifier = Modifier
                            .fillMaxHeight()
                            .weight(weight = 1F),
                            countryName = thirdOption?.countryName,
                            isSelected = selectedOptionId == thirdOption?.id,
                            isRightAnswer = if (isTimerStopped) {
                                when (selectedOptionId) {
                                    null -> questions?.answerId == thirdOption?.id
                                    thirdOption?.id -> thirdOption.id == questions.answerId
                                    else -> thirdOption?.id == questions?.answerId
                                }
                            } else false,
                            isWrongAnswer = if (isTimerStopped) {
                                selectedOptionId == thirdOption?.id && thirdOption?.id != questions?.answerId
                            } else false,
                            onClick = {
                                if (isTimerStopped) return@OptionSelectionButton
                                if (selectedOptionId != null) return@OptionSelectionButton
                                selectedAnswer(thirdOption?.id)
                            }
                        )

                        OptionSelectionButton(modifier = Modifier
                            .fillMaxHeight()
                            .weight(weight = 1F),
                            countryName = fourthOption?.countryName,
                            isSelected = selectedOptionId == fourthOption?.id,
                            isRightAnswer = if (isTimerStopped) {
                                when (selectedOptionId) {
                                    null -> questions?.answerId == fourthOption?.id
                                    fourthOption?.id -> fourthOption.id == questions.answerId
                                    else -> fourthOption?.id == questions?.answerId
                                }
                            } else false,
                            isWrongAnswer = if (isTimerStopped) {
                                selectedOptionId == fourthOption?.id && fourthOption?.id != questions?.answerId
                            } else false,
                            onClick = {
                                if (isTimerStopped) return@OptionSelectionButton
                                if (selectedOptionId != null) return@OptionSelectionButton
                                selectedAnswer(fourthOption?.id)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LoadingNextQuestion(
    remainingTime : Int?
) {
    Box(modifier = Modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = AppBarColor
        )
        Text(text = "${remainingTime ?: 10}")
    }
}

@Composable
fun RemainingTime(remainingTime: String?) {
    Box(modifier = Modifier
        .clip(shape = RoundedCornerShape(size = 8.dp))
        .background(brush = Brush.linearGradient(colors = QuestionNumberBackgroundColors))
        .padding(
            horizontal = dimensionResource(id = R.dimen.margin_large),
            vertical = dimensionResource(id = R.dimen.margin_normal)
        ),
        contentAlignment = Alignment.Center
    ) {
        Text(text = remainingTime ?: "",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White
        )
    }
}