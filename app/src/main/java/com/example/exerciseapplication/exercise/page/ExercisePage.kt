package com.example.exerciseapplication.exercise.page

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.exerciseapplication.Page
import com.example.exerciseapplication.R
import com.example.exerciseapplication.exercise.ExerciseViewModel

@Composable
fun ExercisePage(
    navigate: () -> Unit,
    openDrawer: () -> Unit
) {
    Page(
        pageName = R.string.exercise_page_title,
        navigate = navigate,
        openDrawer = openDrawer,
    ) {
        val exerciseViewModel: ExerciseViewModel = viewModel(
            factory = ExerciseViewModel.Factory
        )
        ExercisePageContents(modifier = it, exerciseViewModel = exerciseViewModel)
    }
}

@Composable
fun ExercisePageContents(modifier: Modifier, exerciseViewModel: ExerciseViewModel) {
    Row(modifier = modifier.fillMaxSize()) {
        Button(onClick = {}) {
            Text(text = "Hi", modifier = Modifier.fillMaxSize(), color = Color.Black)
        }

    }
}