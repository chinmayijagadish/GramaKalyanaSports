package com.gramakalyana.sports.ui.screens.player

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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.gramakalyana.sports.navigation.Screen
import com.gramakalyana.sports.viewmodel.PlayerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamPlayersScreen(

    navController: NavController,

    teamId: String,

    tournamentId: String,

    sportType: String
) {

    val playerViewModel:
            PlayerViewModel = viewModel()

    val allPlayers by
    playerViewModel.players.collectAsState()

    val players =
        playerViewModel.getPlayersForTeam(
            teamId
        )

    Scaffold(

        topBar = {

            TopAppBar(

                title = {

                    Text(
                        text = "Team Players",

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
                .padding(paddingValues)
                .padding(16.dp),

            verticalArrangement =
                Arrangement.spacedBy(16.dp)
        ) {

            item {

                Button(

                    onClick = {

                        navController.navigate(

                            Screen.AddPlayer.createRoute(

                                teamId,

                                tournamentId,

                                sportType
                            )
                        )
                    },

                    modifier =
                        Modifier.fillMaxWidth(),

                    shape =
                        RoundedCornerShape(14.dp)
                ) {

                    Text(
                        text = "ADD PLAYER"
                    )
                }
            }

            if (allPlayers.isEmpty()) {

                item {

                    Text(
                        text = "Loading players..."
                    )
                }
            }

            else if (players.isEmpty()) {

                item {

                    Text(
                        text =
                            "No players added yet."
                    )
                }
            }

            items(players) { player ->

                Card(
                    modifier =
                        Modifier.fillMaxWidth(),

                    shape =
                        RoundedCornerShape(18.dp),

                    colors =
                        CardDefaults.cardColors(
                            containerColor =
                                MaterialTheme
                                    .colorScheme
                                    .secondaryContainer
                        )
                ) {

                    Column(
                        modifier =
                            Modifier.padding(18.dp)
                    ) {

                        Text(
                            text =
                                player.playerName,

                            style =
                                MaterialTheme
                                    .typography
                                    .titleMedium,

                            fontWeight =
                                FontWeight.Bold
                        )

                        Spacer(
                            modifier =
                                Modifier.height(6.dp)
                        )

                        Text(
                            text =
                                "Jersey: ${player.jerseyNumber}"
                        )

                        Text(
                            text =
                                "Role: ${player.role}"
                        )

                        Text(
                            text =
                                "Sport: ${player.sportType}"
                        )
                    }
                }
            }
        }
    }
}