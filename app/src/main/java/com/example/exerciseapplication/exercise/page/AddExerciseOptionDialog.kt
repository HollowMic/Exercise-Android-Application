package com.example.exerciseapplication.exercise.page

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
import com.example.exerciseapplication.exercise.ExerciseViewModel
import com.example.exerciseapplication.ui.theme.GreySurface

@Composable
fun AddExerciseOptionDialog(modifier: Modifier = Modifier, exerciseViewModel: ExerciseViewModel, closeFunction: () -> Unit) {
    Dialog(onDismissRequest = { closeFunction() }) {

        var name by remember { mutableStateOf("") }
        var weight by remember { mutableFloatStateOf(0.0f) }
        var numOfSets by remember { mutableIntStateOf(3) }
        var numOfReps by remember { mutableIntStateOf(10) }


        fun saveExercise() {
            exerciseViewModel.addExercise(name, weight, numOfSets, numOfReps)
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
                TextField(value = name, onValueChange = { name = it })
                TextField(value = weight.toString(), onValueChange = {
                    try {
                        weight = it.toFloat()
                    } catch (_: NumberFormatException) {}
                })
                TextField(value = numOfSets.toString(), onValueChange = {
                    try {
                        numOfSets = it.toInt()
                    } catch (_: NumberFormatException) {}
                })
                TextField(value = numOfReps.toString(), onValueChange = {
                    try {
                        numOfReps = it.toInt()
                    } catch (_: NumberFormatException) {}
                })
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextButton(onClick = { closeFunction() }) {
                        Text(text = stringResource(id = R.string.cancel))
                    }
                    Button(onClick = { saveExercise() }) {
                        Text(text = stringResource(id = R.string.save))
                    }
                }
            }
        }



    }
}