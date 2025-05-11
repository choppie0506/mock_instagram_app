# Navigation Architecture

## Navigation Graph Structure

```kotlin
NavHost(navController, startDestination = "auth") {
    // Auth Flow
    composable("auth") { AuthScreen() }
    composable("otp") { OtpScreen() }
    
    // Main Flow
    composable("main") {
        MainScreen(
            onNavigateToProfile = { navController.navigate("profile") }
        )
    }
    
    // Home/Reels Flow
    composable("home") { HomeScreen() }
    composable("video/{videoId}") { VideoDetailScreen() }
    
    // Discover Flow
    composable("discover") { DiscoverScreen() }
    
    // Upload Flow
    composable("upload") { UploadScreen() }
    composable("upload/preview") { UploadPreviewScreen() }
    
    // Notifications Flow
    composable("notifications") { NotificationsScreen() }
    
    // Profile Flow
    composable("profile") { ProfileScreen() }
    composable("profile/edit") { EditProfileScreen() }
    composable("profile/settings") { SettingsScreen() }
}
```

## Deep Links
- Video sharing: `pepinsta://video/{videoId}`
- Profile sharing: `pepinsta://profile/{userId}`
- Hashtag: `pepinsta://hashtag/{tagName}`

## Navigation Patterns
1. **Bottom Navigation**
   - Home (Reels)
   - Discover
   - Upload (Center)
   - Notifications
   - Profile

2. **Nested Navigation**
   - Profile → Edit Profile → Settings
   - Video → Comments → User Profile
   - Upload → Preview → Success

3. **Modal Navigation**
   - Comments sheet
   - Share sheet
   - Filter options

## Transition Animations
- Slide transitions for main navigation
- Fade transitions for modal sheets
- Shared element transitions for video thumbnails
- Custom transitions for reels swipe

## State Preservation
- Save and restore scroll position
- Maintain video playback state
- Cache navigation state
- Handle configuration changes 