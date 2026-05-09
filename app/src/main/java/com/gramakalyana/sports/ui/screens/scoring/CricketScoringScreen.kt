package com.gramakalyana.sports.ui.screens.scoring

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gramakalyana.sports.ui.components.GlassmorphismCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CricketScoringScreen(navController: NavController, matchId: String?) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cricket Panel", fontWeight = FontWeight.Bold) },
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
            ScoreboardHeader()
            Spacer(modifier = Modifier.height(16.dp))
            CurrentPlayersSection()
            Spacer(modifier = Modifier.height(16.dp))
            ScoringControls()
        }
    }
}

@Composable
fun ScoreboardHeader() {
    Column {
        Text(text = "Riders FC", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
        Row(verticalAlignment = Alignment.Bottom) {
            Text(text = "143/5", style = MaterialTheme.typography.displayMedium, fontWeight = FontWeight.Bold)
            Text(text = " (18.1)", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(bottom = 6.dp, start = 8.dp))
        }
        Text(text = "CRR: 7.85", color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
fun CurrentPlayersSection() {
    GlassmorphismCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Batter 1", fontWeight = FontWeight.Bold)
                Text(text = "42 (30)")
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Batter 2 *", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                Text(text = "18 (14)")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Bowler", color = MaterialTheme.colorScheme.secondary)
                Text(text = "2-24 (3.1)")
            }

            Spacer(modifier = Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                Text("This over: ", fontWeight = FontWeight.Bold)
                Text(text = "1  4  W  0", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}

@Composable
fun ScoringControls() {
    val runButtons = listOf("0", "1", "2", "3", "4", "6")
    val extraButtons = listOf("Wide", "No Ball", "Leg Bye", "Bye")

    Column(modifier = Modifier.fillMaxSize()) {
        Text("Runs", fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 8.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.height(140.dp)
        ) {
            items(runButtons) { run ->
                Button(
                    onClick = { },
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(text = run, style = MaterialTheme.typography.titleLarge)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Extras", fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 8.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.height(120.dp)
        ) {
            items(extraButtons) { extra ->
                Button(
                    onClick = { },
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Text(text = extra, color = MaterialTheme.colorScheme.onSurface)
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
        ) {
            Text(text = "WICKET", fontWeight = FontWeight.Bold, color = Color.White)
        }
    }
}
