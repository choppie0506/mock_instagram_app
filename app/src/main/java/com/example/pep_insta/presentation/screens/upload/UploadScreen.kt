package com.example.pep_insta.presentation.screens.upload

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import kotlinx.coroutines.delay

@Composable
fun UploadScreen() {
    var selectedVideoUri by remember { mutableStateOf<Uri?>(null) }
    var caption by remember { mutableStateOf("") }
    var showConfirmDialog by remember { mutableStateOf(false) }
    var isUploading by remember { mutableStateOf(false) }
    var uploadProgress by remember { mutableStateOf(0f) }
    var uploadComplete by remember { mutableStateOf(false) }
    val context = LocalContext.current

    // Simulate upload process
    LaunchedEffect(isUploading) {
        if (isUploading) {
            repeat(100) {
                delay(50)
                uploadProgress = (it + 1) / 100f
                if (uploadProgress >= 1f) {
                    isUploading = false
                    uploadComplete = true
                }
            }
        }
    }

    val videoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedVideoUri = uri
    }

    // Confirmation Dialog
    if (showConfirmDialog) {
        AlertDialog(
            onDismissRequest = { showConfirmDialog = false },
            title = { Text("Confirm Upload") },
            text = { Text("Are you sure you want to upload this video?") },
            confirmButton = {
                Button(
                    onClick = {
                        showConfirmDialog = false
                        isUploading = true
                    }
                ) {
                    Text("Upload")
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = { showConfirmDialog = false }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
    
    // Upload Complete Dialog
    if (uploadComplete) {
        AlertDialog(
            onDismissRequest = {
                uploadComplete = false
                selectedVideoUri = null
                caption = ""
            },
            title = { Text("Upload Successful") },
            text = { Text("Your video has been uploaded successfully!") },
            confirmButton = {
                Button(
                    onClick = {
                        uploadComplete = false
                        selectedVideoUri = null
                        caption = ""
                    }
                ) {
                    Text("OK")
                }
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (selectedVideoUri == null) {
            // Video Selection UI
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.VideoLibrary,
                        contentDescription = "Select Video",
                        modifier = Modifier.size(64.dp)
                    )
                    Text("Select a video to upload")
                    Button(
                        onClick = { videoPicker.launch("video/*") }
                    ) {
                        Text("Choose Video")
                    }
                }
            }
        } else {
            selectedVideoUri?.let { uri ->
                // Video Preview and Upload UI
                val exoPlayer = remember {
                    ExoPlayer.Builder(context).build().apply {
                        setMediaItem(MediaItem.fromUri(uri))
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
                        .weight(1f)
                ) {
                    AndroidView(
                        factory = { context ->
                            PlayerView(context).apply {
                                player = exoPlayer
                                useController = true
                            }
                        },
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = caption,
                    onValueChange = { caption = it },
                    label = { Text("Add a caption") },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 3
                )

                Spacer(modifier = Modifier.height(16.dp))
                
                // Show upload progress when uploading
                if (isUploading) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Uploading video... ${(uploadProgress * 100).toInt()}%")
                        Spacer(modifier = Modifier.height(8.dp))
                        LinearProgressIndicator(
                            progress = uploadProgress,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                } else {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        OutlinedButton(
                            onClick = { selectedVideoUri = null }
                        ) {
                            Text("Cancel")
                        }
                        Button(
                            onClick = { showConfirmDialog = true }
                        ) {
                            Text("Upload")
                        }
                    }
                }
            }
        }
    }
} 