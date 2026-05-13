package com.gramakalyana.sports.ui.screens.tournament

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
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
            TournamentViewModel =
        viewModel()

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

                        text =
                            "Tournaments",

                        fontWeight =
                            FontWeight.Bold
                    )
                },

                colors =
                    TopAppBarDefaults.topAppBarColors(

                        containerColor =
                            MaterialTheme.colorScheme.background
                    )
            )
        },

        floatingActionButton = {

            FloatingActionButton(

                onClick = {

                    navController.navigate(
                        Screen.TournamentSetup.route
                    )
                },

                containerColor =
                    MaterialTheme.colorScheme.primary
            ) {

                Icon(

                    imageVector =
                        Icons.Default.Edit,

                    contentDescription =
                        "Create Tournament",

                    tint = Color.White
                )
            }
        }

    ) { paddingValues ->

        if (
            showDeleteDialog &&
            selectedTournament != null
        ) {

            AlertDialog(

                onDismissRequest = {

                    showDeleteDialog = false
                },

                title = {

                    Text(
                        text =
                            "Delete Tournament"
                    )
                },

                text = {

                    Text(
                        text =
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

        if (
            showEditDialog &&
            selectedTournament != null
        ) {

            AlertDialog(

                onDismissRequest = {

                    showEditDialog = false
                },

                title = {

                    Text(
                        text =
                            "Edit Tournament"
                    )
                },

                text = {

                    OutlinedTextField(

                        value =
                            editedTournamentName,

                        onValueChange = {

                            editedTournamentName =
                                it
                        },

                        label = {

                            Text(
                                text =
                                    "Tournament Name"
                            )
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

                Text(

                    text =
                        "Manage Sports Tournaments",

                    style =
                        MaterialTheme.typography.headlineSmall,

                    fontWeight =
                        FontWeight.ExtraBold
                )

                Spacer(
                    modifier =
                        Modifier.height(6.dp)
                )

                Text(

                    text =
                        "Create, manage and monitor tournaments.",

                    color =
                        MaterialTheme.colorScheme.onSurface.copy(
                            alpha = 0.7f
                        )
                )

                Spacer(
                    modifier =
                        Modifier.height(10.dp)
                )

                Text(

                    text =
                        "Total Tournaments: ${tournaments.size}",

                    fontWeight =
                        FontWeight.Bold,

                    color =
                        MaterialTheme.colorScheme.primary
                )
            }

            if (tournaments.isEmpty()) {

                item {

                    Card(

                        modifier =
                            Modifier.fillMaxWidth(),

                        shape =
                            RoundedCornerShape(20.dp),

                        colors =
                            CardDefaults.cardColors(

                                containerColor =
                                    MaterialTheme.colorScheme.surfaceVariant
                            )
                    ) {

                        Column(
                            modifier =
                                Modifier.padding(22.dp)
                        ) {

                            Text(
                                text =
                                    "No tournaments created yet."
                            )
                        }
                    }
                }
            }

            items(tournaments) { tournament ->

                Card(

                    modifier =
                        Modifier.fillMaxWidth(),

                    shape =
                        RoundedCornerShape(22.dp),

                    colors =
                        CardDefaults.cardColors(

                            containerColor =
                                MaterialTheme.colorScheme.primaryContainer
                        )
                ) {

                    Column(
                        modifier =
                            Modifier.padding(20.dp)
                    ) {

                        Row(

                            modifier =
                                Modifier.fillMaxWidth(),

                            horizontalArrangement =
                                Arrangement.SpaceBetween,

                            verticalAlignment =
                                Alignment.CenterVertically
                        ) {

                            Column(
                                modifier =
                                    Modifier.weight(1f)
                            ) {

                                Text(

                                    text =
                                        tournament.tournamentName,

                                    style =
                                        MaterialTheme.typography.titleLarge,

                                    fontWeight =
                                        FontWeight.ExtraBold
                                )

                                Spacer(
                                    modifier =
                                        Modifier.height(8.dp)
                                )

                                Text(

                                    text = when (tournament.sportType) {

                                        "Cricket" ->
                                            "🏏 Cricket"

                                        "Kabaddi" ->
                                            "🤼 Kabaddi"

                                        else ->
                                            "🏐 Volleyball"
                                    }
                                )

                                Text(
                                    text =
                                        "📍 ${tournament.zone}"
                                )
                            }

                            Row {

                                IconButton(

                                    onClick = {

                                        selectedTournament =
                                            tournament

                                        editedTournamentName =
                                            tournament.tournamentName

                                        showEditDialog = true
                                    }
                                ) {

                                    Icon(

                                        imageVector =
                                            Icons.Default.Edit,

                                        contentDescription =
                                            "Edit"
                                    )
                                }

                                IconButton(

                                    onClick = {

                                        selectedTournament =
                                            tournament

                                        showDeleteDialog = true
                                    }
                                ) {

                                    Icon(

                                        imageVector =
                                            Icons.Default.Delete,

                                        contentDescription =
                                            "Delete"
                                    )
                                }
                            }
                        }

                        Spacer(
                            modifier =
                                Modifier.height(18.dp)
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

                            modifier =
                                Modifier.fillMaxWidth(),

                            shape =
                                RoundedCornerShape(14.dp)
                        ) {

                            Icon(

                                imageVector =
                                    Icons.Default.ArrowForward,

                                contentDescription = null
                            )

                            Spacer(
                                modifier =
                                    Modifier.width(8.dp)
                            )

                            Text(
                                text =
                                    "OPEN TOURNAMENT"
                            )
                        }
                    }
                }
            }

            item {

                Spacer(
                    modifier =
                        Modifier.height(90.dp)
                )
            }
        }
    }
}