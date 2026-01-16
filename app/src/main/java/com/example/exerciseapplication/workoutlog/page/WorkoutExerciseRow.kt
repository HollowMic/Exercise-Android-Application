package com.example.exerciseapplication.workoutlog.page

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.exerciseapplication.data.entity.Exercise
import com.example.exerciseapplication.workoutlog.WorkoutLogViewModel

@Composable
fun WorkoutExerciseRow(
    exerciseItem: Exercise,
    workoutLogViewModel: WorkoutLogViewModel
) {

    Surface(
        color = Color(0xFF336622)
    ) {
        Column(
            modifier = Modifier
                .animateContentSize()
                .height(60.dp)
                .fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(10.dp, 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { workoutLogViewModel.showAddWorkout(exerciseItem) }) {
                        Icon(imageVector = Icons.Rounded.Add, contentDescription = "")
                    }
                }
            }
        }
    }

}