package com.example.exerciseapplication

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.exerciseapplication.exercise.ExerciseViewModel
import com.example.exerciseapplication.exercise.page.ExercisePage
import com.example.exerciseapplication.inactiveexercise.InactiveViewModel
import com.example.exerciseapplication.inactiveexercise.page.InactivePage
import kotlinx.coroutines.launch

@Composable
fun Route() {
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val navController = rememberNavController()

    ModalNavigationDrawer(
        drawerContent = {
            AppDrawer(
                navController = navController,
                closeDrawer = { coroutineScope.launch { drawerState.close() } },
                drawerState = drawerState,
                coroutineScope = coroutineScope
            )
        },
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
    ) {
        Row {


            val exercisePageString = stringResource(R.string.route_exercise)
            val inactivePageString = stringResource(R.string.route_inactive)
            NavHost(navController = navController, startDestination = exercisePageString) {
                composable(exercisePageString) { backStackEntry ->
                    val exerciseViewModel: ExerciseViewModel = viewModel(
                        backStackEntry,
                        factory = ExerciseViewModel.Factory,
                    )
                    ExercisePage(
                        navigate = {},
                        openDrawer = { coroutineScope.launch { drawerState.open() } },
                        exerciseViewModel = exerciseViewModel
                    )
                }
                composable(inactivePageString) { backStackEntry ->
                    val inactiveViewModel: InactiveViewModel = viewModel(
                        backStackEntry,
                        factory = InactiveViewModel.Factory
                    )
                    InactivePage(
                        navigate = {},
                        openDrawer = { coroutineScope.launch { drawerState.open() } },
                        inactiveViewModel = inactiveViewModel
                    )
                }
            }
        }
    }

}