package com.example.namhockey


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)

        enableEdgeToEdge()
        val newsViewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        val youTubeViewModel = ViewModelProvider(this)[YouTubeViewModel::class.java]
        val loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        setContent {
            AppNavigation(newsViewModel, youTubeViewModel, loginViewModel)
        }
    }
}

