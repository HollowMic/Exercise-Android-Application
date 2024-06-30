package com.example.exerciseapplication.exercise.page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.exerciseapplication.exercise.ExerciseViewModel

@Composable
fun ExerciseList(modifier: Modifier = Modifier, exerciseViewModel: ExerciseViewModel) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        val listOfExercises = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13)
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            items(listOfExercises) { it ->
                Box(
                    modifier = Modifier.padding(0.dp, 1.dp)
                ) {
                    ExerciseRow(exerciseItem = ExerciseItem())
                }

            }
        }
    }
}