package com.gramakalyana.sports.ui.screens.setup

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
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.gramakalyana.sports.data.model.Team
import com.gramakalyana.sports.viewmodel.TeamViewModel
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTeamScreen(

    navController: NavController,

    selectedSport: String,

    selectedZone: String
) {

    var teamName by remember {
        mutableStateOf("")
    }

    val teamViewModel: TeamViewModel =
        viewModel()

    Scaffold(

        topBar = {

            TopAppBar(

                title = {

                    Text(
                        text = "Add Team",

                        fontWeight = FontWeight.Bold
                    )
                },

                navigationIcon = {

                    IconButton(
                        onClick = {
                            navController.navigateUp()
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
                    text = "Team Information",

                    style =
                        MaterialTheme.typography.titleLarge,

                    color =
                        MaterialTheme.colorScheme.primary,

                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Sport: $selectedSport",

                    style =
                        MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = "Zone: $selectedZone",

                    style =
                        MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = teamName,

                    onValueChange = {
                        teamName = it
                    },

                    label = {
                        Text("Team Name")
                    },

                    modifier = Modifier.fillMaxWidth(),

                    shape = RoundedCornerShape(14.dp)
                )
            }

            item {

                Spacer(modifier = Modifier.height(24.dp))

                Button(

                    onClick = {

                        if (teamName.isBlank()) {

                            Toast.makeText(
                                navController.context,

                                "Enter team name",

                                Toast.LENGTH_SHORT
                            ).show()

                            return@Button
                        }

                        val team = Team(

                            teamId =
                                UUID.randomUUID().toString(),

                            teamName = teamName,

                            sportType = selectedSport,

                            zone = selectedZone,

                            playerCount = 0
                        )

                        teamViewModel.createTeam(team)

                        Toast.makeText(
                            navController.context,

                            "Team Added Successfully",

                            Toast.LENGTH_SHORT
                        ).show()

                        teamName = ""

                        navController.popBackStack()
                    },

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(58.dp),

                    shape = RoundedCornerShape(14.dp)
                ) {

                    Text(
                        text = "SAVE TEAM",

                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}