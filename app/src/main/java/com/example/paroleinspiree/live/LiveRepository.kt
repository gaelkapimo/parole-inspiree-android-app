package com.example.paroleinspiree.live

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL

class LiveRepository(
    private val apiKey: String = "AIzaSyDp5m2Llyg7QRyK8fBj9ePbgQj2BYLZTOU",
    private val channelId: String = "UCVIBvBgEi6GEe5usXFSUYzw"
) {
    suspend fun fetchLiveInfo(): LiveInfo = withContext(Dispatchers.IO) {
        try {
            // Appel à l'API YouTube pour récupérer le live en cours
            val url =
                "https://www.googleapis.com/youtube/v3/search?part=snippet&channelId=$channelId&type=video&eventType=live&key=$apiKey"
            val result = URL(url).readText()
            val json = JSONObject(result)
            val items = json.getJSONArray("items")

            if (items.length() > 0) {
                val liveVideo = items.getJSONObject(0)
                val videoId = liveVideo.getJSONObject("id").getString("videoId")
                val title = liveVideo.getJSONObject("snippet").getString("title")
                LiveInfo(videoId = videoId, title = title, status = "live")
            } else {
                LiveInfo(status = "offline")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            LiveInfo(status = "offline")
        }
    }
}