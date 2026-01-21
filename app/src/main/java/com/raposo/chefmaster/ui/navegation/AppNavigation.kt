package com.raposo.chefmaster.ui.navegation

import HomeScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.raposo.chefmaster.ui.screens.AudioScreen
import com.raposo.chefmaster.R

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                // Al pulsar Pasta, pasamos su audio
                onNavigateToAudio = { navController.navigate("audio/${R.raw.audio_pasta}") },
                onNavigateToVideo = { navController.navigate("video") },
                onNavigateToVideo2 = { navController.navigate("video2") }
            )
        }

        // Definimos que la ruta recibe un argumento (el ID del audio)
        composable("audio/{audioId}") { backStackEntry ->
            val audioId = backStackEntry.arguments?.getString("audioId")?.toInt() ?: R.raw.audio_pasta
            AudioScreen(
                audioResId = audioId,
                onBack = { navController.popBackStack() }
            )
        }
    }
}