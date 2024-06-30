package com.example.exerciseapplication.data.repositories

import com.example.exerciseapplication.data.dao.ExerciseDao
import com.example.exerciseapplication.data.entity.Exercise
import kotlinx.coroutines.flow.Flow

class ExerciseRepository(private val exerciseDao: ExerciseDao) {
    val exercises: Flow<List<Exercise>> = exerciseDao.getAllExercises()

}