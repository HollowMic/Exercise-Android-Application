package com.example.exerciseapplication.exercise.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    var showDialog by remember { mutableStateOf(false) }
    fun showingDialog() {
        showDialog = true
    }
    fun hideDialog() {
        showDialog = false
    }

    Page(
        pageName = R.string.exercise_page_title,
        navigate = navigate,
        openDrawer = openDrawer,
        actions = listOf {
            IconButton(onClick = { showingDialog() }) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "")
            }
        }
    ) {
        val exerciseViewModel: ExerciseViewModel = viewModel(
            factory = ExerciseViewModel.Factory
        )

        if (showDialog) {
            AddExerciseOptionDialog(modifier = it, exerciseViewModel = exerciseViewModel, closeFunction = { hideDialog() })
        }
        ExercisePageContents(modifier = it, exerciseViewModel = exerciseViewModel)
    }
}

@Composable
fun ExercisePageContents(modifier: Modifier, exerciseViewModel: ExerciseViewModel) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            val exerciseList = exerciseViewModel.exercises.collectAsState(initial = emptyList())
            Text(text = exerciseList.value.size.toString())
            DateRow(modifier, exerciseViewModel)
            ExerciseList(modifier, exerciseViewModel)
        }
    }
}

@Composable
@Preview
fun ExercisePageContentsPreview() {
    val exerciseViewModel: ExerciseViewModel = viewModel(
        factory = ExerciseViewModel.Factory
    )
    ExercisePageContents(modifier = Modifier
        .height(1000.dp)
        .width(400.dp), exerciseViewModel = exerciseViewModel)
}
