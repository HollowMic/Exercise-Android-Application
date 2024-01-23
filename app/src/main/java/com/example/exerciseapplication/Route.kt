package com.example.exerciseapplication

import androidx.compose.runtime.Composable

@Composable
fun Route(navController: NavController) {
    NavHost(navController = navController, startDestination = stringResource(R.string.route_exersice)) {
        composable(stringResource(R.string.route_exersice))
    }
}