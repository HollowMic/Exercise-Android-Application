package com.example.exerciseapplication.workoutlog.page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.exerciseapplication.workoutlog.WorkoutLogViewModel

@Composable
fun WorkoutLogList(modifier: Modifier = Modifier, workoutLogViewModel: WorkoutLogViewModel) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        val exerciseList = workoutLogViewModel.exercises.collectAsState(initial = emptyList())
        Column {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                items(exerciseList.value) { it ->
                    Box(
                        modifier = Modifier.padding(0.dp, 1.dp)
                    ) {
                        WorkoutExerciseRow(exerciseItem = it, workoutLogViewModel = workoutLogViewModel)
                    }
                }

            }
        }

    }
}