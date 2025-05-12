package com.example.pep_insta.presentation.screens.feed

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.compose.collectAsLazyPagingItems

// Mock data model for video posts
data class VideoPost(
    val id: String,
    val videoUri: String
)

// Mock PagingSource for video posts
class VideoPagingSource : PagingSource<Int, VideoPost>() {
    override fun getRefreshKey(state: PagingState<Int, VideoPost>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, VideoPost> {
        val page = params.key ?: 0
        val mockPosts = List(10) { index ->
            val videoUri = if (index % 2 == 0) "file:///android_asset/videos/video1.mp4" else "file:///android_asset/videos/video2.mp4"
            VideoPost(
                id = "post_${page * 10 + index}",
                videoUri = videoUri
            )
        }
        return LoadResult.Page(
            data = mockPosts,
            prevKey = if (page == 0) null else page - 1,
            nextKey = page + 1
        )
    }
}

// VideoPlayer composable using ExoPlayer
@Composable
fun VideoPlayer(videoUri: String) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(videoUri))
            prepare()
        }
    }

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
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f)
            .padding(8.dp)
    ) {
        AndroidView(
            factory = { ctx ->
                PlayerView(ctx).apply {
                    player = exoPlayer
                    useController = true
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}

// FeedScreen composable
@Composable
fun FeedScreen() {
    val pager = remember {
        Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { VideoPagingSource() }
        )
    }
    val pagingItems = pager.flow.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(pagingItems.itemCount) { index ->
            val post = pagingItems[index]
            if (post != null) {
                VideoPlayer(videoUri = post.videoUri)
            }
        }
    }
} 