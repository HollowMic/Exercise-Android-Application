package com.example.exerciseapplication.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.exerciseapplication.data.entity.Exercise
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM exercise")
    fun getAllExercises(): Flow<List<Exercise>>

    @Query("SELECT * FROM exercise WHERE in_current_rotation = 1")
    fun getAllActiveExercises(): Flow<List<Exercise>>

    @Query("SELECT * FROM exercise WHERE in_current_rotation = 0")
    fun getAllInactiveExercises(): Flow<List<Exercise>>

    @Query("SElECT * FROM exercise WHERE id=:exerciseId")
    suspend fun getExerciseById(exerciseId: UUID): Exercise

    @Insert
    suspend fun insertExercise(exercise: Exercise)

    @Query("DELETE FROM exercise WHERE id=:exerciseId")
    suspend fun deleteExerciseById(exerciseId: UUID)

    @Query("DELETE FROM exercise ")
    suspend fun deleteAllExercise()

    @Query("UPDATE exercise SET in_current_rotation = 0 WHERE id = :exerciseId")
    suspend fun setExerciseInactive(exerciseId: UUID)

    @Query("UPDATE exercise SET in_current_rotation = 1 WHERE id = :exerciseId")
    suspend fun setExerciseActive(exerciseId: UUID)
}