package com.raposo.chefmaster.ui.screens

import android.media.MediaPlayer
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun AudioScreen(audioResId: Int, onBack: () -> Unit) {
    val context = LocalContext.current

    // Uso de remember para no recrear el reproductor en recomposición
    val player = remember {
        MediaPlayer.create(context, audioResId)
    }

    // Gestión del ciclo de vida con DisposableEffect
    DisposableEffect(Unit) {
        onDispose {
            try {
                if (player.isPlaying) {
                    player.stop()
                }
            } catch (_: IllegalStateException) {
                // Manejo de estado para evitar cierres inesperados
            }
            player.release()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Instrucciones de Audio",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(
                onClick = {
                    if (!player.isPlaying) {
                        player.start()
                    }
                }
            ) {
                Text("Play")
            }

            Button(
                onClick = {
                    if (player.isPlaying) {
                        player.pause()
                    }
                }
            ) {
                Text("Pause")
            }

            Button(
                onClick = {
                    // Reiniciar vuelve al inicio sin auto play
                    player.seekTo(0)
                    if (player.isPlaying) {
                        player.pause()
                    }
                }
            ) {
                Text("Reiniciar")
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        Button(onClick = onBack) {
            Text("Volver al Catálogo")
        }
    }
}