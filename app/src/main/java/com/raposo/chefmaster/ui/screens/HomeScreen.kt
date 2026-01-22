import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.raposo.chefmaster.R

@Composable
fun HomeScreen(onNavigateToAudio: () -> Unit, onNavigateToVideo: () -> Unit, onNavigateToVideo2: () -> Unit) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.statusBars.asPaddingValues())
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // --- TÍTULO PRINCIPAL ---
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "ChefMaster",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = "Catálogo de Recetas",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(16.dp))

        // --- TARJETA 1: PASTA ---
        RecipeCard(
            title = "Especialidad Local: Pasta Italiana",
            imageContent = {
                Image(
                    painter = painterResource(id = R.drawable.receta_pasta),
                    contentDescription = "Plato de pasta",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
            },
            onNavigateToVideo = onNavigateToVideo,
            onNavigateToAudio = onNavigateToAudio
        )

        // --- TARJETA 2: PIZZA ---
        RecipeCard(
            title = "Sugerencia del día: Pizza",
            imageContent = {
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data("https://www.foodandwine.com/thmb/Wd4lBRZz3X_8qBr69UOu2m7I2iw=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/classic-cheese-pizza-FT-RECIPE0422-31a2c938fc2546c9a07b7011658cfd05.jpg")
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.placeholder),
                    error = painterResource(R.drawable.error),
                    contentDescription = "Receta de internet",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
            },
            onNavigateToVideo = onNavigateToVideo2,
            onNavigateToAudio = onNavigateToAudio
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}

/**
 * Un Composable reutilizable para mostrar una tarjeta de receta.
 */
@Composable
fun RecipeCard(
    title: String,
    imageContent: @Composable () -> Unit,
    onNavigateToVideo: () -> Unit,
    onNavigateToAudio: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = MaterialTheme.shapes.large // Bordes más redondeados
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Título de la receta
            Text(text = title, style = MaterialTheme.typography.titleLarge)

            // Contenido de la imagen
            imageContent()

            // Fila de botones
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Botón principal
                Button(
                    onClick = onNavigateToVideo,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Ver preparación",
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text("Ver")
                }
                // Botón secundario
                OutlinedButton(
                    onClick = onNavigateToAudio,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.VolumeUp,
                        contentDescription = "Escuchar preparación",
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text("Escuchar")
                }
            }
        }
    }
}