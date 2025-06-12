package com.backtocoding.jetpackcomposenavigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavGraph() {
    // Simulated persistent state
    var hasOnboarded by rememberSaveable { mutableStateOf(false) }
    var isLoggedIn by rememberSaveable { mutableStateOf(false) }

    // Decide start destination dynamically
    val startDestination = when {
        hasOnboarded.not() -> "onboarding"
        isLoggedIn.not() -> "auth"
        else -> "main"
    }

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        onboardingNavGraph(navController) { hasOnboarded = true }
        authNavGraph(navController) { isLoggedIn = true }
        mainNavGraph(navController)
    }
}

























