package com.example.exerciseapplication.exercise.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp

@Composable
fun LoadingPopup(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xcc000000)),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = modifier
                .padding(10.dp)
                .background(Color(0x80999999)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
                    .background(Color(0x80999999)),
                shape = RoundedCornerShape(3.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0x80999999), contentColor = Color(0xFF000000))
            ) {
                Text(
                    modifier = Modifier
                        .padding(20.dp),
                    text = "Loading...",
                    textAlign = TextAlign.Center,
                    fontSize = TextUnit(30F, TextUnitType.Sp),
                )
            }

        }
    }
}