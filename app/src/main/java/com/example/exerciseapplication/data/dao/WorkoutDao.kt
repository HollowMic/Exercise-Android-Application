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
            weightAmount = :weight,
            notes = :notes
        WHERE id = :workoutId
    """)
    fun updateWorkout(
        workoutId: UUID,
        reps: Int,
        sets: Int,
        weight: Float,
        notes: String,
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

    @Query("""
        SELECT * FROM workout
        WHERE exerciseId = :exerciseId
          AND performedDate = :date
        LIMIT 1
    """)
    fun getWorkoutForExerciseDate(
        exerciseId: UUID,
        date: LocalDate
    ): Workout?

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

    @Query("DELETE FROM workout")
    suspend fun deleteAllWorkouts()

    @Query("SELECT * FROM workout")
    suspend fun getAllWorkoutsOnce(): List<Workout>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(workouts: List<Workout>)

}