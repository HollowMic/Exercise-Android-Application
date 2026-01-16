package com.example.exerciseapplication.exercise.page

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.exerciseapplication.exercise.ExerciseViewModel
import com.example.exerciseapplication.utils.DateUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRow(modifier: Modifier = Modifier, exerciseViewModel: ExerciseViewModel) {
    Box {

        var expanded by remember { mutableStateOf(false) }
        fun toggleExpanded() {
            expanded = !expanded
        }
        val dateState = rememberDatePickerState()
        val millisToLocalDate = dateState.selectedDateMillis?.let {
            DateUtils().convertMillisToLocalDate(it)
        }
        val dateToString = millisToLocalDate?.let {
            DateUtils().dateToString(millisToLocalDate)
        } ?: "Choose Date"
        exerciseViewModel.setDate(millisToLocalDate)

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(if (expanded) 600.dp else 60.dp)
                .animateContentSize(),
            color = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ) {
            Column(
                verticalArrangement = Arrangement.Top
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    val date = dateToString
                    Text(text = date, modifier = Modifier.padding(20.dp, 0.dp))
                    IconButton(
                        modifier = Modifier.padding(20.dp, 0.dp),
                        onClick = { toggleExpanded() }
                    ) {
                        Icon(imageVector = Icons.Rounded.DateRange, "")
                    }
                }

                if (expanded) {
                    Card(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.secondaryContainer)
                            .padding(10.dp)
                    ) {
                        DatePicker(
                            state = dateState,
                            )
                    }
                }
            }

        }

    }
}

@Composable
@Preview
fun DateRowPreview() {
    val exerciseViewModel: ExerciseViewModel = viewModel(
        factory = ExerciseViewModel.Factory
    )
    DateRow(Modifier, exerciseViewModel)
}