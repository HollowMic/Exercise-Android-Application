package com.example.exerciseapplication.workoutlog.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.exerciseapplication.Page
import com.example.exerciseapplication.R
import com.example.exerciseapplication.workoutlog.WorkoutLogViewModel

@Composable
fun WorkoutLogPage(
    navigate: () -> Unit,
    openDrawer: () -> Unit,
    workoutLogViewModel: WorkoutLogViewModel
) {


    Page(
        pageName = R.string.workout_log_page_title,
        navigate = navigate,
        openDrawer = openDrawer,
        actions = listOf {}
    ) {
        if (workoutLogViewModel.showDialog) {
            AddWorkoutOptionDialog(modifier = it, workoutLogViewModel = workoutLogViewModel, closeFunction = { workoutLogViewModel.hideDialog() })
        }

        WorkoutLogPageContents(modifier = it, workoutLogViewModel = workoutLogViewModel)

    }
}


@Composable
fun WorkoutLogPageContents(modifier: Modifier, workoutLogViewModel: WorkoutLogViewModel) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            val workoutList = workoutLogViewModel.workouts.collectAsState(initial = emptyList())
            Text(text = workoutList.value.size.toString())
            WorkoutLogList(modifier, workoutLogViewModel)
        }
    }
}
