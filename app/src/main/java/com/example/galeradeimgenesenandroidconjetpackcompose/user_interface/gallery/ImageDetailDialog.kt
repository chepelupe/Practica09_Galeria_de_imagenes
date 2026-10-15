package com.example.galeradeimgenesenandroidconjetpackcompose.ui.gallery

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest
import com.example.galeradeimgenesenandroidconjetpackcompose.model.GalleryImage

/**
 * Vista ampliada de una imagen dentro de un cuadro de diálogo.
 * Se cierra al tocar fuera del diálogo, con el botón atrás o con el botón "Cerrar".
 */
@Composable
fun ImageDetailDialog(
    image: GalleryImage,
    onDismiss: () -> Unit
) {
    var state by remember { mutableStateOf<AsyncImagePainter.State>(AsyncImagePainter.State.Empty) }

    Dialog(
        onDismissRequest = onDismiss,
        // Permite que el diálogo use casi todo el ancho de la pantalla.
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.92f)
                .padding(vertical = 24.dp),
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.surface
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(image.fullUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = image.title,
                        contentScale = ContentScale.Fit,
                        onState = { state = it },
                        modifier = Modifier.fillMaxWidth()
                    )

                    when (state) {
                        is AsyncImagePainter.State.Loading ->
                            CircularProgressIndicator(modifier = Modifier.size(48.dp))
                        is AsyncImagePainter.State.Error ->
                            Text(
                                text = "No se pudo cargar la imagen",
                                color = MaterialTheme.colorScheme.error
                            )
                        else -> Unit
                    }
                }

                Text(
                    text = image.title,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 12.dp)
                )

                TextButton(
                    onClick = onDismiss,
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Text("Cerrar")
                }
            }
        }
    }
}