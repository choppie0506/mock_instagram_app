package com.example.pep_insta.presentation.screens.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pep_insta.presentation.screens.discover.DiscoverScreen
import com.example.pep_insta.presentation.screens.home.HomeScreen
import com.example.pep_insta.presentation.screens.notifications.NotificationsScreen
import com.example.pep_insta.presentation.screens.profile.ProfileScreen
import com.example.pep_insta.presentation.screens.upload.UploadScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    val mainNavController = rememberNavController()
    val navBackStackEntry by mainNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = currentRoute == "home",
                    onClick = { mainNavController.navigate("home") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Search, contentDescription = "Discover") },
                    label = { Text("Discover") },
                    selected = currentRoute == "discover",
                    onClick = { mainNavController.navigate("discover") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Add, contentDescription = "Upload") },
                    label = { Text("Upload") },
                    selected = currentRoute == "upload",
                    onClick = { mainNavController.navigate("upload") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Notifications, contentDescription = "Notifications") },
                    label = { Text("Notifications") },
                    selected = currentRoute == "notifications",
                    onClick = { mainNavController.navigate("notifications") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = currentRoute == "profile",
                    onClick = { mainNavController.navigate("profile") }
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = mainNavController,
            startDestination = "home",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("home") {
                HomeScreen()
            }
            composable("discover") {
                DiscoverScreen()
            }
            composable("upload") {
                UploadScreen()
            }
            composable("notifications") {
                NotificationsScreen()
            }
            composable("profile") {
                ProfileScreen()
            }
        }
    }
} 