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
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

import com.raposo.chefmaster.R
import com.raposo.chefmaster.ui.theme.ChefMasterTheme

@Composable
fun HomeScreen(onNavigateToAudio: () -> Unit, onNavigateToVideo: () -> Unit, onNavigateToVideo2: () -> Unit) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.statusBars.asPaddingValues())
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Título temático
        Text(
            text = "ChefMaster",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Catálogo de Recetas",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // IMAGEN LOCAL
        Text(text = "Especialidad Local: Pasta Italiana", style = MaterialTheme.typography.titleLarge)
        Image(
            painter = painterResource(id = R.drawable.receta_pasta),
            contentDescription = "Plato de pasta",
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = onNavigateToVideo,
                modifier = Modifier.weight(1f)
            ) {
                Text("Ver")
            }
            Spacer(modifier = Modifier.padding(horizontal = 8.dp))
            Button(
                onClick = onNavigateToAudio,
                modifier = Modifier.weight(1f)
            ) {
                Text("Escuchar")
            }
        }

        // IMAGEN REMOTA
        Text(text = "Sugerencia del día: Pizza", style = MaterialTheme.typography.titleLarge)
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
                .height(180.dp),
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = onNavigateToVideo2,
                modifier = Modifier.weight(1f)
            ) {
                Text("Ver")
            }
            Spacer(modifier = Modifier.padding(horizontal = 8.dp))
            Button(
                onClick = onNavigateToAudio,
                modifier = Modifier.weight(1f)
            ) {
                Text("Escuchar")
            }
        }
        Spacer(modifier = Modifier.height(16.dp)) // Espacio final en la parte inferior
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    ChefMasterTheme {
        HomeScreen(onNavigateToAudio = {}, onNavigateToVideo = {}, onNavigateToVideo2 = {})
    }
}
