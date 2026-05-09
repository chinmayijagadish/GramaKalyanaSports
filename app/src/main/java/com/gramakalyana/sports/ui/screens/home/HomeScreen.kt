package com.gramakalyana.sports.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gramakalyana.sports.navigation.Screen
import com.gramakalyana.sports.ui.components.AnimatedBottomBar
import com.gramakalyana.sports.ui.components.GlassmorphismCard
import com.gramakalyana.sports.ui.components.LiveBadge

@Composable
fun HomeScreen(navController: NavController) {
    val scrollState = rememberScrollState()

    Scaffold(
        bottomBar = {
            AnimatedBottomBar(
                currentRoute = Screen.Home.route,
                onNavigate = { route ->
                    navController.navigate(route) {
                        popUpTo(Screen.Home.route) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .background(MaterialTheme.colorScheme.background)
        ) {
            HomeHeader()
            ActionButtons(navController)
            LiveMatchCarousel()
            SportsCategories()
            UpcomingMatches()
        }
    }
}

@Composable
fun HomeHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
            .background(MaterialTheme.colorScheme.primary)
            .padding(24.dp)
    ) {
        Column {
            Text(
                text = "Grama-Kalyana Sports",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Digital Village Sports Scoreboard",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White.copy(alpha = 0.8f)
            )
        }
    }
}

@Composable
fun ActionButtons(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        HomeActionButton("Zone Select") { navController.navigate(Screen.ZoneSelection.route) }
        HomeActionButton("Scorer Login") { navController.navigate(Screen.ScorerLogin.route) }
    }
}

@Composable
fun HomeActionButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
    ) {
        Text(text = text, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun LiveMatchCarousel() {
    Column(modifier = Modifier.padding(vertical = 16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Live Matches",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            LiveBadge()
        }
        Spacer(modifier = Modifier.height(12.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            items(3) { index ->
                LiveMatchCardExample()
            }
        }
    }
}

@Composable
fun LiveMatchCardExample() {
    GlassmorphismCard(
        modifier = Modifier.width(280.dp),
        cornerRadius = 16.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Cricket - Finale", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Team A", fontWeight = FontWeight.Bold)
                Text(text = "vs", color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f))
                Text(text = "Team B", fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "120/4 (15.2)", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.primary)
                Text(text = "118/8 (20.0)", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}

@Composable
fun SportsCategories() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Categories",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CategoryCard("Cricket")
            CategoryCard("Kabaddi")
            CategoryCard("Volleyball")
        }
    }
}

@Composable
fun CategoryCard(title: String) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.size(100.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Text(text = title, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
fun UpcomingMatches() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Upcoming Matches",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(12.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Kabaddi - Semi Final")
                Text(text = "Zone North vs Zone South", fontWeight = FontWeight.Bold, modifier = Modifier.padding(vertical = 8.dp))
                Text(text = "Starts at 4:30 PM", color = MaterialTheme.colorScheme.secondary)
            }
        }
        Spacer(modifier = Modifier.height(80.dp)) // padding for bottom bar
    }
}
