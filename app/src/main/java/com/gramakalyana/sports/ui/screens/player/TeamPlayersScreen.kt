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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.gramakalyana.sports.data.model.Player
import com.gramakalyana.sports.navigation.Screen
import com.gramakalyana.sports.viewmodel.PlayerViewModel
import com.gramakalyana.sports.viewmodel.TeamViewModel

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

    val teamViewModel:
            TeamViewModel = viewModel()

    val allPlayers by
    playerViewModel.players.collectAsState()

    val players =
        allPlayers.filter {

            it.teamId == teamId
        }

    var showDeleteDialog by remember {
        mutableStateOf(false)
    }

    var selectedPlayer by remember {
        mutableStateOf<Player?>(null)
    }

    var showEditDialog by remember {
        mutableStateOf(false)
    }

    var editedName by remember {
        mutableStateOf("")
    }

    var editedJersey by remember {
        mutableStateOf("")
    }

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

        if (showDeleteDialog && selectedPlayer != null) {

            AlertDialog(

                onDismissRequest = {

                    showDeleteDialog = false
                },

                title = {

                    Text("Delete Player")
                },

                text = {

                    Text(
                        "Are you sure you want to delete this player?"
                    )
                },

                confirmButton = {

                    TextButton(

                        onClick = {

                            playerViewModel.deletePlayer(
                                selectedPlayer!!.playerId
                            )

                            val updatedCount =
                                players.size - 1

                            teamViewModel.updatePlayerCount(

                                teamId,

                                updatedCount
                            )

                            showDeleteDialog = false
                        }
                    ) {

                        Text("Delete")
                    }
                },

                dismissButton = {

                    TextButton(

                        onClick = {

                            showDeleteDialog = false
                        }
                    ) {

                        Text("Cancel")
                    }
                }
            )
        }

        if (showEditDialog && selectedPlayer != null) {

            AlertDialog(

                onDismissRequest = {

                    showEditDialog = false
                },

                title = {

                    Text("Edit Player")
                },

                text = {

                    Column {

                        OutlinedTextField(
                            value = editedName,

                            onValueChange = {
                                editedName = it
                            },

                            label = {
                                Text("Player Name")
                            }
                        )

                        Spacer(
                            modifier =
                                Modifier.height(12.dp)
                        )

                        OutlinedTextField(
                            value = editedJersey,

                            onValueChange = {
                                editedJersey = it
                            },

                            label = {
                                Text("Jersey Number")
                            }
                        )
                    }
                },

                confirmButton = {

                    TextButton(

                        onClick = {

                            val updatedPlayer =

                                selectedPlayer!!.copy(

                                    playerName =
                                        editedName,

                                    jerseyNumber =
                                        editedJersey.toInt()
                                )

                            playerViewModel.createPlayer(
                                updatedPlayer
                            )

                            showEditDialog = false
                        }
                    ) {

                        Text("Save")
                    }
                },

                dismissButton = {

                    TextButton(

                        onClick = {

                            showEditDialog = false
                        }
                    ) {

                        Text("Cancel")
                    }
                }
            )
        }

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

            item {

                Text(
                    text = "Registered Players",

                    style =
                        MaterialTheme.typography.titleLarge,

                    fontWeight = FontWeight.Bold
                )
            }

            if (allPlayers.isEmpty()) {

                item {

                    Text(
                        text =
                            "Loading players..."
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

                        Spacer(
                            modifier =
                                Modifier.height(12.dp)
                        )

                        Button(

                            onClick = {

                                selectedPlayer = player

                                editedName =
                                    player.playerName

                                editedJersey =
                                    player.jerseyNumber.toString()

                                showEditDialog = true
                            },

                            modifier =
                                Modifier.fillMaxWidth(),

                            shape =
                                RoundedCornerShape(12.dp)
                        ) {

                            Text(
                                text = "EDIT PLAYER"
                            )
                        }

                        Spacer(
                            modifier =
                                Modifier.height(10.dp)
                        )

                        Button(

                            onClick = {

                                selectedPlayer = player

                                showDeleteDialog = true
                            },

                            modifier =
                                Modifier.fillMaxWidth(),

                            shape =
                                RoundedCornerShape(12.dp)
                        ) {

                            Text(
                                text = "DELETE PLAYER"
                            )
                        }
                    }
                }
            }
        }
    }
}