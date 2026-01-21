package com.raposo.chefmaster.ui.navegation

import HomeScreen
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.raposo.chefmaster.R
import com.raposo.chefmaster.ui.screens.AudioScreen
import com.raposo.chefmaster.ui.screens.VideoScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        // --- PANTALLA 1: HOME (Catálogo) ---
        composable(route = "home") {
            HomeScreen(
                // Navegación para Pasta (Audio local y Vídeo local)
                onNavigateToAudio = {
                    navController.navigate("audio/${R.raw.audio_pasta}")
                },
                onNavigateToVideo = {
                    val videoUri = "android.resource://${context.packageName}/${R.raw.video_pasta}"
                    navController.navigate("video/${Uri.encode(videoUri)}")
                },
                // Navegación para Pizza (Mismo audio, pero Vídeo remoto)
                onNavigateToVideo2 = {
                    val videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                    navController.navigate("video/${Uri.encode(videoUrl)}")
                }
            )
        }

        // --- PANTALLA 2: AUDIO ---
        composable(
            route = "audio/{audioId}",
            arguments = listOf(navArgument("audioId") { type = NavType.IntType })
        ) { backStackEntry ->
            val audioId = backStackEntry.arguments?.getInt("audioId") ?: R.raw.audio_pasta
            AudioScreen(
                audioResId = audioId,
                onBack = { navController.popBackStack() }
            )
        }

        // --- PANTALLA 3: VÍDEO ---
        composable(
            route = "video/{videoUri}",
            arguments = listOf(navArgument("videoUri") { type = NavType.StringType })
        ) { backStackEntry ->
            val videoUri = backStackEntry.arguments?.getString("videoUri") ?: ""
            VideoScreen(
                videoUri = videoUri,
                onBack = { navController.popBackStack() }
            )
        }
    }
}