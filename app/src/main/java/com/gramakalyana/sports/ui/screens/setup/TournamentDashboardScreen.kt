package com.gramakalyana.sports.ui.screens.setup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.gramakalyana.sports.navigation.Screen
import com.gramakalyana.sports.viewmodel.TeamViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TournamentDashboardScreen(

    navController: NavController,

    tournamentId: String,

    tournamentName: String,

    sport: String,

    zone: String
) {

    val teamViewModel: TeamViewModel =
        viewModel()

    val teams by
    teamViewModel.teams.collectAsState()

    Scaffold(

        topBar = {

            TopAppBar(

                title = {

                    Text(
                        text = "Tournament Dashboard",

                        fontWeight = FontWeight.Bold
                    )
                },

                colors = TopAppBarDefaults.topAppBarColors(

                    containerColor =
                        MaterialTheme.colorScheme.background
                )
            )
        }

    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    MaterialTheme.colorScheme.background
                )
                .padding(paddingValues)
                .padding(16.dp),

            verticalArrangement =
                Arrangement.spacedBy(20.dp)
        ) {

            item {

                Card(
                    modifier = Modifier.fillMaxWidth(),

                    shape = RoundedCornerShape(20.dp),

                    colors = CardDefaults.cardColors(
                        containerColor =
                            MaterialTheme.colorScheme.primaryContainer
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {

                        Text(
                            text = tournamentName,

                            style =
                                MaterialTheme.typography.headlineSmall,

                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "Sport: $sport"
                        )

                        Text(
                            text = "Zone: $zone"
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "Tournament ID:",

                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = tournamentId,

                            style =
                                MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }

            item {

                Card(
                    modifier = Modifier.fillMaxWidth(),

                    shape = RoundedCornerShape(20.dp),

                    colors = CardDefaults.cardColors(
                        containerColor =
                            MaterialTheme.colorScheme.secondaryContainer
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(20.dp),

                        horizontalAlignment =
                            Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = "Manage Teams",

                            style =
                                MaterialTheme.typography.titleLarge,

                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(

                            onClick = {

                                navController.navigate(

                                    Screen.AddTeam.createRoute(
                                        sport,
                                        zone
                                    )
                                )
                            },

                            modifier = Modifier.fillMaxWidth(),

                            shape = RoundedCornerShape(14.dp),

                            colors = ButtonDefaults.buttonColors(
                                containerColor =
                                    MaterialTheme.colorScheme.primary
                            )
                        ) {

                            Text(
                                text = "ADD TEAM",

                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            item {

                Text(
                    text = "Added Teams",

                    style =
                        MaterialTheme.typography.titleLarge,

                    fontWeight = FontWeight.Bold
                )
            }

            if (teams.isEmpty()) {

                item {

                    Text(
                        text =
                            "No teams added yet."
                    )
                }
            }

            items(teams) { team ->

                Card(
                    modifier = Modifier.fillMaxWidth(),

                    shape = RoundedCornerShape(18.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(18.dp)
                    ) {

                        Text(
                            text = team.teamName,

                            style =
                                MaterialTheme.typography.titleMedium,

                            fontWeight = FontWeight.Bold
                        )

                        Spacer(
                            modifier =
                                Modifier.height(6.dp)
                        )

                        Text(
                            text =
                                "Sport: ${team.sportType}"
                        )

                        Text(
                            text =
                                "Zone: ${team.zone}"
                        )

                        Text(
                            text =
                                "Players: ${team.playerCount}"
                        )
                    }
                }
            }

            item {

                HorizontalDivider()

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Upcoming Features",

                    style =
                        MaterialTheme.typography.titleMedium,

                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text("• Player Registration")
                Text("• Match Scheduling")
                Text("• Live Scoring")
                Text("• Knockout Fixtures")
                Text("• Public Live Viewer")
            }
        }
    }
}