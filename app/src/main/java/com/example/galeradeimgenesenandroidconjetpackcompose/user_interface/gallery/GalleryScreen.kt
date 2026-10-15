package com.example.galeradeimgenesenandroidconjetpackcompose.ui.gallery

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.galeradeimgenesenandroidconjetpackcompose.model.GalleryImage
import com.example.galeradeimgenesenandroidconjetpackcompose.model.SampleImages

/**
 * Pantalla principal: cuadrícula adaptativa de imágenes.
 * Mantiene el estado de la imagen seleccionada para mostrar el detalle.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalleryScreen(
    images: List<GalleryImage> = SampleImages.images,
    modifier: Modifier = Modifier
) {
    // Guardamos solo el id (un Int se puede guardar en rotaciones; la clase no).
    var selectedId by rememberSaveable { mutableStateOf<Int?>(null) }
    val selectedImage = images.find { it.id == selectedId }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Mi Galería") })
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            // Adaptive: cada celda mide al menos 150dp y se reparte el espacio sobrante.
            columns = GridCells.Adaptive(minSize = 150.dp),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // key permite a Compose reutilizar celdas y mantener el scroll estable.
            items(items = images, key = { it.id }) { image ->
                GalleryItem(
                    image = image,
                    onClick = { selectedId = image.id }
                )
            }
        }
    }

    // Si hay una imagen seleccionada, se muestra el diálogo de detalle.
    selectedImage?.let { image ->
        ImageDetailDialog(
            image = image,
            onDismiss = { selectedId = null }
        )
    }
}