# 🏆 Grama-Kalyana Sports

### Digital Village Sports Tournament Management & Live Scoring Platform

Grama-Kalyana Sports is a modern Android application built using **Kotlin + Jetpack Compose** that digitalizes village-level sports tournaments such as **Cricket, Kabaddi, and Volleyball**.

The application transforms traditional manual scorekeeping into a professional real-time sports management platform with live score updates, tournament management, player tracking, and public live viewing.

---

# 📱 Project Overview

Village sports tournaments are highly energetic community events, but scorekeeping and tournament management are often handled manually using paper records.

Grama-Kalyana Sports solves this problem by providing:

- ✅ Real-time live scoring
- ✅ Public live match viewing
- ✅ Tournament & team management
- ✅ Player statistics tracking
- ✅ Zone-based tournament filtering
- ✅ Professional scorer dashboard
- ✅ Firebase real-time synchronization
- ✅ Multi-sport support
- ✅ Professional sports-style UI

The goal of the project is to bring a professional digital sports experience to grassroots village communities.

---

# 🚀 Features

## 🔐 Authentication System
- Firebase Authentication
- Secure scorer/admin login
- Role-based access

---

## 🏆 Tournament Management
- Create tournaments
- Edit tournament details
- Match scheduling
- Zone-based organization
- Sport-wise tournament management

---

## 👥 Team & Player Management
- Team creation & editing
- Player registration
- Sport-specific player roles
- Team-wise player restrictions
- Player participation tracking

---

# 🎮 Supported Sports

## 🏏 Cricket Live Scoring
Implemented Features:
- Runs & wickets tracking
- Overs management
- Strike rotation
- Extras (wide, no-ball)
- CRR & RRR calculation
- Innings transition
- Match winner detection
- Real-time live updates

---

## 🤼 Kabaddi Live Scoring
Implemented Features:
- Raid points
- Bonus points
- Tackle points
- Super tackle logic
- Super raid logic
- All-out handling
- Raid switching
- Winner calculation

---

## 🏐 Volleyball Live Scoring
Implemented Features:
- Rally scoring
- Set system
- Match point handling
- Serving team switching
- Win-by-2 rule
- Match winner logic

---

# 🌐 Core Functionalities

## 🔴 Real-Time Public Live Viewer
Viewers can watch scores update live during matches through Firebase Realtime Database synchronization.

Features include:
- Live scorecards
- Match status indicators
- Current batting/raiding/serving team display
- Match result cards
- Real-time updates

---

## 🗺️ Zone-Based Filtering
Users can browse tournaments and matches based on:
- Village
- Zone
- Local tournament grouping

---

## 🏅 Sports-wise Match Filtering
Separate filtering for:
- Cricket matches
- Kabaddi matches
- Volleyball matches

---

# 🎨 UI & Design

The application uses a modern sports-inspired interface with:
- Glassmorphism UI components
- Dark sports theme
- Responsive layouts
- Animated components
- Compact sports cards
- Professional live match dashboards

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
- Reactive State Management
- Modular UI Structure

---

# 🏗️ Architecture Overview

The application follows MVVM architecture with Firebase real-time synchronization.

```text
UI Layer
   ↓
ViewModel Layer
   ↓
Firebase Realtime Database
```

