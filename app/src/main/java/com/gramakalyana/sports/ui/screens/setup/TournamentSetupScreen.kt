package com.gramakalyana.sports.ui.screens.setup

import android.app.DatePickerDialog
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.gramakalyana.sports.data.model.Tournament
import com.gramakalyana.sports.navigation.Screen
import com.gramakalyana.sports.viewmodel.TournamentViewModel
import java.util.Calendar
import java.util.UUID

val sportsList = listOf(
    "Cricket",
    "Kabaddi",
    "Volleyball"
)

val zonesList = listOf(
    "Rural",
    "Urban",
    "North",
    "South"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TournamentSetupScreen(
    navController: NavController
) {

    var tournamentName by remember {
        mutableStateOf("")
    }

    var selectedZone by remember {
        mutableStateOf("")
    }

    var selectedSport by remember {
        mutableStateOf("")
    }

    var expandedSport by remember {
        mutableStateOf(false)
    }

    var expandedZone by remember {
        mutableStateOf(false)
    }

    var selectedDate by remember {
        mutableStateOf("")
    }

    val tournamentViewModel:
            TournamentViewModel = viewModel()

    val currentUserId =
        FirebaseAuth.getInstance()
            .currentUser
            ?.uid ?: ""

    val calendar = Calendar.getInstance()

    val datePickerDialog = DatePickerDialog(
        navController.context,

        { _, year, month, dayOfMonth ->

            selectedDate =
                "$dayOfMonth/${month + 1}/$year"
        },

        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    Scaffold(

        topBar = {

            TopAppBar(
                title = {

                    Text(
                        text = "Setup Tournament",

                        fontWeight = FontWeight.Bold
                    )
                },

                navigationIcon = {

                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {

                        Icon(
                            imageVector =
                                Icons.Default.ArrowBack,

                            contentDescription = "Back"
                        )
                    }
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
                Arrangement.spacedBy(18.dp)
        ) {

            item {

                Text(
                    text = "General Information",

                    style =
                        MaterialTheme.typography.titleLarge,

                    color =
                        MaterialTheme.colorScheme.primary,

                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = tournamentName,

                    onValueChange = {
                        tournamentName = it
                    },

                    label = {
                        Text("Tournament Name")
                    },

                    modifier = Modifier.fillMaxWidth(),

                    shape = RoundedCornerShape(14.dp)
                )

                Spacer(modifier = Modifier.height(14.dp))

                // ZONE DROPDOWN

                ExposedDropdownMenuBox(
                    expanded = expandedZone,

                    onExpandedChange = {
                        expandedZone = !expandedZone
                    }
                ) {

                    OutlinedTextField(
                        value = selectedZone,

                        onValueChange = {},

                        readOnly = true,

                        label = {
                            Text("Select Zone")
                        },

                        trailingIcon = {

                            ExposedDropdownMenuDefaults
                                .TrailingIcon(
                                    expanded = expandedZone
                                )
                        },

                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(),

                        shape = RoundedCornerShape(14.dp)
                    )

                    ExposedDropdownMenu(
                        expanded = expandedZone,

                        onDismissRequest = {
                            expandedZone = false
                        }
                    ) {

                        zonesList.forEach { zone ->

                            DropdownMenuItem(
                                text = {
                                    Text(zone)
                                },

                                onClick = {

                                    selectedZone = zone

                                    expandedZone = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // SPORT DROPDOWN

                ExposedDropdownMenuBox(
                    expanded = expandedSport,

                    onExpandedChange = {
                        expandedSport = !expandedSport
                    }
                ) {

                    OutlinedTextField(
                        value = selectedSport,

                        onValueChange = {},

                        readOnly = true,

                        label = {
                            Text("Select Sport")
                        },

                        trailingIcon = {

                            ExposedDropdownMenuDefaults
                                .TrailingIcon(
                                    expanded = expandedSport
                                )
                        },

                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(),

                        shape = RoundedCornerShape(14.dp)
                    )

                    ExposedDropdownMenu(
                        expanded = expandedSport,

                        onDismissRequest = {
                            expandedSport = false
                        }
                    ) {

                        sportsList.forEach { sport ->

                            DropdownMenuItem(
                                text = {
                                    Text(sport)
                                },

                                onClick = {

                                    selectedSport = sport

                                    expandedSport = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                OutlinedTextField(
                    value = selectedDate,

                    onValueChange = {},

                    readOnly = true,

                    label = {
                        Text("Tournament Date")
                    },

                    modifier = Modifier.fillMaxWidth(),

                    trailingIcon = {

                        IconButton(
                            onClick = {
                                datePickerDialog.show()
                            }
                        ) {

                            Icon(
                                imageVector =
                                    Icons.Default.DateRange,

                                contentDescription =
                                    "Pick Date"
                            )
                        }
                    },

                    shape = RoundedCornerShape(14.dp)
                )
            }

            item {

                Spacer(modifier = Modifier.height(28.dp))

                Button(

                    onClick = {

                        if (
                            tournamentName.isBlank() ||
                            selectedZone.isBlank() ||
                            selectedSport.isBlank() ||
                            selectedDate.isBlank()
                        ) {

                            Toast.makeText(
                                navController.context,

                                "Please fill all fields",

                                Toast.LENGTH_SHORT
                            ).show()

                            return@Button
                        }

                        val tournamentId =
                            UUID.randomUUID().toString()

                        val tournament = Tournament(

                            tournamentId = tournamentId,

                            tournamentName = tournamentName,

                            sportType = selectedSport,

                            zone = selectedZone,

                            startDate = selectedDate,

                            createdByUserId =
                                currentUserId
                        )

                        tournamentViewModel
                            .createTournament(
                                tournament
                            )

                        navController.navigate(

                            Screen.TournamentDashboard.createRoute(

                                tournamentId,

                                tournamentName,

                                selectedSport,

                                selectedZone
                            )

                        ) {

                            popUpTo(
                                Screen.TournamentSetup.route
                            ) {

                                inclusive = true
                            }
                        }
                    },

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(58.dp),

                    shape = RoundedCornerShape(14.dp)
                ) {

                    Text(
                        text = "CONTINUE TO TEAM SETUP",

                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}