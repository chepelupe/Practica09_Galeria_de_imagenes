package com.example.galeradeimgenesenandroidconjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.galeradeimgenesenandroidconjetpackcompose.ui.gallery.GalleryScreen
import com.example.galeradeimgenesenandroidconjetpackcompose.ui.theme.GaleríaDeImágenesEnAndroidConJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GaleríaDeImágenesEnAndroidConJetpackComposeTheme {
                GalleryScreen()
            }
        }
    }
}