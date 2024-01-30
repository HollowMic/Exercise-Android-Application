package com.example.exerciseapplication.exercise.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
fun ExercisePageContents(modifier: Modifier, exerciseViewModel: ExerciseViewModel?) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            DateRow(modifier)
            ExerciseList(modifier)
        }
    }
}

@Composable
@Preview
fun ExercisePageContentsPreview() {

    ExercisePageContents(modifier = Modifier.height(1000.dp).width(400.dp), exerciseViewModel = null)
}
