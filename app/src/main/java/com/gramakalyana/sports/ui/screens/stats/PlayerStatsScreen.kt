package com.gramakalyana.sports.ui.screens.stats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gramakalyana.sports.viewmodel.PlayerStatsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerStatsScreen(
    playerId: String
) {

    val statsViewModel:
            PlayerStatsViewModel =
        viewModel()

    LaunchedEffect(Unit) {

        statsViewModel.observePlayerStats(
            playerId
        )
    }

    val stats by
    statsViewModel.playerStats.collectAsState()

    Scaffold(

        topBar = {

            TopAppBar(

                title = {

                    Text(
                        text = "Player Stats",

                        fontWeight = FontWeight.Bold
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
                .background(
                    MaterialTheme.colorScheme.background
                )
                .padding(paddingValues)
                .padding(16.dp),

            verticalArrangement =
                Arrangement.spacedBy(16.dp)
        ) {

            item {

                Card(
                    modifier =
                        Modifier.fillMaxWidth(),

                    shape =
                        RoundedCornerShape(20.dp),

                    colors =
                        CardDefaults.cardColors(

                            containerColor =
                                MaterialTheme.colorScheme.primaryContainer
                        )
                ) {

                    Column(
                        modifier =
                            Modifier.padding(20.dp),

                        verticalArrangement =
                            Arrangement.spacedBy(10.dp)
                    ) {

                        Text(

                            text =
                                stats.playerName,

                            style =
                                MaterialTheme.typography.headlineSmall,

                            fontWeight =
                                FontWeight.Bold
                        )

                        Text(
                            text =
                                "Matches: ${stats.matchesPlayed}"
                        )

                        Text(
                            text =
                                "Runs: ${stats.totalRuns}"
                        )

                        Text(
                            text =
                                "Wickets: ${stats.totalWickets}"
                        )

                        Text(
                            text =
                                "4s: ${stats.totalFours}"
                        )

                        Text(
                            text =
                                "6s: ${stats.totalSixes}"
                        )

                        Text(
                            text =
                                "Raid Points: ${stats.totalRaidPoints}"
                        )

                        Text(
                            text =
                                "Tackle Points: ${stats.totalTacklePoints}"
                        )

                        Text(
                            text =
                                "Smashes: ${stats.totalSmashes}"
                        )

                        Text(
                            text =
                                "Blocks: ${stats.totalBlocks}"
                        )

                        Text(
                            text =
                                "MVP Awards: ${stats.mvpAwards}"
                        )
                    }
                }
            }
        }
    }
}