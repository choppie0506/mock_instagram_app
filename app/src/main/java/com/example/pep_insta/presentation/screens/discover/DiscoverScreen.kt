package com.example.pep_insta.presentation.screens.discover

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.ui.Alignment

@Composable
fun DiscoverScreen() {
    val videos = remember {
        // Dummy data for testing
        List(20) { index ->
            DiscoverVideoItem(
                id = index.toString(),
                thumbnailUrl = "https://picsum.photos/200/300?random=$index",
                username = "user$index",
                likes = index * 100
            )
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Search Bar
        OutlinedTextField(
            value = "",
            onValueChange = { /* TODO: Implement search */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = { Text("Search videos") }
        )

        // Video Grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(1.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(videos) { video ->
                VideoThumbnail(video = video)
            }
        }
    }
}

@Composable
fun VideoThumbnail(video: DiscoverVideoItem) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(1.dp)
            .clickable { /* TODO: Navigate to video */ }
    ) {
        AsyncImage(
            model = video.thumbnailUrl,
            contentDescription = "Video thumbnail",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Like count overlay
        Surface(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(4.dp),
            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Likes",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = video.likes.toString(),
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}

data class DiscoverVideoItem(
    val id: String,
    val thumbnailUrl: String,
    val username: String,
    val likes: Int
) 