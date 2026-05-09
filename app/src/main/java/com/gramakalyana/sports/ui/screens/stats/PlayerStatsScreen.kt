package com.gramakalyana.sports.ui.screens.stats

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gramakalyana.sports.ui.components.GlassmorphismCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerStatsScreen(navController: NavController, playerId: String?) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Player Statistics", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            PlayerProfileHeader()
            Spacer(modifier = Modifier.height(24.dp))
            StatCardsSection()
            Spacer(modifier = Modifier.height(24.dp))
            RecentPerformanceSection()
        }
    }
}

@Composable
fun PlayerProfileHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Star, contentDescription = null, tint = Color.White, modifier = Modifier.size(40.dp))
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text("Naveen Kumar", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Text("Kabaddi - Raider", color = MaterialTheme.colorScheme.secondary)
            Text("Rank: #2 in Tournament", style = MaterialTheme.typography.labelMedium)
        }
    }
}

@Composable
fun StatCardsSection() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        StatCard(title = "Matches", value = "12", modifier = Modifier.weight(1f))
        StatCard(title = "Total Points", value = "104", modifier = Modifier.weight(1f))
        StatCard(title = "MVP", value = "4", modifier = Modifier.weight(1f))
    }
}

@Composable
fun StatCard(title: String, value: String, modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = value, style = MaterialTheme.typography.displaySmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            Text(text = title, style = MaterialTheme.typography.labelSmall)
        }
    }
}

@Composable
fun RecentPerformanceSection() {
    Text("Recent Matches", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
    Spacer(modifier = Modifier.height(12.dp))

    GlassmorphismCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            PerformanceRow(match = "vs Tigers", score = "14 Points", result = "Won")
            Spacer(modifier = Modifier.height(12.dp))
            PerformanceRow(match = "vs Dragons", score = "9 Points", result = "Lost")
            Spacer(modifier = Modifier.height(12.dp))
            PerformanceRow(match = "vs Warriors", score = "18 Points", result = "Won")
        }
    }
}

@Composable
fun PerformanceRow(match: String, score: String, result: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(match, fontWeight = FontWeight.Medium)
        Text(score, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .background(if (result == "Won") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error)
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Text(result, color = Color.White, style = MaterialTheme.typography.labelSmall)
        }
    }
}
