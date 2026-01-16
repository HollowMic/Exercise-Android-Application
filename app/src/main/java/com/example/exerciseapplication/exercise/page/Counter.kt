package com.example.exerciseapplication.exercise.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Counter(
    modifier: Modifier = Modifier,
    value: Float,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit
) {
    Box(modifier = modifier){

        Row(
            modifier = Modifier.padding(5.dp, 0.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            var valueString = value.toString()
            if (value.compareTo(value.toInt()) == 0) {
                valueString = value.toInt().toString()
            }
            Text(
                modifier = Modifier,
                text = valueString
            )
            Column(
                modifier = Modifier.height(40.dp)
            ) {
                IconButton(
                    onClick = onIncrement,
                    modifier = Modifier.size(20.dp)
                ) {
                    Icon(imageVector = Icons.Rounded.KeyboardArrowUp, contentDescription = "")
                }
                IconButton(
                    onClick = onDecrement,
                    modifier = Modifier.size(20.dp)
                ) {
                    Icon(imageVector = Icons.Rounded.KeyboardArrowDown, contentDescription = "")
                }
            }
        }
    }
}

@Preview
@Composable
fun CounterPreview() {
    Counter(Modifier, 3.4.toFloat(), {}, {})
}