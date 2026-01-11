package com.example.exerciseapplication.inactiveexercise.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.exerciseapplication.Page
import com.example.exerciseapplication.R
import com.example.exerciseapplication.inactiveexercise.InactiveViewModel

@Composable
fun InactivePage(
    navigate: () -> Unit,
    openDrawer: () -> Unit,
    inactiveViewModel: InactiveViewModel
) {
    var showDialog by remember { mutableStateOf(false) }
    fun showingDialog() {
        showDialog = true
    }
    fun hideDialog() {
        showDialog = false
    }

    Page(
        pageName = R.string.inactive_exercise_page_title,
        navigate = navigate,
        openDrawer = openDrawer,
        actions = listOf {
            IconButton(onClick = { showingDialog() }) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "")
            }
        }
    ) {

        InactivePageContents(modifier = it, inactiveViewModel = inactiveViewModel)

    }
}

@Composable
fun InactivePageContents(modifier: Modifier, inactiveViewModel: InactiveViewModel) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
//            val exerciseList = exerciseViewModel.exercises.collectAsState(initial = emptyList())
//            Text(text = exerciseList.value.size.toString())
            InactiveExerciseList(modifier, inactiveViewModel)
        }
    }
}