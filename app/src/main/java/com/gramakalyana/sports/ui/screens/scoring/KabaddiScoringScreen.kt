package com.gramakalyana.sports.ui.screens.scoring

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.gramakalyana.sports.ui.components.GlassmorphismCard
import com.gramakalyana.sports.viewmodel.KabaddiLiveViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KabaddiScoringScreen(

    navController: NavController,

    matchId: String?
) {

    if (matchId == null) return

    val kabaddiViewModel:
            KabaddiLiveViewModel =
        viewModel()

    LaunchedEffect(Unit) {

        kabaddiViewModel.observeLiveMatch(
            matchId
        )
    }

    val liveData by
    kabaddiViewModel.liveMatch.collectAsState()

    Scaffold(

        topBar = {

            TopAppBar(

                title = {

                    Text(
                        text = "Kabaddi Scoring",
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
                            contentDescription = null
                        )
                    }
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
                Arrangement.spacedBy(18.dp)
        ) {

            item {

                GlassmorphismCard(
                    modifier =
                        Modifier.fillMaxWidth()
                ) {

                    Column(
                        modifier =
                            Modifier.padding(20.dp),

                        verticalArrangement =
                            Arrangement.spacedBy(12.dp)
                    ) {

                        Text(

                            text =
                                "${liveData.teamAName} ${liveData.teamAScore} - ${liveData.teamBScore} ${liveData.teamBName}",

                            style =
                                MaterialTheme.typography.headlineSmall,

                            fontWeight =
                                FontWeight.ExtraBold
                        )

                        Text(
                            text =
                                "Half: ${liveData.currentHalf}"
                        )

                        Text(
                            text =
                                "Raid Team: ${liveData.currentRaidingTeam}"
                        )

                        Text(
                            text =
                                "Players On Mat: ${liveData.teamAPlayersOnMat} - ${liveData.teamBPlayersOnMat}"
                        )
                    }
                }
            }

            item {

                Text(
                    text = "Raid History",
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier =
                        Modifier.height(8.dp)
                )

                FlowRow(

                    horizontalArrangement =
                        Arrangement.spacedBy(8.dp),

                    verticalArrangement =
                        Arrangement.spacedBy(8.dp)
                ) {

                    liveData.recentRaids.forEach {

                        Card(

                            colors =
                                CardDefaults.cardColors(

                                    containerColor =
                                        MaterialTheme.colorScheme.primaryContainer
                                )
                        ) {

                            Text(

                                text = it,

                                modifier =
                                    Modifier.padding(
                                        horizontal = 14.dp,
                                        vertical = 10.dp
                                    )
                            )
                        }
                    }
                }
            }

            item {

                Text(
                    text = "Team A Controls",
                    fontWeight = FontWeight.Bold
                )
            }

            item {

                Row(
                    horizontalArrangement =
                        Arrangement.spacedBy(10.dp)
                ) {

                    KabaddiButton(
                        text = "Touch +1"
                    ) {

                        val updated =

                            liveData.copy(

                                teamAScore =
                                    liveData.teamAScore + 1,

                                touchPointsA =
                                    liveData.touchPointsA + 1,

                                teamBPlayersOnMat =
                                    maxOf(
                                        0,
                                        liveData.teamBPlayersOnMat - 1
                                    ),

                                recentRaids =
                                    (
                                            liveData.recentRaids +
                                                    "A Touch +1"
                                            ).takeLast(10)
                            )

                        kabaddiViewModel
                            .updateLiveMatch(updated)
                    }

                    KabaddiButton(
                        text = "Bonus +1"
                    ) {

                        val updated =

                            liveData.copy(

                                teamAScore =
                                    liveData.teamAScore + 1,

                                bonusPointsA =
                                    liveData.bonusPointsA + 1,

                                recentRaids =
                                    (
                                            liveData.recentRaids +
                                                    "A Bonus +1"
                                            ).takeLast(10)
                            )

                        kabaddiViewModel
                            .updateLiveMatch(updated)
                    }
                }
            }

            item {

                Row(
                    horizontalArrangement =
                        Arrangement.spacedBy(10.dp)
                ) {

                    KabaddiButton(
                        text = "Super Raid +3"
                    ) {

                        val updated =

                            liveData.copy(

                                teamAScore =
                                    liveData.teamAScore + 3,

                                superRaid = true,

                                teamBPlayersOnMat =
                                    maxOf(
                                        0,
                                        liveData.teamBPlayersOnMat - 3
                                    ),

                                recentRaids =
                                    (
                                            liveData.recentRaids +
                                                    "A Super Raid +3"
                                            ).takeLast(10)
                            )

                        kabaddiViewModel
                            .updateLiveMatch(updated)
                    }

                    KabaddiButton(
                        text = "All Out +2"
                    ) {

                        val updated =

                            liveData.copy(

                                teamAScore =
                                    liveData.teamAScore + 2,

                                allOutCountA =
                                    liveData.allOutCountA + 1,

                                teamBPlayersOnMat = 7,

                                recentRaids =
                                    (
                                            liveData.recentRaids +
                                                    "A All Out +2"
                                            ).takeLast(10)
                            )

                        kabaddiViewModel
                            .updateLiveMatch(updated)
                    }
                }
            }

            item {

                Text(
                    text = "Team B Controls",
                    fontWeight = FontWeight.Bold
                )
            }

            item {

                Row(
                    horizontalArrangement =
                        Arrangement.spacedBy(10.dp)
                ) {

                    KabaddiButton(
                        text = "Touch +1"
                    ) {

                        val updated =

                            liveData.copy(

                                teamBScore =
                                    liveData.teamBScore + 1,

                                touchPointsB =
                                    liveData.touchPointsB + 1,

                                teamAPlayersOnMat =
                                    maxOf(
                                        0,
                                        liveData.teamAPlayersOnMat - 1
                                    ),

                                recentRaids =
                                    (
                                            liveData.recentRaids +
                                                    "B Touch +1"
                                            ).takeLast(10)
                            )

                        kabaddiViewModel
                            .updateLiveMatch(updated)
                    }

                    KabaddiButton(
                        text = "Bonus +1"
                    ) {

                        val updated =

                            liveData.copy(

                                teamBScore =
                                    liveData.teamBScore + 1,

                                bonusPointsB =
                                    liveData.bonusPointsB + 1,

                                recentRaids =
                                    (
                                            liveData.recentRaids +
                                                    "B Bonus +1"
                                            ).takeLast(10)
                            )

                        kabaddiViewModel
                            .updateLiveMatch(updated)
                    }
                }
            }

            item {

                Row(
                    horizontalArrangement =
                        Arrangement.spacedBy(10.dp)
                ) {

                    KabaddiButton(
                        text = "Super Raid +3"
                    ) {

                        val updated =

                            liveData.copy(

                                teamBScore =
                                    liveData.teamBScore + 3,

                                superRaid = true,

                                teamAPlayersOnMat =
                                    maxOf(
                                        0,
                                        liveData.teamAPlayersOnMat - 3
                                    ),

                                recentRaids =
                                    (
                                            liveData.recentRaids +
                                                    "B Super Raid +3"
                                            ).takeLast(10)
                            )

                        kabaddiViewModel
                            .updateLiveMatch(updated)
                    }

                    KabaddiButton(
                        text = "All Out +2"
                    ) {

                        val updated =

                            liveData.copy(

                                teamBScore =
                                    liveData.teamBScore + 2,

                                allOutCountB =
                                    liveData.allOutCountB + 1,

                                teamAPlayersOnMat = 7,

                                recentRaids =
                                    (
                                            liveData.recentRaids +
                                                    "B All Out +2"
                                            ).takeLast(10)
                            )

                        kabaddiViewModel
                            .updateLiveMatch(updated)
                    }
                }
            }

            item {

                Spacer(
                    modifier =
                        Modifier.height(10.dp)
                )

                Button(

                    onClick = {

                        kabaddiViewModel
                            .finishMatch(liveData)
                    },

                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(56.dp),

                    shape =
                        RoundedCornerShape(14.dp),

                    colors =
                        ButtonDefaults.buttonColors(

                            containerColor =
                                MaterialTheme.colorScheme.primary
                        )
                ) {

                    Text(
                        text = "FINISH MATCH",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            if (liveData.matchCompleted) {

                item {

                    Card(

                        modifier =
                            Modifier.fillMaxWidth(),

                        shape =
                            RoundedCornerShape(18.dp),

                        colors =
                            CardDefaults.cardColors(

                                containerColor =
                                    Color(0xFF1B5E20)
                            )
                    ) {

                        Column(
                            modifier =
                                Modifier.padding(20.dp)
                        ) {

                            Text(

                                text =
                                    "RESULT",

                                color =
                                    Color.White,

                                fontWeight =
                                    FontWeight.Bold
                            )

                            Spacer(
                                modifier =
                                    Modifier.height(10.dp)
                            )

                            Text(

                                text =
                                    liveData.resultText,

                                color =
                                    Color.White,

                                style =
                                    MaterialTheme.typography.titleLarge,

                                fontWeight =
                                    FontWeight.ExtraBold
                            )
                        }
                    }
                }
            }

            item {

                Spacer(
                    modifier =
                        Modifier.height(100.dp)
                )
            }
        }
    }
}

@Composable
fun KabaddiButton(
    text: String,
    onClick: () -> Unit
) {

    Button(

        onClick = onClick,

        modifier =
            Modifier.fillMaxWidth(),

        shape =
            RoundedCornerShape(12.dp)
    ) {

        Text(text)
    }
}