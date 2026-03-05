package com.example.exerciseapplication.exercise.page

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.exerciseapplication.Page
import com.example.exerciseapplication.R
import com.example.exerciseapplication.data.ExportData
import com.example.exerciseapplication.exercise.ExerciseViewModel
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun ExercisePage(
    navigate: () -> Unit,
    openDrawer: () -> Unit,
    exerciseViewModel: ExerciseViewModel
) {
    var showDialog by remember { mutableStateOf(false) }
    fun showingDialog() {
        showDialog = true
    }
    fun hideDialog() {
        showDialog = false
    }

    var showLoading by remember { mutableStateOf(false) }
    fun showingLoading() {
        showLoading = true
    }
    fun hideLoading() {
        showLoading = false
    }

    val context = LocalContext.current

    val downloadDatabase = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CreateDocument("text/plain")
    ) { uri: Uri? ->

        showingLoading()

        val exportData = runBlocking { exerciseViewModel.getDatabaseData() }
        val json = Json { prettyPrint = true }

        val jsonString = json.encodeToString(exportData)

        uri?.let {
            context.contentResolver.openOutputStream(it)?.use { outputStream ->
                outputStream.write(jsonString.toByteArray())
            }
        }.also { hideLoading() }

    }

    val uploadDatabase = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = { uri ->
            showingLoading()
            uri?.let {
                context.contentResolver.openInputStream(it)?.use { inputStream ->
                    val json = Json { prettyPrint = true }
                    val jsonString = inputStream
                        .bufferedReader()
                        .use { it.readText() }

                    val importData = json.decodeFromString<ExportData>(jsonString)
                    exerciseViewModel.uploadNewDatabase(importData)
                }
            }.also { hideLoading() }
        }
    )

    Page(
        pageName = R.string.exercise_page_title,
        navigate = navigate,
        openDrawer = openDrawer,
        actions = listOf {
            IconButton(onClick = { showingDialog() }) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "")
            }
            IconButton(onClick = { downloadDatabase.launch("Database_1.json") }) {
                Icon(
                    painter = painterResource(R.drawable.outline_download_24),
                    contentDescription = "Download Database"
                )
            }
            IconButton(onClick = { uploadDatabase.launch(arrayOf("application/json", "text/plain")) }) {
                Icon(
                    painter = painterResource(R.drawable.outline_upload_24),
                    contentDescription = "Upload Database"
                )
            }
        }
    ) {

        Box(modifier = it.fillMaxSize()) {

            ExercisePageContents(
                modifier = Modifier.matchParentSize(),
                exerciseViewModel = exerciseViewModel
            )

            if (showDialog) {
                AddExerciseOptionDialog(
                    modifier = Modifier.align(Alignment.Center),
                    exerciseViewModel = exerciseViewModel,
                    closeFunction = { hideDialog() }
                )
            }

            AnimatedVisibility(
                visible = showLoading,
                enter = fadeIn(animationSpec = tween(150)),
                exit = fadeOut(animationSpec = tween(150)),
                modifier = Modifier.matchParentSize()
            ) {
                LoadingPopup(Modifier.matchParentSize())
            }
        }
    }
}

@Composable
fun ExercisePageContents(modifier: Modifier, exerciseViewModel: ExerciseViewModel) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            DateRow(modifier, exerciseViewModel)
            ExerciseList(modifier, exerciseViewModel)
        }
    }
}

@Composable
@Preview
fun ExercisePageContentsPreview() {
    val exerciseViewModel: ExerciseViewModel = viewModel(
        factory = ExerciseViewModel.Factory
    )
    ExercisePageContents(modifier = Modifier
        .height(1000.dp)
        .width(400.dp), exerciseViewModel = exerciseViewModel)
}
