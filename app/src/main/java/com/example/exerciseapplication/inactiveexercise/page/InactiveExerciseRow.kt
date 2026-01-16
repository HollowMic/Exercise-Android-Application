package com.example.exerciseapplication.inactiveexercise.page

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.exerciseapplication.R
import com.example.exerciseapplication.data.entity.Exercise
import com.example.exerciseapplication.exercise.page.Counter
import com.example.exerciseapplication.inactiveexercise.InactiveViewModel
import com.example.exerciseapplication.ui.theme.PurpleTertiaryDark

@Composable
fun InactiveExerciseRow(
    modifier: Modifier = Modifier,
    exerciseItem: Exercise,
    inactiveViewModel: InactiveViewModel
) {
    var expanded by remember { mutableStateOf(false) }
    var selected by remember { mutableStateOf(false) }
    fun toggleExpanded() {
        expanded = !expanded
        if (expanded) {
            selected = true
        }
    }
    Surface(
        color = PurpleTertiaryDark
    ) {
        Column(
            modifier = Modifier
                .animateContentSize()
                .height(if (expanded) 130.dp else 60.dp)
                .fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(10.dp, 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        modifier = Modifier.padding(10.dp, 0.dp),
                        onClick = { selected = !selected }
                    ) {
                        if (selected) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_check_box_24),
                                contentDescription = ""
                            )
                        } else {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_check_box_outline_blank_24),
                                contentDescription = ""
                            )
                        }
                    }

                    Text(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(10.dp, 10.dp),
                        text = exerciseItem.exerciseName
                    )
                }

                IconButton(
                    modifier = Modifier.padding(10.dp, 0.dp),
                    onClick = { toggleExpanded() }
                ) {
                    if (expanded) {
                        Icon(imageVector = Icons.Rounded.KeyboardArrowDown, contentDescription = "")
                    } else {
                        Icon(imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowLeft, contentDescription = "")
                    }
                }
            }
            if (expanded) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
//                    val exerciseList = exerciseViewModel.exercises.collectAsState(initial = emptyList())
//                    Text(text = exerciseList.value.size.toString())
//                    Button(onClick = { exerciseViewModel.addExercise("new Exercise") }) {
//                        Text(text = "add")
//                    }
                    var buttonColor by remember { mutableStateOf(Color(0xFF440000)) }
                    var removeCounter by remember { mutableIntStateOf(0) }
                    Button(
                        onClick = {
                            removeCounter += 1
                            when (removeCounter) {
                                1 -> buttonColor = Color(0xFF880000)
                                2 -> buttonColor = Color(0xFFFF0000)
                                3 -> {
                                    inactiveViewModel.reactivateExercise(exerciseItem)
                                    removeCounter = 0
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = buttonColor
                        )
                    ) {
                        Text(text = "Readd")
                    }
                    Counter(value = exerciseItem.defaultWeightAmount, onIncrement = {}, onDecrement = {})
                    Counter(value = exerciseItem.exerciseSetDefault.toFloat(), onIncrement = {}, onDecrement = {})
                    Counter(value = exerciseItem.exerciseRepDefault.toFloat(), onIncrement = {}, onDecrement = {})
//                    Button(onClick = { expanded = !expanded }) {
//                        Text(text = stringResource(id = R.string.save))
//                    }
                }
            }
        }
    }

}