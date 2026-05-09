# 🏆 Grama-Kalyana Sports
### Digital Village Sports Scoreboard App

Grama-Kalyana Sports is a modern Android application developed using **Kotlin + Jetpack Compose** to digitalize village-level sports tournaments such as **Cricket, Kabaddi, and Volleyball**.

This project is being developed as part of an internship at **MindMatrix** with the goal of transforming traditional manual scorekeeping into a real-time digital sports experience for local communities.

---

# 📱 Project Vision

Village sports tournaments are highly energetic community events, but scorekeeping is usually done manually with no proper digital records or live viewing system.

Grama-Kalyana Sports aims to solve this problem by providing:

- ✅ Real-time live score updates
- ✅ Public live viewing system
- ✅ Zone-based tournament filtering
- ✅ Digital player statistics
- ✅ Professional scorer panel
- ✅ Multi-sport support
- ✅ Firebase-powered live synchronization

The app is designed to bring a professional sports experience to grassroots tournaments.

---

# 🚀 Current Development Status

### ✅ Completed
- Android Studio project setup
- Jetpack Compose integration
- Navigation architecture
- Modular screen structure
- Theme setup
- UI component structure
- GitHub integration

### 🔄 In Progress
- Firebase integration
- Real-time live scoring
- Authentication system
- Sports logic implementation
- Responsive UI improvements
- Dark/Light mode optimization

### 📌 Planned Features
- Cricket scoring engine
- Kabaddi raid logic
- Volleyball set logic
- Player career statistics
- Match history
- Share scorecard
- Offline sync
- Tournament analytics

---

# 🛠️ Tech Stack

## Frontend
- Kotlin
- Jetpack Compose
- Material 3
- Compose Navigation

## Backend
- Firebase Authentication
- Firebase Realtime Database

## Architecture
- MVVM Architecture
- Repository Pattern
- Modular UI Structure

---

# 📂 Project Structure

```text
app/
├── manifests/
│   └── AndroidManifest.xml
│
├── kotlin+java/
│   └── com.gramakalyana.sports/
│
│       ├── navigation/
│       │   ├── AppNavGraph.kt
│       │   └── Screen.kt
│       │
│       ├── ui/
│       │
│       │   ├── components/
│       │   │   ├── AnimatedBottomBar.kt
│       │   │   ├── GlassmorphismCard.kt
│       │   │   ├── GradientBackground.kt
│       │   │   └── LiveBadge.kt
│       │   │
│       │   ├── screens/
│       │   │
│       │   │   ├── auth/
│       │   │   │   └── ScorerLoginScreen.kt
│       │   │   │
│       │   │   ├── home/
│       │   │   │   └── HomeScreen.kt
│       │   │   │
│       │   │   ├── live/
│       │   │   │   ├── LiveMatchesScreen.kt
│       │   │   │   └── MatchDetailsScreen.kt
│       │   │   │
│       │   │   ├── scoring/
│       │   │   │   ├── CricketScoringScreen.kt
│       │   │   │   ├── KabaddiScoringScreen.kt
│       │   │   │   └── VolleyballScoringScreen.kt
│       │   │   │
│       │   │   ├── setup/
│       │   │   │   └── TournamentSetupScreen.kt
│       │   │   │
│       │   │   ├── splash/
│       │   │   │   └── SplashScreen.kt
│       │   │   │
│       │   │   ├── stats/
│       │   │   │   └── PlayerStatsScreen.kt
│       │   │   │
│       │   │   └── zone/
│       │   │       └── ZoneSelectionScreen.kt
│       │   │
│       │   └── theme/
│       │       ├── Color.kt
│       │       ├── Theme.kt
│       │       └── Type.kt
│       │
│       └── MainActivity.kt
│
├── com.gramakalyana.sports (androidTest)
│
└── com.gramakalyana.sports (test)
```

---

# 📌 Planned Upcoming Structure

As development progresses, the following architecture folders will be added:

```text
data/
├── model/
├── repository/
└── firebase/

logic/
├── cricket/
├── kabaddi/
└── volleyball/

viewmodel/
```

These folders will handle:
- Firebase integration
- Real-time synchronization
- Match scoring logic
- Player statistics
- Business logic separation
- MVVM architecture

---

# 🏗️ Architecture Overview

The project follows a modular Jetpack Compose architecture:

```text
UI Layer
   ↓
ViewModel Layer
   ↓
Repository Layer
   ↓
Firebase Realtime Database
```

This separation helps maintain:
- Clean code
- Scalability
- Easier debugging
- Real-time state management
- Multi-sport logic handling

---

# 🎮 Supported Sports

## 🏏 Cricket
Planned features:
- Runs
- Wickets
- Overs
- Strike rotation
- Extras
- Innings handling

## 🤼 Kabaddi
Planned features:
- Raid points
- Bonus points
- Tackle points
- All-out logic
- Team switching

## 🏐 Volleyball
Planned features:
- Rally scoring
- Set system
- Win-by-2 rule
- Match tracking

---

# 🌐 Core Features

## 🔴 Real-Time Live Scores
Scorers can update scores instantly and public viewers can watch matches live.

## 🗺️ Zone-Based Filtering
Users can select their zone/village and view only relevant tournaments and matches.

## 🔐 Scorer Authentication
Only authorized scorers/admins can update scores.

## 📊 Player Statistics
Track player performance and match history.

## 🎨 Modern UI
- Glassmorphism inspired UI
- Responsive layouts
- Dark & Light Mode support
- Modern sports-themed design

---

# 📌 Development Goals

- Build a scalable real-time sports platform
- Improve accessibility of local tournaments
- Create digital player records
- Encourage youth participation in sports
- Bring technology to grassroots communities

---

# 🔥 Future Scope

- AI-generated match summaries
- Tournament leaderboards
- Match highlights
- Push notifications
- Team logos and media
- Analytics dashboard
- Web version for public viewing

---

# 👨‍💻 Developed By

**Chinmayi**

Android Developer | AI & Mobile App Enthusiast

---

# 🤝 Internship

This project is being developed as part of an internship at:

### MindMatrix

Focused on:
- Android Development
- Real-Time Systems
- Firebase Integration
- UI/UX Design
- AI-assisted development workflows

---

# 📷 Upcoming Screens

- Splash Screen
- Home Dashboard
- Zone Selection
- Live Match Viewer
- Scorer Panel
- Player Stats Dashboard

---

# 📌 Note

This project is currently under active development. Features, UI, and architecture are continuously being improved step-by-step.
