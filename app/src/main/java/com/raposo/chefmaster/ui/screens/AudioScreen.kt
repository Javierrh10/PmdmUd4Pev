package com.raposo.chefmaster.ui.screens

import android.media.MediaPlayer
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class) // Necesario para TopAppBar
@Composable
fun AudioScreen(audioResId: Int, onBack: () -> Unit) {
    val context = LocalContext.current

    // Estado para saber si el audio se está reproduciendo
    var isPlaying by remember { mutableStateOf(false) }

    val player = remember(audioResId) {
        MediaPlayer.create(context, audioResId).apply {
            setOnCompletionListener {
                isPlaying = false // Actualiza el estado cuando termina
            }
        }
    }

    DisposableEffect(player) {
        onDispose {
            player.release()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Instrucciones de Audio") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Tarjeta que contiene los controles de audio
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                shape = MaterialTheme.shapes.large
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                        contentDescription = if (isPlaying) "Pausar" else "Reproducir",
                        modifier = Modifier.size(100.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = if (isPlaying) "Reproduciendo..." else "Pausado",
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Fila con los botones de acción
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Botón de Reiniciar
                        IconButton(onClick = {
                            player.seekTo(0)
                            if (!isPlaying) {
                                player.start()
                                isPlaying = true
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Replay,
                                contentDescription = "Reiniciar",
                                modifier = Modifier.size(32.dp)
                            )
                        }

                        // Botón principal del play y pausa
                        FilledIconButton(
                            onClick = {
                                if (player.isPlaying) {
                                    player.pause()
                                } else {
                                    player.start()
                                }
                                isPlaying = !isPlaying
                            },
                            modifier = Modifier.size(64.dp)
                        ) {
                            Icon(
                                imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                                contentDescription = if (isPlaying) "Pausar" else "Reproducir",
                                modifier = Modifier.size(40.dp)
                            )
                        }

                        Spacer(modifier = Modifier.size(32.dp))
                    }
                }
            }
        }
    }
}
