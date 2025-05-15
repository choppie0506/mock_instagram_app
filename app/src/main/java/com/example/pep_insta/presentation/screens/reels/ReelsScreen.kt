package com.example.pep_insta.presentation.screens.reels

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReelsScreen() {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    
    // Sample video URIs - replace with your actual video sources
    val videoUris = remember {
        listOf(
            "file:///android_asset/videos/video1.mp4",
            "file:///android_asset/videos/video2.mp4"
        )
    }
    
    val pagerState = rememberPagerState(pageCount = { videoUris.size })
    
    // Track the currently playing video
    var currentPlayer by remember { mutableStateOf<ExoPlayer?>(null) }
    
    // Handle page changes
    LaunchedEffect(pagerState.currentPage) {
        // Stop previous video
        currentPlayer?.pause()
        
        // Start new video
        currentPlayer?.play()
    }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        VerticalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            val videoUri = videoUris[page]
            
            // Create ExoPlayer for this page
            val exoPlayer = remember {
                ExoPlayer.Builder(context).build().apply {
                    setMediaItem(MediaItem.fromUri(videoUri))
                    repeatMode = Player.REPEAT_MODE_ONE
                    playWhenReady = true
                    prepare()
                }
            }
            
            // Update current player
            LaunchedEffect(exoPlayer) {
                currentPlayer = exoPlayer
            }
            
            // Handle lifecycle
            DisposableEffect(lifecycleOwner) {
                val observer = LifecycleEventObserver { _, event ->
                    when (event) {
                        Lifecycle.Event.ON_START -> exoPlayer.play()
                        Lifecycle.Event.ON_STOP -> exoPlayer.pause()
                        else -> {}
                    }
                }
                
                lifecycleOwner.lifecycle.addObserver(observer)
                
                onDispose {
                    lifecycleOwner.lifecycle.removeObserver(observer)
                    exoPlayer.release()
                    if (currentPlayer == exoPlayer) {
                        currentPlayer = null
                    }
                }
            }
            
            Box(modifier = Modifier.fillMaxSize()) {
                // Video player view
                AndroidView(
                    factory = { ctx ->
                        PlayerView(ctx).apply {
                            player = exoPlayer
                            useController = false // Hide default controls
                            resizeMode = 3 // Use numeric constant (3 = RESIZE_MODE_ZOOM in Media3)
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                )
                
                // Overlay controls
                ReelsControls(
                    likes = 1234,
                    comments = 567,
                    onLikeClick = { /* Handle like */ },
                    onCommentClick = { /* Handle comment */ },
                    onShareClick = { /* Handle share */ }
                )
            }
        }
    }
} 