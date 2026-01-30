package com.example.exerciseapplication.exercise.page

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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

    val context = LocalContext.current

    val downloadDatabase = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CreateDocument("text/plain")
    ) { uri: Uri? ->

        val exportData = exerciseViewModel.getDatabaseData()
        val json = Json { prettyPrint = true }

        val jsonString = json.encodeToString(exportData)

        uri?.let {
            context.contentResolver.openOutputStream(it)?.use { outputStream ->
                outputStream.write(jsonString.toByteArray())
            }
        }
    }

    val uploadDatabase = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = { uri ->
            uri?.let {
                context.contentResolver.openInputStream(it)?.use { inputStream ->
                    val json = Json { prettyPrint = true }
                    val jsonString = inputStream
                        .bufferedReader()
                        .use { it.readText() }

                    val importData = json.decodeFromString<ExportData>(jsonString)
                    exerciseViewModel.uploadNewDatabase(importData)
                }
            }
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


        if (showDialog) {
            AddExerciseOptionDialog(modifier = it, exerciseViewModel = exerciseViewModel, closeFunction = { hideDialog() })
        }
        ExercisePageContents(modifier = it, exerciseViewModel = exerciseViewModel)
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
