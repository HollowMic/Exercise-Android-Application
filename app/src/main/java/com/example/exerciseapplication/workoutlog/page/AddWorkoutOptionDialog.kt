package com.example.exerciseapplication.workoutlog.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.exerciseapplication.R
import com.example.exerciseapplication.ui.theme.GreySurface
import com.example.exerciseapplication.workoutlog.WorkoutLogViewModel
import java.time.LocalDate

@Composable
fun AddWorkoutOptionDialog(modifier: Modifier = Modifier, workoutLogViewModel: WorkoutLogViewModel, closeFunction: () -> Unit) {
    Dialog(onDismissRequest = { closeFunction() }) {

        var name by remember { mutableStateOf("") }
        var weight by remember { mutableFloatStateOf(0.0f) }
        var numOfSets by remember { mutableIntStateOf(3) }
        var numOfReps by remember { mutableIntStateOf(10) }
        val notes by remember { mutableStateOf("") }



        fun saveWorkout() {
            workoutLogViewModel.addWorkout(LocalDate.now(), weight, numOfSets, numOfReps, notes)
            closeFunction()
        }

        Card(
            shape = ShapeDefaults.Medium,
            colors = CardDefaults.cardColors(containerColor = GreySurface),
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp, 100.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Exercise Name") }
                )
                TextField(
                    value = weight.toString(),
                    label = { Text("Weight Amount (kg)") },
                    onValueChange = {
                        try {
                            weight = it.toFloat()
                        } catch (_: NumberFormatException) {}
                    }
                )
                TextField(
                    value = numOfSets.toString(),
                    label = { Text("Number of sets") },
                    onValueChange = {
                        try {
                            numOfSets = it.toInt()
                        } catch (_: NumberFormatException) {}
                    }
                )
                TextField(
                    value = numOfReps.toString(),
                    label = { Text("Number of Repetitions") },
                    onValueChange = {
                        try {
                            numOfReps = it.toInt()
                        } catch (_: NumberFormatException) {}
                    }
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextButton(onClick = { closeFunction() }) {
                        Text(text = stringResource(id = R.string.cancel))
                    }
                    Button(onClick = { saveWorkout() }) {
                        Text(text = stringResource(id = R.string.save))
                    }
                }
            }
        }



    }
}