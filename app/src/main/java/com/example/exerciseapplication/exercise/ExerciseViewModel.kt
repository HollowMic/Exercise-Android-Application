package com.example.exerciseapplication.exercise

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.exerciseapplication.ExerciseApplication
import com.example.exerciseapplication.data.entity.Exercise
import com.example.exerciseapplication.data.repositories.ExerciseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.UUID


class ExerciseViewModel(
    private val exerciseRepository: ExerciseRepository
): ViewModel() {
    val exercises: Flow<List<Exercise>> = exerciseRepository.exercises


    init {
        viewModelScope.launch {
        }
    }

    fun addExercise(name: String, weight: Float, numOfReps: Int) = viewModelScope.launch {
        val exercise = Exercise(UUID.randomUUID(), name, weight, numOfReps, 10)
        exerciseRepository.addExercise(exercise)
    }

    fun deleteAllExercises() = viewModelScope.launch {
        exerciseRepository.removeAllExercises()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object: ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T: ViewModel> create(
                modelClass:Class<T>,
                extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[APPLICATION_KEY])
                return ExerciseViewModel(
                    (application as ExerciseApplication).exerciseRepository
                ) as T
            }
        }
    }
}