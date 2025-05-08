package com.example.namhockey

import android.provider.MediaStore.Video
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeApiService {
    @GET("videos")
    suspend fun getVideoDetails(
        @Query("part") part: String = "snippet",
        @Query("id") videoIds: String,  // comma-separated
        @Query("key") apiKey: String
    ): YouTubeResponse

}

data class YouTubeResponse(
    val items: List<YouTubeVideoItem>
)

data class YouTubeVideoItem(
    val id: String,
    val snippet: Snippet
)

data class Snippet(
    val title: String,
    val thumbnails: Thumbnails
)

data class Thumbnails(
    val medium: Thumbnail
)

data class Thumbnail(
    val url: String
)
