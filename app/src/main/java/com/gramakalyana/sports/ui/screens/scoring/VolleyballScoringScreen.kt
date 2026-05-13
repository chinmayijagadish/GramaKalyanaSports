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
import com.gramakalyana.sports.viewmodel.VolleyballLiveViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VolleyballScoringScreen(

    navController: NavController,

    matchId: String?
) {

    if (matchId == null) return

    val volleyballViewModel:
            VolleyballLiveViewModel =
        viewModel()

    LaunchedEffect(Unit) {

        volleyballViewModel.observeLiveMatch(
            matchId
        )
    }

    val liveData by
    volleyballViewModel.liveMatch.collectAsState()

    Scaffold(

        topBar = {

            TopAppBar(

                title = {

                    Text(
                        text = "Volleyball Scoring",
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
                            Arrangement.spacedBy(14.dp)
                    ) {

                        Text(

                            text =
                                "${liveData.teamAName} ${liveData.teamAPoints} - ${liveData.teamBPoints} ${liveData.teamBName}",

                            style =
                                MaterialTheme.typography.headlineSmall,

                            fontWeight =
                                FontWeight.ExtraBold
                        )

                        Text(
                            text =
                                "Set ${liveData.currentSet}"
                        )

                        Text(
                            text =
                                "Sets: ${liveData.teamASets} - ${liveData.teamBSets}"
                        )

                        Text(
                            text =
                                "Serving Team: ${liveData.servingTeam}"
                        )

                        if (liveData.matchPoint) {

                            Text(

                                text =
                                    "MATCH POINT",

                                color =
                                    MaterialTheme.colorScheme.error,

                                fontWeight =
                                    FontWeight.Bold
                            )
                        }
                    }
                }
            }

            item {

                Text(
                    text = "Recent Points",
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

                    liveData.recentPoints.forEach {

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

                Row(
                    horizontalArrangement =
                        Arrangement.spacedBy(10.dp)
                ) {

                    VolleyballButton(
                        text = "+1 Team A"
                    ) {

                        var updated =

                            liveData.copy(

                                teamAPoints =
                                    liveData.teamAPoints + 1,

                                servingTeam = "A",

                                recentPoints =
                                    (
                                            liveData.recentPoints +
                                                    "A +1"
                                            ).takeLast(10)
                            )

                        if (

                            updated.teamAPoints >= 25 &&

                            updated.teamAPoints -
                            updated.teamBPoints >= 2
                        ) {

                            updated =

                                updated.copy(

                                    teamASets =
                                        updated.teamASets + 1,

                                    setResults =
                                        (
                                                updated.setResults +
                                                        "${updated.teamAPoints}-${updated.teamBPoints}"
                                                ),

                                    currentSet =
                                        updated.currentSet + 1,

                                    teamAPoints = 0,

                                    teamBPoints = 0
                                )
                        }

                        if (updated.teamASets == 3) {

                            volleyballViewModel
                                .finishMatch(updated)

                        } else {

                            volleyballViewModel
                                .updateLiveMatch(updated)
                        }
                    }

                    VolleyballButton(
                        text = "+1 Team B"
                    ) {

                        var updated =

                            liveData.copy(

                                teamBPoints =
                                    liveData.teamBPoints + 1,

                                servingTeam = "B",

                                recentPoints =
                                    (
                                            liveData.recentPoints +
                                                    "B +1"
                                            ).takeLast(10)
                            )

                        if (

                            updated.teamBPoints >= 25 &&

                            updated.teamBPoints -
                            updated.teamAPoints >= 2
                        ) {

                            updated =

                                updated.copy(

                                    teamBSets =
                                        updated.teamBSets + 1,

                                    setResults =
                                        (
                                                updated.setResults +
                                                        "${updated.teamAPoints}-${updated.teamBPoints}"
                                                ),

                                    currentSet =
                                        updated.currentSet + 1,

                                    teamAPoints = 0,

                                    teamBPoints = 0
                                )
                        }

                        if (updated.teamBSets == 3) {

                            volleyballViewModel
                                .finishMatch(updated)

                        } else {

                            volleyballViewModel
                                .updateLiveMatch(updated)
                        }
                    }
                }
            }

            item {

                Row(
                    horizontalArrangement =
                        Arrangement.spacedBy(10.dp)
                ) {

                    VolleyballButton(
                        text = "Timeout A"
                    ) {

                        if (liveData.timeoutA > 0) {

                            volleyballViewModel
                                .updateLiveMatch(

                                    liveData.copy(

                                        timeoutA =
                                            liveData.timeoutA - 1
                                    )
                                )
                        }
                    }

                    VolleyballButton(
                        text = "Timeout B"
                    ) {

                        if (liveData.timeoutB > 0) {

                            volleyballViewModel
                                .updateLiveMatch(

                                    liveData.copy(

                                        timeoutB =
                                            liveData.timeoutB - 1
                                    )
                                )
                        }
                    }
                }
            }

            item {

                Button(

                    onClick = {

                        volleyballViewModel
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
fun VolleyballButton(
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