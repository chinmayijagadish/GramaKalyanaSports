package com.gramakalyana.sports.ui.screens.player

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.gramakalyana.sports.viewmodel.PlayerViewModel
import com.gramakalyana.sports.viewmodel.TeamViewModel
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPlayerScreen(

    navController: NavController,

    teamId: String,

    tournamentId: String,

    sportType: String
) {

    var playerName by remember {
        mutableStateOf("")
    }

    var jerseyNumber by remember {
        mutableStateOf("")
    }

    var selectedRole by remember {
        mutableStateOf("")
    }

    var expanded by remember {
        mutableStateOf(false)
    }

    val playerViewModel:
            PlayerViewModel = viewModel()

    val teamViewModel:
            TeamViewModel = viewModel()

    val allPlayers by
    playerViewModel.players.collectAsState()

    val currentPlayers =

        allPlayers.filter {

            it.teamId == teamId
        }

    val maxPlayers =

        when (sportType) {

            "Cricket" -> 11

            "Kabaddi" -> 7

            else -> 6
        }

    val roles = when (sportType) {

        "Cricket" -> listOf(

            "Batsman",

            "Batter",

            "Bowler",

            "Fast Bowler",

            "Spinner",

            "Wicket Keeper",

            "All Rounder"
        )

        "Kabaddi" -> listOf(

            "Raider",

            "Defender",

            "Corner Defender",

            "Cover Defender",

            "All Rounder"
        )

        else -> listOf(

            "Setter",

            "Outside Hitter",

            "Opposite Hitter",

            "Middle Blocker",

            "Libero"
        )
    }

    Scaffold(

        topBar = {

            TopAppBar(

                title = {

                    Text(

                        text =
                            "Add Player",

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

                Text(

                    text =
                        "Players: ${currentPlayers.size}/$maxPlayers",

                    style =
                        MaterialTheme.typography.titleMedium,

                    fontWeight =
                        FontWeight.Bold
                )

                Spacer(
                    modifier =
                        Modifier.height(12.dp)
                )

                OutlinedTextField(
                    value = playerName,

                    onValueChange = {
                        playerName = it
                    },

                    label = {
                        Text("Player Name")
                    },

                    modifier =
                        Modifier.fillMaxWidth(),

                    shape =
                        RoundedCornerShape(14.dp)
                )

                Spacer(
                    modifier =
                        Modifier.height(12.dp)
                )

                OutlinedTextField(
                    value = jerseyNumber,

                    onValueChange = {

                        jerseyNumber =
                            it.filter { char ->
                                char.isDigit()
                            }
                    },

                    label = {
                        Text("Jersey Number")
                    },

                    modifier =
                        Modifier.fillMaxWidth(),

                    shape =
                        RoundedCornerShape(14.dp)
                )

                Spacer(
                    modifier =
                        Modifier.height(12.dp)
                )

                ExposedDropdownMenuBox(
                    expanded = expanded,

                    onExpandedChange = {
                        expanded = !expanded
                    }
                ) {

                    OutlinedTextField(
                        value = selectedRole,

                        onValueChange = {},

                        readOnly = true,

                        label = {
                            Text("Select Role")
                        },

                        trailingIcon = {

                            ExposedDropdownMenuDefaults
                                .TrailingIcon(
                                    expanded = expanded
                                )
                        },

                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .menuAnchor(),

                        shape =
                            RoundedCornerShape(14.dp)
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,

                        onDismissRequest = {
                            expanded = false
                        }
                    ) {

                        roles.forEach { role ->

                            DropdownMenuItem(
                                text = {
                                    Text(role)
                                },

                                onClick = {

                                    selectedRole = role

                                    expanded = false
                                }
                            )
                        }
                    }
                }

                Spacer(
                    modifier =
                        Modifier.height(24.dp)
                )

                Button(

                    onClick = {

                        if (
                            playerName.isBlank() ||
                            jerseyNumber.isBlank() ||
                            selectedRole.isBlank()
                        ) {

                            Toast.makeText(

                                navController.context,

                                "Fill all fields",

                                Toast.LENGTH_SHORT
                            ).show()

                            return@Button
                        }

                        if (
                            currentPlayers.size >= maxPlayers
                        ) {

                            Toast.makeText(

                                navController.context,

                                "Maximum players reached for $sportType",

                                Toast.LENGTH_LONG
                            ).show()

                            return@Button
                        }

                        val jersey =
                            jerseyNumber.toIntOrNull()

                        if (jersey == null) {

                            Toast.makeText(

                                navController.context,

                                "Invalid jersey number",

                                Toast.LENGTH_SHORT
                            ).show()

                            return@Button
                        }

                        val duplicateJersey =

                            currentPlayers.any {

                                it.jerseyNumber == jersey
                            }

                        if (duplicateJersey) {

                            Toast.makeText(

                                navController.context,

                                "Jersey already exists",

                                Toast.LENGTH_SHORT
                            ).show()

                            return@Button
                        }

                        val player = Player(

                            playerId =
                                UUID.randomUUID().toString(),

                            playerName =
                                playerName,

                            jerseyNumber =
                                jersey,

                            role =
                                selectedRole,

                            teamId =
                                teamId,

                            tournamentId =
                                tournamentId,

                            sportType =
                                sportType
                        )

                        playerViewModel
                            .createPlayer(player)

                        teamViewModel.updatePlayerCount(

                            teamId,

                            currentPlayers.size + 1
                        )

                        Toast.makeText(

                            navController.context,

                            "Player Added",

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
                        text = "SAVE PLAYER"
                    )
                }
            }
        }
    }
}