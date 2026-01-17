package com.example.exerciseapplication.data.repositories

import androidx.annotation.WorkerThread
import com.example.exerciseapplication.data.InsertResult
import com.example.exerciseapplication.data.dao.WorkoutDao
import com.example.exerciseapplication.data.entity.Exercise
import com.example.exerciseapplication.data.entity.Workout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.util.UUID

class WorkoutRepository(private val workoutDao: WorkoutDao) {
    val allWorkouts: Flow<List<Workout>> = workoutDao.getAllWorkouts()

    @WorkerThread
    suspend fun addWorkout(workout: Workout): InsertResult {
        return withContext(Dispatchers.IO) {

            val result = workoutDao.insertWorkout(workout)

            if (result == -1L) {
                val oldWorkout = workoutDao.getWorkoutForExerciseDate(workout.exerciseId, workout.performedDate)
                if (oldWorkout != null) {
                    workoutDao.updateWorkout(
                        oldWorkout.id,
                        workout.reps,
                        workout.sets,
                        workout.weightAmount
                    )
                }
                InsertResult.DuplicateDate
            } else {
                InsertResult.Success
            }
        }
    }

    @WorkerThread
    suspend fun removeWorkout(exerciseId: UUID, date: LocalDate) {
        withContext(Dispatchers.IO) {
            workoutDao.removeWorkout(exerciseId, date)
        }
    }

    @WorkerThread
    fun getWorkoutsByExercise(exercise: Exercise): Flow<List<Workout>> {
        return workoutDao.getWorkoutsForExercise(exercise.id)
    }

    @WorkerThread
    fun getWorkoutsByDate(date: LocalDate): Flow<List<Workout>> =
        workoutDao.getWorkoutsByDate(date)


}