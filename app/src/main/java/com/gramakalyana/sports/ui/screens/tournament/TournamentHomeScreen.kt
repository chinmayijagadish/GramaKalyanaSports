package com.gramakalyana.sports.ui.screens.tournament

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
import com.gramakalyana.sports.data.model.Tournament
import com.gramakalyana.sports.navigation.Screen
import com.gramakalyana.sports.viewmodel.TournamentViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TournamentHomeScreen(
    navController: NavController
) {

    val tournamentViewModel:
            TournamentViewModel = viewModel()

    val tournaments by
    tournamentViewModel.tournaments.collectAsState()

    var showDeleteDialog by remember {
        mutableStateOf(false)
    }

    var showEditDialog by remember {
        mutableStateOf(false)
    }

    var selectedTournament by remember {
        mutableStateOf<Tournament?>(null)
    }

    var editedTournamentName by remember {
        mutableStateOf("")
    }

    Scaffold(

        topBar = {

            TopAppBar(

                title = {

                    Text(
                        text = "Tournament Home",

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

        if (showDeleteDialog && selectedTournament != null) {

            AlertDialog(

                onDismissRequest = {

                    showDeleteDialog = false
                },

                title = {

                    Text("Delete Tournament")
                },

                text = {

                    Text(
                        "Are you sure you want to delete this tournament?"
                    )
                },

                confirmButton = {

                    TextButton(

                        onClick = {

                            tournamentViewModel
                                .deleteTournament(

                                    selectedTournament!!
                                        .tournamentId
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

        if (showEditDialog && selectedTournament != null) {

            AlertDialog(

                onDismissRequest = {

                    showEditDialog = false
                },

                title = {

                    Text("Edit Tournament")
                },

                text = {

                    OutlinedTextField(
                        value = editedTournamentName,

                        onValueChange = {
                            editedTournamentName = it
                        },

                        label = {
                            Text("Tournament Name")
                        }
                    )
                },

                confirmButton = {

                    TextButton(

                        onClick = {

                            val updatedTournament =

                                selectedTournament!!.copy(

                                    tournamentName =
                                        editedTournamentName
                                )

                            tournamentViewModel
                                .createTournament(
                                    updatedTournament
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
                Arrangement.spacedBy(18.dp)
        ) {

            item {

                Button(

                    onClick = {

                        navController.navigate(
                            Screen.TournamentSetup.route
                        )
                    },

                    modifier = Modifier.fillMaxWidth(),

                    shape = RoundedCornerShape(14.dp)
                ) {

                    Text(
                        text = "CREATE NEW TOURNAMENT"
                    )
                }
            }

            item {

                Text(
                    text = "Existing Tournaments",

                    style =
                        MaterialTheme.typography.titleLarge,

                    fontWeight = FontWeight.Bold
                )
            }

            if (tournaments.isEmpty()) {

                item {

                    Text(
                        text =
                            "Loading tournaments..."
                    )
                }
            }

            items(tournaments) { tournament ->

                Card(
                    modifier = Modifier.fillMaxWidth(),

                    shape = RoundedCornerShape(18.dp),

                    colors = CardDefaults.cardColors(
                        containerColor =
                            MaterialTheme.colorScheme.primaryContainer
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(18.dp)
                    ) {

                        Text(
                            text =
                                tournament.tournamentName,

                            style =
                                MaterialTheme.typography.titleLarge,

                            fontWeight = FontWeight.Bold
                        )

                        Spacer(
                            modifier =
                                Modifier.height(8.dp)
                        )

                        Text(
                            text =
                                "Sport: ${tournament.sportType}"
                        )

                        Text(
                            text =
                                "Zone: ${tournament.zone}"
                        )

                        Spacer(
                            modifier =
                                Modifier.height(12.dp)
                        )

                        Button(

                            onClick = {

                                navController.navigate(

                                    Screen.TournamentDashboard
                                        .createRoute(

                                            tournament.tournamentId,

                                            tournament.tournamentName,

                                            tournament.sportType,

                                            tournament.zone
                                        )
                                )
                            },

                            modifier = Modifier.fillMaxWidth()
                        ) {

                            Text(
                                text = "OPEN TOURNAMENT"
                            )
                        }

                        Spacer(
                            modifier =
                                Modifier.height(10.dp)
                        )

                        Button(

                            onClick = {

                                selectedTournament =
                                    tournament

                                editedTournamentName =
                                    tournament.tournamentName

                                showEditDialog = true
                            },

                            modifier =
                                Modifier.fillMaxWidth(),

                            shape =
                                RoundedCornerShape(12.dp)
                        ) {

                            Text(
                                text = "EDIT TOURNAMENT"
                            )
                        }

                        Spacer(
                            modifier =
                                Modifier.height(10.dp)
                        )

                        Button(

                            onClick = {

                                selectedTournament =
                                    tournament

                                showDeleteDialog = true
                            },

                            modifier =
                                Modifier.fillMaxWidth(),

                            shape =
                                RoundedCornerShape(12.dp)
                        ) {

                            Text(
                                text = "DELETE TOURNAMENT"
                            )
                        }
                    }
                }
            }
        }
    }
}