package com.gramakalyana.sports.ui.screens.player

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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

    val roles = when (sportType) {

        "Cricket" -> listOf(
            "Batsman",
            "Bowler",
            "All Rounder",
            "Wicket Keeper"
        )

        "Kabaddi" -> listOf(
            "Raider",
            "Defender",
            "All Rounder"
        )

        else -> listOf(
            "Setter",
            "Spiker",
            "Libero",
            "Blocker"
        )
    }

    Scaffold(

        topBar = {

            TopAppBar(

                title = {

                    Text(
                        text = "Add Player",

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
                        jerseyNumber = it
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

                        val player = Player(

                            playerId =
                                UUID.randomUUID().toString(),

                            playerName =
                                playerName,

                            jerseyNumber =
                                jerseyNumber.toInt(),

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