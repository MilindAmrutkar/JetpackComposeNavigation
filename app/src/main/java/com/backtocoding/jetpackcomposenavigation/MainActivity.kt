package com.backtocoding.jetpackcomposenavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.backtocoding.jetpackcomposenavigation.ui.theme.JetpackComposeNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeNavigationTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "form") {
                    // Form screen - collects data and stores it in the Form entry's SavedStateHandle
                    composable("form") { FormScreen(navController) }

                    // Profile screen - retrieves data from the previous back-stack entry's SavedStateHandle
                    composable("profile") {
                        // previousBackStackEntry refers to the "form" entry
                       val formEntry = navController.previousBackStackEntry
                        val user: User? = formEntry
                            ?.savedStateHandle
                            ?.get<User>("user")

                        user?.let {
                            ProfileScreen(user = it)
                        }
                    }
                }
            }
        }
    }
}

