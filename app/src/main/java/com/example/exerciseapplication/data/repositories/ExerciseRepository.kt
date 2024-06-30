package com.example.exerciseapplication.data.repositories

import androidx.annotation.WorkerThread
import com.example.exerciseapplication.data.dao.ExerciseDao
import com.example.exerciseapplication.data.entity.Exercise
import kotlinx.coroutines.flow.Flow

class ExerciseRepository(private val exerciseDao: ExerciseDao) {
    val exercises: Flow<List<Exercise>> = exerciseDao.getAllExercises()

    @WorkerThread
    suspend fun addExercise(exercise: Exercise) {
        exerciseDao.insertExercise(exercise)
    }

    @WorkerThread
    suspend fun removeAllExercises() {
        exerciseDao.deleteAllExercise()
    }

}