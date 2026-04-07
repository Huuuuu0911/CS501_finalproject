package com.example.cs501_finalproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cs501_finalproject.ui.HomeScreen
import com.example.cs501_finalproject.ui.MapScreen
import com.example.cs501_finalproject.ui.ResultScreen
import com.example.cs501_finalproject.ui.TriageScreen

object Routes {
    const val HOME = "home"
    const val TRIAGE = "triage"
    const val RESULT = "result"
    const val MAP = "map"
}

@Composable
fun AppNav() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        composable(Routes.HOME) {
            HomeScreen(
                onStartClick = {
                    navController.navigate(Routes.TRIAGE)
                }
            )
        }

        composable(Routes.TRIAGE) {
            TriageScreen(
                onSubmitClick = {
                    navController.navigate(Routes.RESULT)
                }
            )
        }

        composable(Routes.RESULT) {
            ResultScreen(
                onFindCareClick = {
                    navController.navigate(Routes.MAP)
                }
            )
        }

        composable(Routes.MAP) {
            MapScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}