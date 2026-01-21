import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun HomeScreen(onNavigateToAudio: () -> Unit) { // Añadimos navegación
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Título temático
        Text(
            text = "ChefMaster: Catálogo de Recetas",
            style = MaterialTheme.typography.headlineMedium
        )

        // IMAGEN LOCAL
        Text(text = "Especialidad Local: Pasta Italiana", style = MaterialTheme.typography.titleLarge)
        Image(
            painter = painterResource(id = R.drawable.receta_pasta), // Imagen
            contentDescription = "Plato de pasta",
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            contentScale = ContentScale.Crop
        )

        // BOTÓN PARA IR A LA SIGUIENTE PANTALLA
        Button(
            onClick = onNavigateToAudio,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver preparación")
        }

        // BOTÓN PARA IR A LA VENTANA AUDIO
        Button(
            onClick = onNavigateToAudio,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Escuchar Preparación")
        }


        // IMAGEN REMOTA
        Text(text = "Sugerencia del día: Pizza", style = MaterialTheme.typography.titleLarge)
        AsyncImage(
            model = ImageRequest.Builder(context)
                // Usamos una URL de una imagen del plato
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

        // BOTÓN PARA IR A LA SIGUIENTE PANTALLA
        Button(
            onClick = onNavigateToAudio,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver preparación")
        }

        // BOTÓN PARA IR A LA VENTANA AUDIO
        Button(
            onClick = onNavigateToAudio,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Escuchar Preparación")
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    ChefMasterTheme {
        HomeScreen(onNavigateToAudio = {})
    }
}
