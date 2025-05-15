package com.example.pep_insta.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pep_insta.presentation.screens.reels.ReelsScreen

sealed class Screen(val route: String) {
    object Reels : Screen("reels")
    // Add other screens here
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Reels.route
    ) {
        composable(Screen.Reels.route) {
            ReelsScreen()
        }
        // Add other screen routes here
    }
} 