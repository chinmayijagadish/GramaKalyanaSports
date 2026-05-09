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
fun KabaddiScoringScreen(navController: NavController, matchId: String?) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Kabaddi Panel", fontWeight = FontWeight.Bold) },
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
            KabaddiScoreboardHeader()
            Spacer(modifier = Modifier.height(24.dp))
            KabaddiScoringControls()
        }
    }
}

@Composable
fun KabaddiScoreboardHeader() {
    GlassmorphismCard(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Bulls", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
                Text("24", style = MaterialTheme.typography.displayMedium, fontWeight = FontWeight.Bold)
            }
            Text("Half Time", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.outline)
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Panthers", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.secondary)
                Text("19", style = MaterialTheme.typography.displayMedium, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun KabaddiScoringControls() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text("Team A Points", fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
            Button(onClick = {}, modifier = Modifier.weight(1f), shape = RoundedCornerShape(12.dp)) { Text("Touch Point") }
            Button(onClick = {}, modifier = Modifier.weight(1f), shape = RoundedCornerShape(12.dp)) { Text("Bonus Point") }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
            Button(onClick = {}, modifier = Modifier.weight(1f), shape = RoundedCornerShape(12.dp), colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)) { Text("Tackle Point") }
            Button(onClick = {}, modifier = Modifier.weight(1f), shape = RoundedCornerShape(12.dp), colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)) { Text("ALL OUT (2 pts)") }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text("Team B Points", fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
            Button(onClick = {}, modifier = Modifier.weight(1f), shape = RoundedCornerShape(12.dp)) { Text("Touch Point") }
            Button(onClick = {}, modifier = Modifier.weight(1f), shape = RoundedCornerShape(12.dp)) { Text("Bonus Point") }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
            Button(onClick = {}, modifier = Modifier.weight(1f), shape = RoundedCornerShape(12.dp), colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)) { Text("Tackle Point") }
            Button(onClick = {}, modifier = Modifier.weight(1f), shape = RoundedCornerShape(12.dp), colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)) { Text("ALL OUT (2 pts)") }
        }
    }
}
