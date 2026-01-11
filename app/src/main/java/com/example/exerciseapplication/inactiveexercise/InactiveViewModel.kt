package com.example.exerciseapplication.inactiveexercise

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

class InactiveViewModel(
    private val exerciseRepository: ExerciseRepository
): ViewModel() {
    val inactiveExercises: Flow<List<Exercise>> = exerciseRepository.inactiveExercises

    init {
        viewModelScope.launch {
        }
    }

    fun reactivateExercise(exerciseItem: Exercise) = viewModelScope.launch {
        exerciseRepository.reactivateExercise(exerciseItem.id)
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object: ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T: ViewModel> create(
                modelClass:Class<T>,
                extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[APPLICATION_KEY])
                return InactiveViewModel(
                    (application as ExerciseApplication).exerciseRepository
                ) as T
            }
        }
    }
}