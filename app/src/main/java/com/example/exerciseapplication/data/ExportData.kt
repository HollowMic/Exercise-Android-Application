package com.example.exerciseapplication.data

import com.example.exerciseapplication.data.entity.Exercise
import com.example.exerciseapplication.data.entity.Workout
import kotlinx.serialization.Serializable

@Serializable
data class ExportData(
    val exercises: List<Exercise>,
    val workouts: List<Workout>,
    val exportedAt: Long = System.currentTimeMillis(),
    val schemaVersion: Int = 2
)
