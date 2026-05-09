package com.gramakalyana.sports.ui.screens.player

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamPlayersScreen(

    teamId: String
) {

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

        Column(
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
                        text = "Manage Players",

                        style =
                            MaterialTheme.typography.titleLarge,

                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Team ID:"
                    )

                    Text(
                        text = teamId
                    )
                }
            }

            Button(
                onClick = {

                },

                modifier = Modifier.fillMaxWidth(),

                shape = RoundedCornerShape(14.dp)
            ) {

                Text(
                    text = "ADD PLAYER"
                )
            }
        }
    }
}