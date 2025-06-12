package com.backtocoding.jetpackcomposenavigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.onboardingNavGraph(
    navController: NavController,
    onFinishOnboarding: () -> Unit
) {
    navigation(
        route = "onboarding",
        startDestination = "step1"
    ) {
        composable("step1") {
            Step1Screen(onNext =  { navController.navigate("step2") } )
        }
        composable("step2") {
            Step2Screen(onFinish = {
                onFinishOnboarding()
                navController.navigate("auth") {
                    popUpTo("onboarding") {
                        inclusive = true
                    }
                }
            })
        }
    }
}

fun NavGraphBuilder.authNavGraph(
    navController: NavController,
    onLoginSuccess: () -> Unit
) {
    navigation(
        route = "auth",
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(onLogin = {
                onLoginSuccess()
                navController.navigate("main") {
                    popUpTo("auth") { inclusive = true }
                    launchSingleTop = true
                }
            })
        }
        composable("signup") {
            SignupScreen(onSignup = {
                onLoginSuccess()
                navController.navigate("main") {
                    popUpTo("auth") { inclusive = true }
                    launchSingleTop = true
                }
            })
        }
    }
}

fun NavGraphBuilder.mainNavGraph(navController: NavController) {
    navigation(
        route = "main",
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(onProfile =  { navController.navigate("profile") })
        }
        composable("profile") {
            ProfileScreen(onLogout =  {
                navController.navigate("auth") {
                    popUpTo("main") { inclusive = true }
                    launchSingleTop = true
                }
            })
        }
    }
}





















