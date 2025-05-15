package com.example.pep_insta.presentation.screens.reels

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ReelsControls(
    likes: Int = 0,
    comments: Int = 0,
    onLikeClick: () -> Unit = {},
    onCommentClick: () -> Unit = {},
    onShareClick: () -> Unit = {}
) {
    var isLiked by remember { mutableStateOf(false) }
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Left side - Video info
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "@username",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Video description goes here #reels #viral",
                color = Color.White,
                fontSize = 14.sp
            )
        }
        
        // Right side - Action buttons
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            IconButton(
                onClick = {
                    isLiked = !isLiked
                    onLikeClick()
                }
            ) {
                Icon(
                    imageVector = if (isLiked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Like",
                    tint = if (isLiked) Color.Red else Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }
            Text(
                text = likes.toString(),
                color = Color.White,
                fontSize = 12.sp
            )
            
            IconButton(onClick = onCommentClick) {
                Icon(
                    imageVector = Icons.Default.Comment,
                    contentDescription = "Comment",
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }
            Text(
                text = comments.toString(),
                color = Color.White,
                fontSize = 12.sp
            )
            
            IconButton(onClick = onShareClick) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Share",
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
} 