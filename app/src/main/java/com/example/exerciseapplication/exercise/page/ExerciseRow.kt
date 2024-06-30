package com.example.exerciseapplication.exercise.page

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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.exerciseapplication.R
import com.example.exerciseapplication.exercise.ExerciseItem
import com.example.exerciseapplication.exercise.ExerciseViewModel
import com.example.exerciseapplication.ui.theme.PurpleTertiaryDark

@Composable
fun ExerciseRow(
    modifier: Modifier = Modifier,
    exerciseItem: ExerciseItem,
    exerciseViewModel: ExerciseViewModel
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
                .height(if (expanded) 120.dp else 60.dp)
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
                        text = stringResource(R.string.item_default_text)
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
                    horizontalArrangement = Arrangement.End
                ) {
                    val exerciseList = exerciseViewModel.exercises.collectAsState(initial = emptyList())
                    Text(text = exerciseList.value.size.toString())
//                    Button(onClick = { exerciseViewModel.addExercise("new Exercise") }) {
//                        Text(text = "add")
//                    }
                    Button(onClick = { exerciseViewModel.deleteAllExercises() }) {
                        Text(text = "remove")
                    }
                    Counter(startValue = 32.5f)
                    Counter(startValue = 3.0f)
                    Button(onClick = { expanded = !expanded }) {
                        Text(text = stringResource(id = R.string.save))
                    }
                }
            }
        }
    }

}

@Composable
@Preview
fun ExerciseRowPreview() {
    val exerciseViewModel: ExerciseViewModel = viewModel(
        factory = ExerciseViewModel.Factory
    )
    ExerciseRow(exerciseItem = ExerciseItem(), exerciseViewModel = exerciseViewModel)
}