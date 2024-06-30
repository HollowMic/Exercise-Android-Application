package com.example.exerciseapplication

import android.app.Application
import com.example.exerciseapplication.data.ExerciseDatabase
import com.example.exerciseapplication.data.repositories.ExerciseRepository

class ExerciseApplication: Application() {
    private val exerciseDatabase by lazy { ExerciseDatabase.getDatabase(this) }
    val exerciseRepository by lazy { ExerciseRepository(exerciseDatabase.exerciseDao()) }
}