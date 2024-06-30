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
//            val fileDirectory = context.filesDir.absolutePath + "/file.json"
//            println(fileDirectory)
//            println("isdjoiadjiosajdioa")
////            withContext(Dispatchers.IO) {
////                File(fileDirectory).createNewFile()
////            }
//            val valFile = context.openFileInput(fileDirectory)
////            var valFile: FileInputStream
////            try {
////                valFile = context.openFileInput("file.json")
////            } catch (e: FileNotFoundException) {
////                withContext(Dispatchers.IO) {
////
////                }
////                valFile = context.openFileInput("file.json")
////            }
//            val valReader = JsonReader(InputStreamReader(valFile))
            // Put data from file into viewModel using some read function.
        }
    }

    fun addExercise(name: String) = viewModelScope.launch {
        val exercise = Exercise(UUID.randomUUID(), name, 3, 10)
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