package com.example.exerciseapplication.data.repositories

import androidx.annotation.WorkerThread
import androidx.room.Transaction
import com.example.exerciseapplication.data.ExportData
import com.example.exerciseapplication.data.dao.ExerciseDao
import com.example.exerciseapplication.data.dao.WorkoutDao
import com.example.exerciseapplication.data.entity.Exercise
import kotlinx.coroutines.flow.Flow
import java.util.UUID

class ExerciseRepository(private val exerciseDao: ExerciseDao, private val workoutDao: WorkoutDao) {
    val exercises: Flow<List<Exercise>> = exerciseDao.getAllActiveExercises()
    val inactiveExercises: Flow<List<Exercise>> = exerciseDao.getAllInactiveExercises()

    @WorkerThread
    suspend fun addExercise(exercise: Exercise) {
        exerciseDao.insertExercise(exercise)
    }

    @WorkerThread
    suspend fun removeAllExercises() {
        exerciseDao.deleteAllExercises()
    }

    @WorkerThread
    suspend fun deactivateExercise(id: UUID) {
        exerciseDao.setExerciseInactive(id)
    }

    @WorkerThread
    suspend fun reactivateExercise(id: UUID) {
        exerciseDao.setExerciseActive(id)
    }

    @WorkerThread
    suspend fun updateExerciseInfo(id: UUID, exercise: Exercise) {
        exerciseDao.setExerciseInfo(
            exerciseId = id,
            exerciseName = exercise.exerciseName,
            setsDefault = exercise.exerciseSetDefault,
            repsDefault = exercise.exerciseRepDefault,
            weightDefault = exercise.defaultWeightAmount
        )
    }

    suspend fun exportData(): ExportData {
        return ExportData(
            exercises = exerciseDao.getAllExercisesOnce(),
            workouts = workoutDao.getAllWorkoutsOnce()
        )
    }

    @Transaction
    suspend fun importData(data: ExportData) {
        exerciseDao.deleteAllExercises()
        workoutDao.deleteAllWorkouts()
        exerciseDao.insertAll(data.exercises)
        workoutDao.insertAll(data.workouts)
    }



}