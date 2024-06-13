package com.example.exerciseapplication.exercise.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.time.Instant
import java.util.Date

@Composable
fun DateRow(modifier: Modifier = Modifier) {
    Box {
        Surface(
            modifier = Modifier.fillMaxWidth().height(60.dp),
            color = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().height(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                val date = Date.from(Instant.now()).toString().subSequence(0, 10).toString()
                Text(text = date, modifier = Modifier.padding(20.dp, 0.dp))
                IconButton(
                    modifier = Modifier.padding(20.dp, 0.dp),
                    onClick = { /*TODO*/ }
                ) {
                    Icon(imageVector = Icons.Rounded.DateRange, "")
                }
            }
        }
    }
}

@Composable
@Preview
fun DateRowPreview() {
    DateRow(Modifier)
}