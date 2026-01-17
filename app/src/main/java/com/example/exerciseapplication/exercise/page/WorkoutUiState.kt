package com.example.exerciseapplication.exercise.page

data class WorkoutUiState(
    val reps: Int,
    val sets: Int,
    val weight: Float,
    val existsInDb: Boolean
)
