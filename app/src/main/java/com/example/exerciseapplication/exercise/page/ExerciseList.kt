package com.example.exerciseapplication.exercise.page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.exerciseapplication.exercise.ExerciseViewModel
import java.time.LocalDate

@Composable
fun ExerciseList(modifier: Modifier = Modifier, exerciseViewModel: ExerciseViewModel) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        val exerciseList by exerciseViewModel.exercises.collectAsState(initial = emptyList())
        val selectedDate by exerciseViewModel.selectedDate.collectAsState(initial = LocalDate.now())
        val workoutList by exerciseViewModel.allWorkouts.collectAsState(initial = emptyList()) //TODO Might be slow to get whole list.
        Column {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                items(exerciseList, key = { exercise -> "${exercise.id}-${selectedDate}" }) { exercise ->
                    Box(
                        modifier = Modifier.padding(0.dp, 1.dp)
                    ) {
                        val workoutState = exerciseViewModel.workoutStateForExercise(exercise, workoutList)
                        ExerciseRow(
                            exerciseItem = exercise,
                            exerciseViewModel = exerciseViewModel,
                            workoutState = workoutState,
                            onChange = { weight, sets, reps ->
                                exerciseViewModel.updateWorkout(
                                    exercise,
                                    weight,
                                    sets,
                                    reps
                                )
                            }
                        )
                    }
                }

            }
        }

    }
}