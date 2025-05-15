# Pep_insta App Structure

## Overview
Pep_insta is an Instagram-like social media application that allows users to share photos and videos, interact with other users' content, and build a social network. The app follows modern Android development practices using Jetpack Compose, MVVM architecture, and clean code principles.

## Core Features

### 1. Authentication
- User registration with email and password
- Social media login options (Google, Facebook)
- Password recovery
- Profile creation and customization

### 2. Feed and Content
- Home feed with chronological and algorithmic sorting options
- Photo and video content support with optimized media players
- Infinite scrolling with efficient recycling
- Content caching for offline viewing

### 3. Content Creation
- Camera integration for photo capture
- Video recording with duration limits
- Media gallery access
- Filters and basic editing tools
- Post creation with captions and location tagging

### 4. Social Interactions
- Like, comment, and share functionality
- User following/followers system
- Direct messaging between users
- Content bookmarking
- Content reporting

### 5. Discovery
- Search functionality for users and content
- Explore page with trending and suggested content
- Hashtag system for content categorization
- Location-based content discovery

### 6. User Profiles
- Customizable user profiles with bio and profile picture
- Content grid view of user posts
- Activity history
- Saved content collection

### 7. Notifications
- Push notifications for likes, comments, follows, and messages
- In-app notification center
- Customizable notification preferences

## Technical Architecture

### Application Layers
1. **Presentation Layer**
   - Activities and Fragments
   - Jetpack Compose UI
   - ViewModels
   - UI State management

2. **Domain Layer**
   - Use Cases
   - Domain Models
   - Business Logic

3. **Data Layer**
   - Repositories
   - Data Sources (Remote and Local)
   - Data Models
   - API Service

### Key Components

#### 1. UI Components
- MainScreen (Bottom Navigation)
- HomeScreen (Feed)
- DiscoverScreen (Explore)
- UploadScreen (Content Creation)
- NotificationsScreen
- ProfileScreen
- SplashScreen
- OptimizedVideoPlayer
- PostCard

#### 2. ViewModels
- AuthViewModel
- FeedViewModel
- ProfileViewModel
- UploadViewModel
- NotificationsViewModel
- SearchViewModel
- MessageViewModel

#### 3. Repositories
- UserRepository
- PostRepository
- MediaRepository
- NotificationRepository
- SearchRepository
- MessageRepository

#### 4. Data Sources
- Remote: Retrofit API Client
- Local: Room Database
- Media Cache: Custom implementation
- Preferences: DataStore

## Functional Requirements

### Authentication
- FR1: Users must be able to create accounts with email and password
- FR2: Users must be able to log in with existing credentials
- FR3: Users must be able to reset passwords via email
- FR4: Users must be able to log out from multiple devices

### Content
- FR5: Users must be able to view a feed of content from followed users
- FR6: Users must be able to create posts with images or videos
- FR7: Users must be able to add captions and location to posts
- FR8: Users must be able to edit or delete their own posts
- FR9: Feed must support infinite scrolling

### Social
- FR10: Users must be able to follow/unfollow other users
- FR11: Users must be able to like posts
- FR12: Users must be able to comment on posts
- FR13: Users must be able to view who liked or commented on posts
- FR14: Users must be able to send direct messages to other users

### Profile
- FR15: Users must be able to customize their profile information
- FR16: Users must be able to view their own and others' profiles
- FR17: Users must be able to see followers/following lists
- FR18: Users must be able to set privacy settings (public/private)

### Discovery
- FR19: Users must be able to search for other users and content
- FR20: Users must be able to discover content through explore page
- FR21: Users must be able to search content by hashtags

### Notifications
- FR22: Users must receive notifications for social interactions
- FR23: Users must be able to customize notification settings

## Non-Functional Requirements

### Performance
- NFR1: App must launch and display content within 3 seconds on standard devices
- NFR2: Video playback must start within 1.5 seconds of being visible
- NFR3: Feed scrolling must maintain 60fps on mid-range devices
- NFR4: App must efficiently cache media to minimize data usage
- NFR5: Battery consumption must be optimized for extended usage

### Reliability
- NFR6: App must handle network interruptions gracefully
- NFR7: Content uploads must be resumable after network failures
- NFR8: App must not crash due to unexpected user inputs
- NFR9: App must maintain state during configuration changes

### Security
- NFR10: User credentials must be securely stored
- NFR11: API communications must use HTTPS
- NFR12: Media access permissions must be requested explicitly
- NFR13: Private user data must be encrypted in transit and at rest

### Usability
- NFR14: UI must follow Material Design 3 guidelines
- NFR15: App must be accessible to users with disabilities
- NFR16: UI must be responsive across different screen sizes
- NFR17: Critical paths must require minimal user actions

### Scalability
- NFR18: Architecture must support addition of new features
- NFR19: App must handle increasing content volume efficiently
- NFR20: Backend integration must support horizontal scaling

### Maintainability
- NFR21: Code must follow clean architecture principles
- NFR22: Components must be loosely coupled
- NFR23: Tests must cover critical functionality
- NFR24: Documentation must be comprehensive

## Tech Stack

### Frontend
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM with Clean Architecture
- **State Management**: StateFlow, SharedFlow
- **Navigation**: Jetpack Navigation Compose
- **Dependency Injection**: Hilt
- **Image Loading**: Coil
- **Video Playback**: ExoPlayer (Media3)

### Local Storage
- **Database**: Room
- **Preferences**: DataStore
- **Media Cache**: Custom implementation with Coil

### Networking
- **HTTP Client**: Retrofit with OkHttp
- **Serialization**: Kotlinx Serialization
- **Authentication**: JWT

### Testing
- **Unit Testing**: JUnit, Mockito
- **UI Testing**: Compose UI Testing
- **E2E Testing**: Espresso

## Future Enhancements
- Live streaming capabilities
- AR filters and effects
- E-commerce integration
- Content monetization
- Advanced analytics for creators
- Cross-platform synchronization 