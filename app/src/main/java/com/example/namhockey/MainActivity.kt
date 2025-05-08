package com.example.namhockey


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val newsViewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        val youTubeViewModel = ViewModelProvider(this)[YouTubeViewModel::class.java]

        setContent {
            AppNavigation(newsViewModel, youTubeViewModel)
        }
    }
}

