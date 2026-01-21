package com.raposo.chefmaster.ui.navegation

import HomeScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        // PANTALLA 1: Catalogo
        composable("home") {
            HomeScreen(
                onNavigateToAudio = { navController.navigate("audio") }
            )
        }

        // PANTALLA 3: Vídeo esto lo hago despues de haber hecho el audio ma tarde
        composable("video") {
            // VideoScreen()
        }
    }
}