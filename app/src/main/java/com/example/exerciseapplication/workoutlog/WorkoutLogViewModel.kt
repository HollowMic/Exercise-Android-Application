package com.example.exerciseapplication.workoutlog

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.exerciseapplication.ExerciseApplication
import com.example.exerciseapplication.data.entity.Exercise
import com.example.exerciseapplication.data.entity.Workout
import com.example.exerciseapplication.data.repositories.ExerciseRepository
import com.example.exerciseapplication.data.repositories.WorkoutRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.UUID

class WorkoutLogViewModel(
    private val workoutRepository: WorkoutRepository,
    private val exerciseRepository: ExerciseRepository
): ViewModel()  {
    val workouts: Flow<List<Workout>> = workoutRepository.allWorkouts
    val exercises: Flow<List<Exercise>> = exerciseRepository.exercises

    init {
        viewModelScope.launch {
        }
    }

    var showDialog by mutableStateOf(false)
    fun hideDialog() {
        showDialog = false
    }
    private var exerciseToAddTo: Exercise = Exercise(UUID.randomUUID(), "", 0.toFloat(), 0, 0, false)
    fun showAddWorkout(exercise: Exercise) {
        showDialog = true
        exerciseToAddTo = exercise
    }

    fun addWorkout(date: LocalDate, weight: Float, numOfSets: Int,  numOfReps: Int, notes: String) = viewModelScope.launch {
        val workout = Workout(
            UUID.randomUUID(),
            exerciseToAddTo.id,
            date,
            weightAmount = weight,
            sets = numOfSets,
            reps = numOfReps,
            notes = notes
            )
        workoutRepository.addWorkout(workout)
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object: ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T: ViewModel> create(
                modelClass:Class<T>,
                extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[APPLICATION_KEY])
                return WorkoutLogViewModel(
                    (application as ExerciseApplication).workoutRepository,
                    (application).exerciseRepository
                ) as T
            }
        }
    }
}