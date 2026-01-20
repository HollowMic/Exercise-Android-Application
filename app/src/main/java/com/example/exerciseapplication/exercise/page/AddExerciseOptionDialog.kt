package com.example.exerciseapplication.exercise.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.exerciseapplication.R
import com.example.exerciseapplication.exercise.ExerciseViewModel
import com.example.exerciseapplication.ui.theme.GreySurface
import kotlin.math.roundToInt

@Composable
fun AddExerciseOptionDialog(modifier: Modifier = Modifier, exerciseViewModel: ExerciseViewModel, closeFunction: () -> Unit) {
    Dialog(onDismissRequest = { closeFunction() }) {

        var name by remember { mutableStateOf("") }
        var weight by remember { mutableFloatStateOf(0.0f) }
        var numOfSets by remember { mutableIntStateOf(3) }
        var numOfReps by remember { mutableIntStateOf(10) }
        var weightText by remember { mutableStateOf(weight.toString()) }
        var numOfSetsText by remember { mutableStateOf(numOfSets.toString()) }
        var numOfRepsText by remember { mutableStateOf(numOfReps.toString()) }

        fun roundToNearestHalf(value: Float): Float {
            return (value * 2).roundToInt() / 2f
        }

        fun saveExercise() {
            weight = roundToNearestHalf(weight)
            exerciseViewModel.addExercise(name, weight, numOfSets, numOfReps)
            closeFunction()
        }

        Card(
            shape = ShapeDefaults.Medium,
            colors = CardDefaults.cardColors(containerColor = GreySurface),
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 100.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text(stringResource(R.string.dialog_excercise_name)) }
                )
                TextField(
                    value = weightText,
                    label = { Text(stringResource(R.string.dialog_weight_amount)) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    ),
                    onValueChange = { input ->
                        if (input.matches(Regex("""\d*\.?\d*"""))) {
                            weightText = input
                            input.toFloatOrNull()?.let {
                                weight = it
                            }
                        }
                    }
                )
                TextField(
                    value = numOfSetsText,
                    label = { Text(stringResource(R.string.dialog_number_of_set)) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    ),
                    onValueChange = { input ->
                        if (input.matches(Regex("""\d*"""))) {
                            numOfSetsText = input
                            input.toIntOrNull()?.let {
                                numOfSets = it
                            }
                        }
                    }
                )
                TextField(
                    value = numOfRepsText,
                    label = { Text(stringResource(R.string.dialog_number_of_reps)) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    ),
                    onValueChange = { input ->
                        if (input.matches(Regex("""\d*"""))) {
                            numOfRepsText = input
                            input.toIntOrNull()?.let {
                                numOfReps = it
                            }
                        }
                    }
                )
                Row(
                    modifier = Modifier.fillMaxWidth().padding(0.dp, 10.dp, 0.dp, 0.dp),
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