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
import com.example.exerciseapplication.exercise.page.WorkoutUiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.UUID


@OptIn(ExperimentalCoroutinesApi::class)
class ExerciseViewModel(
    private val exerciseRepository: ExerciseRepository,
    private val workoutRepository: WorkoutRepository
): ViewModel() {
    val exercises: StateFlow<List<Exercise>> =
        exerciseRepository.exercises
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5_000),
                emptyList()
            )

    val allWorkouts: Flow<List<Workout>> = workoutRepository.allWorkouts

    private var _selectedDate = MutableStateFlow(LocalDate.now())
    val selectedDate: StateFlow<LocalDate> = _selectedDate

    fun setDate(newDate: LocalDate?) {
        if (newDate != null) {
            _selectedDate.value = newDate
        }
    }

    init {
        viewModelScope.launch {
            _selectedDate
                .flatMapLatest { date ->
                    workoutRepository.getWorkoutsByDate(date)
                }
                .stateIn(
                    viewModelScope,
                    SharingStarted.WhileSubscribed(5_000),
                    emptyList()
                )
        }
    }

    fun addExercise(name: String, weight: Float, numOfSets: Int,  numOfReps: Int) = viewModelScope.launch {
        val exercise = Exercise(UUID.randomUUID(), name, weight, numOfSets, numOfReps, true)
        exerciseRepository.addExercise(exercise)
    }

    fun removeExercise(exerciseItem: Exercise) = viewModelScope.launch {
        exerciseRepository.deactivateExercise(exerciseItem.id)
    }

    fun addWorkout(exercise: Exercise, weight: Float, numOfSets: Int, numOfReps: Int) = viewModelScope.launch {
        val workout = Workout(
            UUID.randomUUID(),
            exercise.id,
            selectedDate.value,
            weightAmount = weight,
            sets = numOfSets,
            reps = numOfReps
        )
        workoutRepository.addWorkout(workout)
    }

    fun removeWorkout(exercise: Exercise) = viewModelScope.launch {
        workoutRepository.removeWorkout(exerciseId = exercise.id, date = selectedDate.value)
    }


    fun workoutStateForExercise(
        exercise: Exercise,
        workouts: List<Workout>
    ): WorkoutUiState {

        val workout = workouts
            .firstOrNull { it.exerciseId == exercise.id && it.performedDate == selectedDate.value }

        return if (workout == null) {
            WorkoutUiState(
                reps = exercise.exerciseRepDefault,
                sets = exercise.exerciseSetDefault,
                weight = exercise.defaultWeightAmount,
                existsInDb = false
            )
        } else {
            WorkoutUiState(
                reps = workout.reps,
                sets = workout.sets,
                weight = workout.weightAmount,
                existsInDb = true
            )
        }
    }

    fun updateWorkout(
        exercise: Exercise,
        weight: Float,
        sets: Int,
        reps: Int
    ) {
        val date = _selectedDate.value

        viewModelScope.launch {
            workoutRepository.addWorkout(
                Workout(
                    id = UUID.randomUUID(),
                    exerciseId = exercise.id,
                    performedDate = date,
                    reps = reps,
                    sets = sets,
                    weightAmount = weight
                )
            )
        }
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