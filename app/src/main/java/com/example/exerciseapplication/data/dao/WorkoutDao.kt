package com.example.exerciseapplication.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.exerciseapplication.data.entity.Workout
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.util.UUID

@Dao
interface WorkoutDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWorkout(workout: Workout): Long

    @Query("""
        UPDATE workout
        SET
            reps = :reps,
            sets = :sets,
            weightAmount = :weight
        WHERE id = :workoutId
    """)
    fun updateWorkout(
        workoutId: UUID,
        reps: Int,
        sets: Int,
        weight: Float
    )

    @Query("""
        DELETE FROM workout
        WHERE exerciseId = :exerciseId 
            AND performedDate = :date
    """)
    fun removeWorkout(
        exerciseId: UUID,
        date: LocalDate
    )

    @Query("SELECT * FROM workout")
    fun getAllWorkouts(): Flow<List<Workout>>

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