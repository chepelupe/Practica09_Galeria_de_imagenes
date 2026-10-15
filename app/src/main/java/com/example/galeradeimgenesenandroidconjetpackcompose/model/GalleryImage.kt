package com.example.galeradeimgenesenandroidconjetpackcompose.model

/**
 * Representa una imagen de la galería.
 *
 * @param id Identificador único (también se usa como clave en la lista).
 * @param thumbnailUrl URL de la miniatura que se muestra en la cuadrícula (ligera).
 * @param fullUrl URL de la versión grande que se muestra en la vista de detalle.
 * @param title Texto descriptivo (accesibilidad y título del detalle).
 */
data class GalleryImage(
    val id: Int,
    val thumbnailUrl: String,
    val fullUrl: String,
    val title: String
)

/** Datos de prueba generados a partir de Picsum Photos. */
object SampleImages {

    // Los primeros ids son imágenes válidas de Picsum.
    // El último (9999) NO existe a propósito, para probar el estado de error.
    private val ids = listOf(1015, 1016, 1018, 1019, 1020, 1021, 1022, 1023, 1024, 1025, 1035, 1036, 9999)

    val images: List<GalleryImage> = ids.map { id ->
        GalleryImage(
            id = id,
            thumbnailUrl = "https://picsum.photos/id/$id/400/400",
            fullUrl = "https://picsum.photos/id/$id/1200/1200",
            title = "Imagen $id"
        )
    }
}