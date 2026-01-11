package com.example.exerciseapplication.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.exerciseapplication.data.entity.Workout
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.util.UUID

@Dao
interface WorkoutDao {
    @Insert
    suspend fun insertWorkout(workout: Workout)

    @Query("""
        SELECT * FROM workout
        WHERE performedDate = :date
    """)
    fun getWorkoutsByDate(date: LocalDate): Flow<List<Workout>>

    @Query("""
        SELECT * FROM workout
        WHERE exerciseId = :exerciseId
    """)
    fun getWorkoutsForExercise(exerciseId: UUID): Flow<List<Workout>>
}