package com.example.exerciseapplication.data.repositories

import androidx.annotation.WorkerThread
import com.example.exerciseapplication.data.dao.ExerciseDao
import com.example.exerciseapplication.data.entity.Exercise
import kotlinx.coroutines.flow.Flow
import java.util.UUID

class ExerciseRepository(private val exerciseDao: ExerciseDao) {
    val exercises: Flow<List<Exercise>> = exerciseDao.getAllActiveExercises()
    val inactiveExercises: Flow<List<Exercise>> = exerciseDao.getAllInactiveExercises()

    @WorkerThread
    suspend fun addExercise(exercise: Exercise) {
        exerciseDao.insertExercise(exercise)
    }

    @WorkerThread
    suspend fun removeAllExercises() {
        exerciseDao.deleteAllExercise()
    }

    @WorkerThread
    suspend fun deactivateExercise(id: UUID) {
        exerciseDao.setExerciseInactive(id)
    }

    @WorkerThread
    suspend fun reactivateExercise(id: UUID) {
        exerciseDao.setExerciseActive(id)
    }

}