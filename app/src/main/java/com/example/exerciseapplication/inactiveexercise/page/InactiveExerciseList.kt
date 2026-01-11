package com.example.exerciseapplication.inactiveexercise.page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.example.exerciseapplication.inactiveexercise.InactiveViewModel

@Composable
fun InactiveExerciseList(modifier: Modifier = Modifier, inactiveViewModel: InactiveViewModel) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        val exerciseList = inactiveViewModel.inactiveExercises.collectAsState(initial = emptyList())
        Column {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                items(exerciseList.value) { it ->
                    Box(
                        modifier = Modifier.padding(0.dp, 1.dp)
                    ) {
                        InactiveExerciseRow(exerciseItem = it, inactiveViewModel = inactiveViewModel)
                    }
                }

            }
        }

    }
}