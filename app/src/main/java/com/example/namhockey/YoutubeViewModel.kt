package com.example.namhockey

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch



class YouTubeViewModel : ViewModel() {

    var videoData by mutableStateOf<Map<String, Snippet>>(emptyMap())
        private set

    fun loadVideos(videoIds: List<String>, apiKey: String) {
        viewModelScope.launch {



            try {
                val response = RetrofitClient.apiService.getVideoDetails(
                    videoIds = videoIds.joinToString(","),
                    apiKey = apiKey
                )
                val updatedData = response.items.associate { it.id to it.snippet }
                videoData = updatedData
            } catch (e: Exception) {
                Log.e("YouTube", "Error fetching videos", e)
            }
        }
    }
}