### Benefits
- Clean architecture
- Real-time UI updates
- Better scalability
- Easier debugging
- Multi-sport logic separation

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
│       ├── data/
│       │   ├── firebase/
│       │   │   └── FirebaseManager.kt
│       │   │
│       │   └── model/
│       │       ├── Match.kt
│       │       ├── Team.kt
│       │       ├── Player.kt
│       │       ├── Tournament.kt
│       │       ├── CricketLiveData.kt
│       │       ├── KabaddiLiveData.kt
│       │       └── VolleyballLiveData.kt
│       │
│       ├── navigation/
│       │   ├── AppNavGraph.kt
│       │   └── Screen.kt
│       │
│       ├── ui/
│       │   ├── components/
│       │   ├── screens/
│       │   │   ├── auth/
│       │   │   ├── home/
│       │   │   ├── live/
│       │   │   ├── scoring/
│       │   │   ├── setup/
│       │   │   ├── splash/
│       │   │   ├── stats/
│       │   │   ├── tournament/
│       │   │   └── zone/
│       │
│       ├── utils/
│       │   ├── SelectedZone.kt
│       ├── viewmodel/
│       │   ├── MatchViewModel.kt
│       │   ├── TeamViewModel.kt
│       │   ├── PlayerViewModel.kt
│       │   ├── PlayerStatsViewModel.kt
│       │   ├── TournamentViewModel.kt
│       │   ├── CricketLiveViewModel.kt
│       │   ├── KabaddiLiveViewModel.kt
│       │   ├── ZoneViewModel.kt
│       │   └── VolleyballLiveViewModel.kt
│       │
│       └── MainActivity.kt
```

---

# ✅ Current Functional Modules

- Tournament Management
- Team Management
- Player Management
- Match Scheduling
- Cricket Live Scoring
- Kabaddi Live Scoring
- Volleyball Live Scoring
- Public Match Viewer
- Match Result System
- Zone Filtering
- Sports Filtering
- Firebase Synchronization
- Export Scorecard

---

# 📈 Current Project Status

## Completed
- Authentication System
- Tournament CRUD
- Team CRUD
- Player CRUD
- Real-time Firebase Integration
- Cricket Scoring System
- Kabaddi Scoring System
- Volleyball Scoring System
- Match Result System
- Public Live Viewer
- Match Status Automation
- Responsive Live Scorecards

---

# 🔐 Demo Login Credentials

Since the project is currently under development and not publicly deployed, scorer access is temporarily restricted to a demo account.

## Scorer Login

```text
Email: abc@gmail.com
Password: 123456
```

> Note: This login is provided only for testing/demo purposes.

---

# 🔥 Future Scope

Planned future enhancements:
- AI-generated match summaries
- Tournament rankings
- Team logos & branding
- Match analytics dashboard
- Web viewer platform
- Advanced player analytics
- Live notifications

---

# 📌 Development Goals

- Digitalize village sports tournaments
- Preserve player records digitally
- Improve accessibility of local tournaments
- Encourage youth participation in sports
- Bring technology to grassroots communities

---

# 👨‍💻 Developed By

**Chinmayi Jagadish**  
Android Developer | AI & Mobile App Enthusiast

GitHub Repository:  
https://github.com/chinmayijagadish/GramaKalyanaSports

---

# 🤝 Internship Project

Developed as part of internship work at:

**MindMatrix**

### Focus Areas
- Android Development
- Firebase Integration
- Real-time Systems
- UI/UX Design
- AI-assisted development workflows

---

# 📷 Application Modules

- Splash Screen
- Authentication
- Home Dashboard
- Zone Selection
- Tournament Dashboard
- Team Management
- Player Management
- Live Cricket Scoring
- Live Kabaddi Scoring
- Live Volleyball Scoring
- Public Live Viewer
- Match Result Screens

---

# ⚡ Installation & Setup

## 1️⃣ Clone the Repository

```bash
git clone https://github.com/chinmayijagadish/GramaKalyanaSports.git
```

---

## 2️⃣ Open in Android Studio

- Open Android Studio
- Select **Open Project**
- Choose the cloned folder

---

## 3️⃣ Configure Firebase

- Create Firebase Project
- Enable:
    - Firebase Authentication
    - Firebase Realtime Database
- Download `google-services.json`
- Place it inside:

```text
app/google-services.json
```

---

## 4️⃣ Sync Gradle

Click:

```text
Sync Project with Gradle Files
```

---

## 5️⃣ Run the App

- Connect Android device or emulator
- Click ▶ Run

---

# 📌 Note
This project is actively being enhanced with additional analytics, AI-powered insights, and professional tournament management features for large-scale village sports events.

## Author
Chinmayi