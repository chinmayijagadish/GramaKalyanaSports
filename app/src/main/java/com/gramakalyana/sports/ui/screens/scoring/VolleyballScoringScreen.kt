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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gramakalyana.sports.ui.components.GlassmorphismCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VolleyballScoringScreen(navController: NavController, matchId: String?) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Volleyball Panel", fontWeight = FontWeight.Bold) },
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
            VolleyballScoreboardHeader()
            Spacer(modifier = Modifier.height(24.dp))
            VolleyballScoringControls()
        }
    }
}

@Composable
fun VolleyballScoreboardHeader() {
    GlassmorphismCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(24.dp)) {
            Text(
                "Set 3",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Spikers", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
                    Text("Sets: 1", style = MaterialTheme.typography.bodyMedium)
                    Text("18", style = MaterialTheme.typography.displayLarge, fontWeight = FontWeight.Bold)
                }
                Text("vs", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.outline)
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Blockers", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.secondary)
                    Text("Sets: 1", style = MaterialTheme.typography.bodyMedium)
                    Text("14", style = MaterialTheme.typography.displayLarge, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun VolleyballScoringControls() {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {},
                modifier = Modifier
                    .weight(1f)
                    .height(80.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("+1 Spikers", style = MaterialTheme.typography.titleMedium)
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Button(
                onClick = {},
                modifier = Modifier
                    .weight(1f)
                    .height(80.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Text("+1 Blockers", style = MaterialTheme.typography.titleMedium)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {},
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Text("Timeout Spikers", color = MaterialTheme.colorScheme.onSurface)
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Button(
                onClick = {},
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Text("Timeout Blockers", color = MaterialTheme.colorScheme.onSurface)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary)
        ) {
            Text("End Set", fontWeight = FontWeight.Bold)
        }
    }
}
