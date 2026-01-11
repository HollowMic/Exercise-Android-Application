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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.exerciseapplication.exercise.ExerciseViewModel

@Composable
fun ExerciseList(modifier: Modifier = Modifier, exerciseViewModel: ExerciseViewModel) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        val exerciseList = exerciseViewModel.exercises.collectAsState(initial = emptyList())
        Column {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                items(exerciseList.value) { it ->
                    Box(
                        modifier = Modifier.padding(0.dp, 1.dp)
                    ) {
                        ExerciseRow(exerciseItem = it, exerciseViewModel = exerciseViewModel)
                    }
                }

            }
        }

    }
}