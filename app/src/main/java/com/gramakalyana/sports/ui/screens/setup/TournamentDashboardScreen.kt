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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.gramakalyana.sports.data.model.Team
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

    val allTeams by
    teamViewModel.teams.collectAsState()

    val teams =
        allTeams.filter {

            it.tournamentId == tournamentId
        }

    var showDeleteDialog by remember {
        mutableStateOf(false)
    }

    var showEditDialog by remember {
        mutableStateOf(false)
    }

    var selectedTeam by remember {
        mutableStateOf<Team?>(null)
    }

    var editedTeamName by remember {
        mutableStateOf("")
    }

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

        if (showDeleteDialog && selectedTeam != null) {

            AlertDialog(

                onDismissRequest = {

                    showDeleteDialog = false
                },

                title = {

                    Text("Delete Team")
                },

                text = {

                    Text(
                        "Are you sure you want to delete this team?"
                    )
                },

                confirmButton = {

                    TextButton(

                        onClick = {

                            teamViewModel.deleteTeam(
                                selectedTeam!!.teamId
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

        if (showEditDialog && selectedTeam != null) {

            AlertDialog(

                onDismissRequest = {

                    showEditDialog = false
                },

                title = {

                    Text("Edit Team")
                },

                text = {

                    OutlinedTextField(
                        value = editedTeamName,

                        onValueChange = {
                            editedTeamName = it
                        },

                        label = {
                            Text("Team Name")
                        }
                    )
                },

                confirmButton = {

                    TextButton(

                        onClick = {

                            val updatedTeam =

                                selectedTeam!!.copy(

                                    teamName =
                                        editedTeamName
                                )

                            teamViewModel.createTeam(
                                updatedTeam
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
                    }
                }
            }

            item {

                Button(

                    onClick = {

                        navController.navigate(

                            Screen.AddTeam.createRoute(

                                tournamentId,

                                sport,

                                zone
                            )
                        )
                    },

                    modifier = Modifier.fillMaxWidth(),

                    shape = RoundedCornerShape(14.dp)
                ) {

                    Text(
                        text = "ADD TEAM"
                    )
                }
            }

            if (allTeams.isEmpty()) {

                item {

                    Text(
                        text =
                            "Loading teams..."
                    )
                }
            }

            else if (teams.isEmpty()) {

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
                                "Players: ${team.playerCount}"
                        )

                        Spacer(
                            modifier =
                                Modifier.height(12.dp)
                        )

                        Button(

                            onClick = {

                                navController.navigate(

                                    Screen.TeamPlayers.createRoute(

                                        team.teamId,

                                        tournamentId,

                                        sport
                                    )
                                )
                            },

                            modifier =
                                Modifier.fillMaxWidth(),

                            shape =
                                RoundedCornerShape(12.dp)
                        ) {

                            Text(
                                text = "MANAGE PLAYERS"
                            )
                        }

                        Spacer(
                            modifier =
                                Modifier.height(10.dp)
                        )

                        Button(

                            onClick = {

                                selectedTeam = team

                                editedTeamName =
                                    team.teamName

                                showEditDialog = true
                            },

                            modifier =
                                Modifier.fillMaxWidth(),

                            shape =
                                RoundedCornerShape(12.dp)
                        ) {

                            Text(
                                text = "EDIT TEAM"
                            )
                        }

                        Spacer(
                            modifier =
                                Modifier.height(10.dp)
                        )

                        Button(

                            onClick = {

                                selectedTeam = team

                                showDeleteDialog = true
                            },

                            modifier =
                                Modifier.fillMaxWidth(),

                            shape =
                                RoundedCornerShape(12.dp)
                        ) {

                            Text(
                                text = "DELETE TEAM"
                            )
                        }
                    }
                }
            }

            item {

                HorizontalDivider()
            }
        }
    }
}