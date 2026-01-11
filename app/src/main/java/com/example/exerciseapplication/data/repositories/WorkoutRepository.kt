package com.example.exerciseapplication.data.repositories

import androidx.annotation.WorkerThread
import com.example.exerciseapplication.data.dao.WorkoutDao
import com.example.exerciseapplication.data.entity.Exercise
import com.example.exerciseapplication.data.entity.Workout
import java.time.LocalDate

class WorkoutRepository(private val workoutDao: WorkoutDao) {

    @WorkerThread
    suspend fun addWorkout(workout: Workout) {
        workoutDao.insertWorkout(workout)
    }

    @WorkerThread
    suspend fun getWorkoutsByExercise(exercise: Exercise) {
        workoutDao.getWorkoutsForExercise(exercise.id)
    }

    @WorkerThread
    suspend fun getWorkoutsByDate(date: LocalDate) {
        workoutDao.getWorkoutsByDate(date)
    }

}