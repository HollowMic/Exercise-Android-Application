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

    @Query("SElECT * FROM exercise WHERE id=:exerciseId")
    suspend fun getExerciseById(exerciseId: UUID): Exercise

    @Insert
    suspend fun insertExercise(exercise: Exercise)

    @Query("DELETE FROM exercise WHERE id=:exerciseId")
    suspend fun deleteExerciseById(exerciseId: UUID)
}