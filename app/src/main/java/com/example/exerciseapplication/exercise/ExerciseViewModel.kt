package com.example.exerciseapplication.exercise

import android.content.Context
import android.util.JsonReader
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.exerciseapplication.ExerciseApplication
import kotlinx.coroutines.launch
import java.io.InputStreamReader


class ExerciseViewModel(context: Context): ViewModel() {

    init {
        viewModelScope.launch {
            val valFile = context.openFileInput("file.json")
            val valReader = JsonReader(InputStreamReader(valFile))
            // Put data from file into viewModel using some read function.
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object: ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T: ViewModel> create(
                modelClass:Class<T>, extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[APPLICATION_KEY])
                return ExerciseViewModel(
                    (application as ExerciseApplication),
                ) as T
            }
        }
    }
}