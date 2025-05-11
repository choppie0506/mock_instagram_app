package com.example.pep_insta.presentation.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ProfileScreen() {
    val user = remember {
        UserProfile(
            username = "johndoe",
            name = "John Doe",
            bio = "Digital creator | Travel enthusiast | Food lover",
            followers = 1234,
            following = 567,
            posts = 42,
            profilePictureUrl = "https://picsum.photos/200/200"
        )
    }

    val videos = remember {
        // Dummy data for testing
        List(12) { index ->
            VideoItem(
                id = index.toString(),
                thumbnailUrl = "https://picsum.photos/200/200?random=$index",
                likes = index * 100
            )
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Profile Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile Picture
            AsyncImage(
                model = user.profilePictureUrl,
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(96.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant, CircleShape)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Username and Name
            Text(
                text = user.username,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = user.name,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Bio
            Text(
                text = user.bio,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 32.dp),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Stats
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatItem(count = user.posts, label = "Posts")
                StatItem(count = user.followers, label = "Followers")
                StatItem(count = user.following, label = "Following")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Edit Profile Button
            OutlinedButton(
                onClick = { /* TODO: Navigate to edit profile */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Edit Profile")
            }
        }

        Divider()

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
fun StatItem(count: Int, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = count.toString(),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun VideoThumbnail(video: VideoItem) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(1.dp)
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

data class UserProfile(
    val username: String,
    val name: String,
    val bio: String,
    val followers: Int,
    val following: Int,
    val posts: Int,
    val profilePictureUrl: String
)

data class VideoItem(
    val id: String,
    val thumbnailUrl: String,
    val likes: Int
) 