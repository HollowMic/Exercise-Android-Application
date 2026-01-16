package com.example.exerciseapplication.exercise

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


class ExerciseViewModel(
    private val exerciseRepository: ExerciseRepository,
    private val workoutRepository: WorkoutRepository
): ViewModel() {
    val exercises: Flow<List<Exercise>> = exerciseRepository.exercises

    init {
        viewModelScope.launch {
        }
    }

    private var date: LocalDate = LocalDate.now()

    fun setDate(newDate: LocalDate?) {
        if (newDate != null) {
            date = newDate
        }
        println("set date run: $date")
        //TODO reset all exercise rows to update on date change.
    }

    fun addExercise(name: String, weight: Float, numOfSets: Int,  numOfReps: Int) = viewModelScope.launch {
        val exercise = Exercise(UUID.randomUUID(), name, weight, numOfSets, numOfReps, true)
        exerciseRepository.addExercise(exercise)
    }

    fun deleteAllExercises() = viewModelScope.launch {
        exerciseRepository.removeAllExercises()
    }

    fun removeExercise(exerciseItem: Exercise) = viewModelScope.launch {
        exerciseRepository.deactivateExercise(exerciseItem.id)
    }

    fun addWorkout(exercise: Exercise, weight: Float, numOfSets: Int, numOfReps: Int) = viewModelScope.launch {
        val workout = Workout(
            UUID.randomUUID(),
            exercise.id,
            date,
            weightAmount = weight,
            sets = numOfSets,
            reps = numOfReps
        )
        workoutRepository.addWorkout(workout)
    }

    fun removeWorkout(exercise: Exercise) = viewModelScope.launch {
        workoutRepository.removeWorkout(exerciseId = exercise.id, date = date)
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
                    (application as ExerciseApplication).exerciseRepository,
                    (application).workoutRepository
                ) as T
            }
        }
    }
}