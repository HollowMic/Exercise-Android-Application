package com.example.exerciseapplication.exercise.page

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.exerciseapplication.R
import com.example.exerciseapplication.data.entity.Exercise
import com.example.exerciseapplication.exercise.ExerciseViewModel
import com.example.exerciseapplication.ui.theme.PurpleTertiaryDark
import java.util.UUID

@Composable
fun ExerciseRow(
    modifier: Modifier = Modifier,
    exerciseItem: Exercise,
    exerciseViewModel: ExerciseViewModel,
    workoutState: WorkoutUiState,
    onChange: (Float, Int, Int, String) -> Unit
) {

    val weightAmount by remember(
        workoutState.weight,
        workoutState.existsInDb
    ) {
        mutableFloatStateOf(workoutState.weight)
    }

    val numOfSets by remember(
        workoutState.sets,
        workoutState.existsInDb
    ) {
        mutableIntStateOf(workoutState.sets)
    }

    val numOfReps by remember(
        workoutState.reps,
        workoutState.existsInDb
    ) {
        mutableIntStateOf(workoutState.reps)
    }

    var notes by remember(
        workoutState.notes,
        workoutState.existsInDb
    ) {
        mutableStateOf(workoutState.notes)
    }

    fun increaseWeight() {
        onChange(weightAmount + 0.5f, numOfSets, numOfReps, notes)
    }
    fun decreaseWeight() {
        onChange(weightAmount - 0.5f, numOfSets, numOfReps, notes)
    }
    fun increaseSets() {
        onChange(weightAmount, numOfSets + 1, numOfReps, notes)
    }
    fun decreaseSets() {
        onChange(weightAmount, numOfSets - 1, numOfReps, notes)
    }
    fun increaseReps() {
        onChange(weightAmount, numOfSets, numOfReps + 1, notes)
    }
    fun decreaseReps() {
        onChange(weightAmount, numOfSets, numOfReps - 1, notes)
    }
    fun changeNotes(string: String) {
        notes = string
        onChange(weightAmount, numOfSets, numOfReps, notes)
    }

    val selected = workoutState.existsInDb
    var expanded by remember { mutableStateOf(false) }

    fun toggleExpanded() {
        expanded = !expanded
    }

    fun toggleSelect() {
        if (selected) {
            expanded = false
            exerciseViewModel.removeWorkout(exerciseItem)
        } else {
            onChange(weightAmount, numOfSets, numOfReps, notes)
        }
    }

    Surface(
        color = PurpleTertiaryDark
    ) {
        Column(
            modifier = Modifier
                .animateContentSize()
                .height(if (expanded) 240.dp else 60.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top
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
                        onClick = { toggleSelect() }
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
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(7.dp, 5.dp),
                    verticalArrangement = Arrangement.Top,
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(3.dp)
                            .border(
                                width = 2.dp,
                                color = MaterialTheme.colorScheme.outline,
                                shape = RoundedCornerShape(18.dp)
                            ),
                        verticalArrangement = Arrangement.Top,
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp, 10.dp, 20.dp, 10.dp)
                                .height(60.dp),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                var optionsMenu by remember{ mutableStateOf(false) }
                                Box(modifier = Modifier.padding(0.dp, 0.dp)) {
                                    IconButton(onClick = { optionsMenu = true }) {
                                        Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = "Options")
                                    }
                                }
                                DropdownMenu(expanded = optionsMenu, onDismissRequest = { optionsMenu = false }) {
                                    Box(modifier = Modifier.padding(10.dp, 0.dp)) {
                                        Button(onClick = { exerciseViewModel.saveNewExerciseDefaults() }) {
                                            Text(text = stringResource(id = R.string.save_as_new_default))
                                        }
                                    }
                                    Box(modifier = Modifier.padding(10.dp, 0.dp)) {
                                        var buttonColor by remember { mutableStateOf(Color(0xFF440000)) }
                                        var removeCounter by remember { mutableIntStateOf(0) }
                                        Button(
                                            onClick = {
                                                removeCounter += 1
                                                when (removeCounter) {
                                                    1 -> buttonColor = Color(0xFF880000)
                                                    2 -> buttonColor = Color(0xFFFF0000)
                                                    3 -> {
                                                        exerciseViewModel.removeExercise(exerciseItem)
                                                        removeCounter = 0
                                                    }
                                                }
                                            },
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = buttonColor
                                            )
                                        ) {
                                            Text(text = "Remove")
                                        }
                                    }
                                }
                            }


                            Box {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
//                                    .fillMaxSize()
                                        .padding(10.dp),
                                    horizontalArrangement = Arrangement.End,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Counter(
                                        value = weightAmount,
                                        onIncrement = { increaseWeight() },
                                        onDecrement = { decreaseWeight() })
                                    Counter(
                                        value = numOfSets.toFloat(),
                                        onIncrement = { increaseSets() },
                                        onDecrement = { decreaseSets() })
                                    Counter(
                                        value = numOfReps.toFloat(),
                                        onIncrement = { increaseReps() },
                                        onDecrement = { decreaseReps() })
                                }
                            }
                        }
                        Box(modifier = Modifier.padding(20.dp, 0.dp, 20.dp, 20.dp)) {
                            TextField(
                                value = notes,
                                onValueChange = {it -> changeNotes(it) },
                                label = { Text("Notes") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .heightIn(min = 96.dp), // ~2 lines tall
                                maxLines = 2,
                                minLines = 2,
                                singleLine = false
                            )

                        }
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
    val exercise = Exercise(
        id = UUID.randomUUID(),
        exerciseName = "Test Exercise",
        defaultWeightAmount = 37.5f,
        exerciseRepDefault = 10,
        exerciseSetDefault = 3,
        inCurrentRotation = true
    )
    val workoutState = WorkoutUiState(10, 3, 45.5f, false, "Dont do it")
    ExerciseRow(exerciseItem = exercise, exerciseViewModel = exerciseViewModel, workoutState = workoutState, onChange = { _, _, _, _ -> })
}