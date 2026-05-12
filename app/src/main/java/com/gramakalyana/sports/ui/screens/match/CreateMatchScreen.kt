package com.gramakalyana.sports.ui.screens.match

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.gramakalyana.sports.data.model.Match
import com.gramakalyana.sports.viewmodel.MatchViewModel
import com.gramakalyana.sports.viewmodel.TeamViewModel
import java.util.Calendar
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateMatchScreen(

    navController: NavController,

    tournamentId: String,

    sportType: String,

    zone: String
) {

    val context = LocalContext.current

    val calendar = Calendar.getInstance()

    val teamViewModel:
            TeamViewModel = viewModel()

    val matchViewModel:
            MatchViewModel = viewModel()

    val allTeams by
    teamViewModel.teams.collectAsState()

    val teams =
        allTeams.filter {

            it.tournamentId == tournamentId
        }

    var expandedTeamA by remember {
        mutableStateOf(false)
    }

    var expandedTeamB by remember {
        mutableStateOf(false)
    }

    var selectedTeamAName by remember {
        mutableStateOf("")
    }

    var selectedTeamAId by remember {
        mutableStateOf("")
    }

    var selectedTeamBName by remember {
        mutableStateOf("")
    }

    var selectedTeamBId by remember {
        mutableStateOf("")
    }

    var venue by remember {
        mutableStateOf("")
    }

    var date by remember {
        mutableStateOf("")
    }

    var time by remember {
        mutableStateOf("")
    }

    val datePickerDialog = DatePickerDialog(

        context,

        { _, year, month, dayOfMonth ->

            date =
                "$dayOfMonth/${month + 1}/$year"
        },

        calendar.get(Calendar.YEAR),

        calendar.get(Calendar.MONTH),

        calendar.get(Calendar.DAY_OF_MONTH)
    )

    val timePickerDialog = TimePickerDialog(

        context,

        { _, hour, minute ->

            time =
                String.format(
                    "%02d:%02d",
                    hour,
                    minute
                )
        },

        calendar.get(Calendar.HOUR_OF_DAY),

        calendar.get(Calendar.MINUTE),

        true
    )

    Scaffold(

        topBar = {

            TopAppBar(

                title = {

                    Text(
                        text = "Create Match",

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
                Arrangement.spacedBy(18.dp)
        ) {

            item {

                ExposedDropdownMenuBox(
                    expanded = expandedTeamA,

                    onExpandedChange = {
                        expandedTeamA = !expandedTeamA
                    }
                ) {

                    OutlinedTextField(
                        value = selectedTeamAName,
                        onValueChange = {},
                        readOnly = true,
                        label = {
                            Text("Select Team A")
                        },

                        trailingIcon = {

                            ExposedDropdownMenuDefaults
                                .TrailingIcon(
                                    expanded = expandedTeamA
                                )
                        },

                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .menuAnchor()
                    )

                    ExposedDropdownMenu(
                        expanded = expandedTeamA,

                        onDismissRequest = {
                            expandedTeamA = false
                        }
                    ) {

                        teams.forEach { team ->

                            DropdownMenuItem(

                                text = {
                                    Text(team.teamName)
                                },

                                onClick = {

                                    selectedTeamAName =
                                        team.teamName

                                    selectedTeamAId =
                                        team.teamId

                                    expandedTeamA = false
                                }
                            )
                        }
                    }
                }
            }

            item {

                ExposedDropdownMenuBox(
                    expanded = expandedTeamB,

                    onExpandedChange = {
                        expandedTeamB = !expandedTeamB
                    }
                ) {

                    OutlinedTextField(
                        value = selectedTeamBName,
                        onValueChange = {},
                        readOnly = true,
                        label = {
                            Text("Select Team B")
                        },

                        trailingIcon = {

                            ExposedDropdownMenuDefaults
                                .TrailingIcon(
                                    expanded = expandedTeamB
                                )
                        },

                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .menuAnchor()
                    )

                    ExposedDropdownMenu(
                        expanded = expandedTeamB,

                        onDismissRequest = {
                            expandedTeamB = false
                        }
                    ) {

                        teams.forEach { team ->

                            DropdownMenuItem(

                                text = {
                                    Text(team.teamName)
                                },

                                onClick = {

                                    selectedTeamBName =
                                        team.teamName

                                    selectedTeamBId =
                                        team.teamId

                                    expandedTeamB = false
                                }
                            )
                        }
                    }
                }
            }

            item {

                OutlinedTextField(

                    value = venue,

                    onValueChange = {
                        venue = it
                    },

                    modifier =
                        Modifier.fillMaxWidth(),

                    label = {
                        Text("Venue")
                    }
                )
            }

            item {

                Button(

                    onClick = {

                        datePickerDialog.show()
                    },

                    modifier =
                        Modifier.fillMaxWidth()
                ) {

                    Text(

                        text =
                            if (date.isBlank())

                                "SELECT MATCH DATE"

                            else
                                "Date: $date"
                    )
                }
            }

            item {

                Button(

                    onClick = {

                        timePickerDialog.show()
                    },

                    modifier =
                        Modifier.fillMaxWidth()
                ) {

                    Text(

                        text =
                            if (time.isBlank())

                                "SELECT MATCH TIME"

                            else
                                "Time: $time"
                    )
                }
            }

            item {

                Button(

                    onClick = {

                        if (

                            selectedTeamAId.isBlank() ||
                            selectedTeamBId.isBlank() ||
                            venue.isBlank() ||
                            date.isBlank() ||
                            time.isBlank()
                        ) {

                            Toast.makeText(

                                context,

                                "Fill all fields",

                                Toast.LENGTH_SHORT
                            ).show()

                            return@Button
                        }

                        val match = Match(

                            matchId =
                                UUID.randomUUID().toString(),

                            tournamentId =
                                tournamentId,

                            tournamentName =
                                "",

                            zone = zone,

                            sportType =
                                sportType,

                            teamAId =
                                selectedTeamAId,

                            teamAName =
                                selectedTeamAName,

                            teamBId =
                                selectedTeamBId,

                            teamBName =
                                selectedTeamBName,

                            matchDate =
                                date,

                            matchTime =
                                time,

                            venue =
                                venue,

                            status =
                                "UPCOMING",

                            currentPhase =
                                "",

                            winner =
                                "",

                            tossWinner =
                                "",

                            tossDecision =
                                "",

                            matchNotes =
                                "",

                            teamAScore = 0,

                            teamBScore = 0
                        )

                        matchViewModel.createMatch(
                            match
                        )

                        Toast.makeText(

                            context,

                            "Match Created",

                            Toast.LENGTH_SHORT
                        ).show()

                        navController.popBackStack()
                    },

                    modifier =
                        Modifier.fillMaxWidth(),

                    shape =
                        RoundedCornerShape(14.dp)
                ) {

                    Text(
                        text = "CREATE MATCH"
                    )
                }
            }
        }
    }
}