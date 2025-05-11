package com.example.pep_insta.presentation.screens.notifications

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun NotificationsScreen() {
    val notifications = remember {
        // Dummy data for testing
        List(20) { index ->
            NotificationItem(
                id = index.toString(),
                type = when (index % 3) {
                    0 -> NotificationType.LIKE
                    1 -> NotificationType.COMMENT
                    else -> NotificationType.FOLLOW
                },
                username = "user$index",
                content = when (index % 3) {
                    0 -> "liked your video"
                    1 -> "commented: Great video!"
                    else -> "started following you"
                },
                timestamp = System.currentTimeMillis() - (index * 3600000L),
                userAvatarUrl = "https://picsum.photos/50/50?random=$index"
            )
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(notifications) { notification ->
            NotificationCard(notification = notification)
        }
    }
}

@Composable
fun NotificationCard(notification: NotificationItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // User Avatar
        AsyncImage(
            model = notification.userAvatarUrl,
            contentDescription = "User Avatar",
            modifier = Modifier
                .size(48.dp)
                .padding(end = 12.dp)
        )

        // Notification Content
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = notification.username,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = notification.content,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = formatTimestamp(notification.timestamp),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // Action Icon
        Icon(
            imageVector = when (notification.type) {
                NotificationType.LIKE -> Icons.Default.Favorite
                NotificationType.COMMENT -> Icons.Default.Comment
                NotificationType.FOLLOW -> Icons.Default.PersonAdd
            },
            contentDescription = "Notification Type",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

enum class NotificationType {
    LIKE, COMMENT, FOLLOW
}

data class NotificationItem(
    val id: String,
    val type: NotificationType,
    val username: String,
    val content: String,
    val timestamp: Long,
    val userAvatarUrl: String
)

private fun formatTimestamp(timestamp: Long): String {
    val now = System.currentTimeMillis()
    val diff = now - timestamp

    return when {
        diff < 60000 -> "Just now"
        diff < 3600000 -> "${diff / 60000}m ago"
        diff < 86400000 -> "${diff / 3600000}h ago"
        else -> SimpleDateFormat("MMM d", Locale.getDefault()).format(Date(timestamp))
    }
} 