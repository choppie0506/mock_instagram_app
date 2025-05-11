# Instagram Reels Style App - UI Architecture

## 1. Login Screen
- **Layout**: Full-screen with centered content
- **Components**:
  - Phone number input field
  - OTP input field (appears after phone validation)
  - Login button
  - Loading indicator
- **Interactions**:
  - Phone number validation
  - OTP verification
  - Error handling states

## 2. Home Feed (Reels)
- **Layout**: Full-screen vertical scroll
- **Components**:
  - VideoPlayer (ExoPlayer)
  - Like/Comment/Share buttons
  - User info header
  - Caption section
  - Comments preview
- **Interactions**:
  - Vertical swipe navigation
  - Double tap to like
  - Tap to pause/play
  - Comment section expansion

## 3. Discover Page
- **Layout**: Grid layout with video thumbnails
- **Components**:
  - LazyVerticalGrid for thumbnails
  - Search bar
  - Category filters
  - Video preview on tap
- **Interactions**:
  - Grid item tap to full screen
  - Pull to refresh
  - Infinite scroll

## 4. Upload Video
- **Layout**: Multi-step wizard
- **Components**:
  - Gallery picker
  - Video preview
  - Caption input
  - Upload progress
  - Success confirmation
- **Interactions**:
  - Gallery selection
  - Video trimming
  - Caption validation
  - Upload progress tracking

## 5. Notifications
- **Layout**: List of notification cards
- **Components**:
  - LazyColumn for notifications
  - Notification types (follow, like, comment)
  - User avatars
  - Timestamp
- **Interactions**:
  - Pull to refresh
  - Tap to navigate to content
  - Mark as read

## 6. User Profile
- **Layout**: Scrollable profile with grid
- **Components**:
  - Profile header
  - Stats (followers/following)
  - Video grid
  - Edit profile button
- **Interactions**:
  - Grid item tap to full screen
  - Edit profile navigation
  - Follow/unfollow toggle

## Common Components
- Bottom Navigation Bar
- Top App Bar
- Loading Indicators
- Error States
- Empty States
- Pull to Refresh
- Infinite Scroll

## Navigation
- Bottom Navigation for main sections
- Nested navigation for sub-screens
- Deep linking support for shared content
- Back navigation handling

## State Management
- MVI pattern implementation
- State holders for each screen
- Shared state for auth/user data
- Video playback state management 