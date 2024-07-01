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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Counter(
    modifier: Modifier = Modifier,
    startValue: Float = 0.0f,
) {
    Box(modifier = modifier){

        var value by remember { mutableStateOf(startValue.toString()) }

        Row(
            modifier = Modifier.padding(5.dp, 0.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            value = startValue.toString()
            if (startValue.compareTo(startValue.toInt()) == 0) {
                value = startValue.toInt().toString()
            }
            Text(
                modifier = Modifier,
                text = value
            )
            Column(
                modifier = Modifier.height(40.dp)
            ) {
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.size(20.dp)
                ) {
                    Icon(imageVector = Icons.Rounded.KeyboardArrowUp, contentDescription = "")
                }
                IconButton(
                    onClick = { /*TODO*/ },
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
    Counter()
}