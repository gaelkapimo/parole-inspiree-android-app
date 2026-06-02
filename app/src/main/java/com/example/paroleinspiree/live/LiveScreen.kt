package com.example.paroleinspiree.live

import android.webkit.WebView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Composable
fun LiveScreen(viewModel: LiveViewModel = viewModel(factory = LiveViewModelFactory())) {

    val liveInfo by viewModel.liveInfo.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    val scrollState = rememberScrollState()
    val backgroundColor = Color(0xFFF5F5F5)
    val textColor = Color.Black

    LaunchedEffect(Unit) {
        viewModel.loadLive()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = liveInfo.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    color = textColor,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = when (liveInfo.status) {
                        "live" -> "🔴 En direct"
                        "soon" -> "⏳ Bientôt"
                        else -> "⚪ Hors ligne"
                    },
                    color = if (liveInfo.status == "live") Color.Red else textColor,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(16.dp))

                if (liveInfo.status == "live") {
                    YouTubeLivePlayer(videoId = liveInfo.videoId)

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = { viewModel.loadLive() },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0D47A1)),
                        modifier = Modifier.fillMaxWidth(0.6f)
                    ) {
                        Text("Actualiser le live", color = Color.White)
                    }
                } else {
                    Text(
                        "Le live n'est pas disponible pour le moment.",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 50.dp),
                        color = textColor
                    )
                }
            }
        }
    }
}

@Composable
fun YouTubeLivePlayer(videoId: String) {
    val context = LocalContext.current
    AndroidView(
        factory = {
            WebView(context).apply {
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
                loadUrl("https://www.youtube.com/embed/$videoId?autoplay=1&modestbranding=1&rel=0")
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f)
            .background(Color.Black)
    )
}