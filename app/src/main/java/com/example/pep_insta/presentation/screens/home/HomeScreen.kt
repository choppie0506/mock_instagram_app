package com.example.pep_insta.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import coil.compose.AsyncImage

@Composable
fun HomeScreen() {
    val videos = remember {
        // Dummy data for testing
        List(10) { index ->
            VideoItem(
                id = index.toString(),
                videoUrl = "https://example.com/video$index.mp4",
                thumbnailUrl = "https://picsum.photos/200/300?random=$index",
                username = "user$index",
                caption = "This is video $index",
                likes = index * 100,
                comments = index * 10
            )
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(1.dp)
    ) {
        items(videos) { video ->
            VideoCard(video = video)
        }
    }
}

@Composable
fun VideoCard(video: VideoItem) {
    var isPlaying by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(video.videoUrl))
            prepare()
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(600.dp)
    ) {
        // Video Player
        AndroidView(
            factory = { context ->
                PlayerView(context).apply {
                    player = exoPlayer
                    useController = false
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        // Video Info Overlay
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    AsyncImage(
                        model = "https://picsum.photos/50/50?random=${video.id}",
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color.Gray, shape = CircleShape)
                    )
                    Text(
                        text = video.username,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                }
                IconButton(onClick = { /* TODO: Show options */ }) {
                    Icon(
                        Icons.Default.MoreVert,
                        contentDescription = "More Options",
                        tint = Color.White
                    )
                }
            }

            // Bottom Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = video.caption,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White
                    )
                    Text(
                        text = "${video.likes} likes • ${video.comments} comments",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White
                    )
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    IconButton(onClick = { /* TODO: Like video */ }) {
                        Icon(
                            Icons.Default.Favorite,
                            contentDescription = "Like",
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = { /* TODO: Comment */ }) {
                        Icon(
                            Icons.Default.Comment,
                            contentDescription = "Comment",
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = { /* TODO: Share */ }) {
                        Icon(
                            Icons.Default.Share,
                            contentDescription = "Share",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}

data class VideoItem(
    val id: String,
    val videoUrl: String,
    val thumbnailUrl: String,
    val username: String,
    val caption: String,
    val likes: Int,
    val comments: Int
) 