package com.example.exerciseapplication

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun AppDrawer(
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    navController: NavController,
    closeDrawer: () -> Unit,
    coroutineScope: CoroutineScope
) {
    val context = LocalContext.current
    ModalDrawerSheet {
        NavigationDrawerItem(
            label = { Text(text = stringResource(id = R.string.exercise_page_title)) },
            selected = true,
            onClick = {
                pressNavBarOption(
                    navController,
                    coroutineScope,
                    drawerState,
                    context.resources.getString(R.string.route_exercise)
                )
            }
        )
        NavigationDrawerItem(
            label = { Text(text = stringResource(id = R.string.workout_log_page_title)) },
            selected = true,
            onClick = {
                pressNavBarOption(
                    navController,
                    coroutineScope,
                    drawerState,
                    context.resources.getString(R.string.route_workout_log)
                )
            }
        )
        NavigationDrawerItem(
            label = { Text(text = stringResource(id = R.string.inactive_exercise_page_title)) },
            selected = true,
            onClick = {
                pressNavBarOption(
                    navController,
                    coroutineScope,
                    drawerState,
                    context.resources.getString(R.string.route_inactive)
                )
            }
        )
    }
}

fun pressNavBarOption(navigation: NavController, scope: CoroutineScope, drawerState: DrawerState, navLocation: String) {
    scope.launch {
        drawerState.apply {
            if (isClosed) open() else close()
        }
    }
    navigation.navigate(navLocation)
}