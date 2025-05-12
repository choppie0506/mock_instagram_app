package com.example.pep_insta.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pep_insta.presentation.screens.auth.AuthScreen
import com.example.pep_insta.presentation.screens.main.MainScreen
import com.example.pep_insta.presentation.theme.PepInstaTheme
import com.example.pep_insta.presentation.LoginStatusManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PepInstaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PepInstaApp()
                }
            }
        }
    }
}

@Composable
fun PepInstaApp() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val isLoggedIn = remember { LoginStatusManager.isLoggedIn(context) }

    LaunchedEffect(isLoggedIn) {
        if (isLoggedIn) {
            navController.navigate("main") {
                popUpTo(0)
            }
        } else {
            navController.navigate("auth") {
                popUpTo(0)
            }
        }
    }

    NavHost(navController = navController, startDestination = if (isLoggedIn) "main" else "auth") {
        composable("auth") {
            AuthScreen(navController)
        }
        composable("main") {
            MainScreen(navController)
        }
        composable("home") {
            // TODO: Implement HomeScreen
        }
        composable("discover") {
            // TODO: Implement DiscoverScreen
        }
        composable("upload") {
            // TODO: Implement UploadScreen
        }
        composable("notifications") {
            // TODO: Implement NotificationsScreen
        }
        composable("profile") {
            // TODO: Implement ProfileScreen
        }
    }
} 