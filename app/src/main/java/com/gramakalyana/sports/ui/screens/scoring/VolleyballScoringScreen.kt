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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.gramakalyana.sports.ui.components.GlassmorphismCard
import com.gramakalyana.sports.viewmodel.MatchViewModel
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

    val matchViewModel:
            MatchViewModel =
        viewModel()

    LaunchedEffect(Unit) {

        volleyballViewModel.observeLiveMatch(
            matchId
        )

        matchViewModel.updateMatchStatus(
            matchId,
            "LIVE"
        )
    }

    val liveData by
    volleyballViewModel.liveMatch.collectAsState()

    Scaffold(

        topBar = {

            TopAppBar(

                title = {

                    Row(
                        verticalAlignment =
                            Alignment.CenterVertically
                    ) {

                        Icon(
                            imageVector =
                                Icons.Default.PlayArrow,

                            contentDescription = null
                        )

                        Spacer(
                            modifier =
                                Modifier.width(8.dp)
                        )

                        Text(

                            text =
                                "Volleyball Scoring",

                            fontWeight =
                                FontWeight.Bold
                        )
                    }
                },

                navigationIcon = {

                    IconButton(
                        onClick = {

                            navController.popBackStack()
                        }
                    ) {

                        Icon(
                            imageVector =
                                Icons.AutoMirrored.Filled.ArrowBack,

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
        },

        bottomBar = {

            if (!liveData.matchCompleted) {

                Column(

                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            MaterialTheme.colorScheme.background
                        )
                        .padding(16.dp),

                    verticalArrangement =
                        Arrangement.spacedBy(12.dp)
                ) {

                    Row(
                        modifier =
                            Modifier.fillMaxWidth(),

                        horizontalArrangement =
                            Arrangement.spacedBy(12.dp)
                    ) {

                        VolleyballButton(

                            text =
                                "${liveData.teamAName} +1",

                            modifier =
                                Modifier.weight(1f)

                        ) {

                            var updated =

                                liveData.copy(

                                    teamAPoints =
                                        liveData.teamAPoints + 1,

                                    servingTeam =
                                        liveData.teamAName,

                                    recentPoints =
                                        (
                                                liveData.recentPoints +
                                                        "${liveData.teamAName} +1"
                                                ).takeLast(12)
                                )

                            val setWon =

                                updated.teamAPoints >= 25 &&
                                        updated.teamAPoints -
                                        updated.teamBPoints >= 2

                            if (setWon) {

                                updated = updated.copy(

                                    teamASets =
                                        updated.teamASets + 1,

                                    setResults =
                                        updated.setResults +
                                                "Set ${updated.currentSet}: ${updated.teamAPoints}-${updated.teamBPoints}",

                                    currentSet =
                                        updated.currentSet + 1,

                                    teamAPoints = 0,

                                    teamBPoints = 0
                                )
                            }

                            if (updated.teamASets >= 3) {

                                updated = updated.copy(

                                    winner =
                                        updated.teamAName,

                                    resultText =
                                        "${updated.teamAName} won ${updated.teamASets}-${updated.teamBSets}",

                                    matchCompleted = true,

                                    matchStatus = "COMPLETED"
                                )

                                matchViewModel.finishMatch(

                                    updated.matchId,

                                    updated.teamAName
                                )
                            }

                            volleyballViewModel
                                .updateLiveMatch(updated)
                        }

                        VolleyballButton(

                            text =
                                "${liveData.teamBName} +1",

                            modifier =
                                Modifier.weight(1f)

                        ) {

                            var updated =

                                liveData.copy(

                                    teamBPoints =
                                        liveData.teamBPoints + 1,

                                    servingTeam =
                                        liveData.teamBName,

                                    recentPoints =
                                        (
                                                liveData.recentPoints +
                                                        "${liveData.teamBName} +1"
                                                ).takeLast(12)
                                )

                            val setWon =

                                updated.teamBPoints >= 25 &&
                                        updated.teamBPoints -
                                        updated.teamAPoints >= 2

                            if (setWon) {

                                updated = updated.copy(

                                    teamBSets =
                                        updated.teamBSets + 1,

                                    setResults =
                                        updated.setResults +
                                                "Set ${updated.currentSet}: ${updated.teamAPoints}-${updated.teamBPoints}",

                                    currentSet =
                                        updated.currentSet + 1,

                                    teamAPoints = 0,

                                    teamBPoints = 0
                                )
                            }

                            if (updated.teamBSets >= 3) {

                                updated = updated.copy(

                                    winner =
                                        updated.teamBName,

                                    resultText =
                                        "${updated.teamBName} won ${updated.teamBSets}-${updated.teamASets}",

                                    matchCompleted = true,

                                    matchStatus = "COMPLETED"
                                )

                                matchViewModel.finishMatch(

                                    updated.matchId,

                                    updated.teamBName
                                )
                            }

                            volleyballViewModel
                                .updateLiveMatch(updated)
                        }
                    }

                    Row(
                        modifier =
                            Modifier.fillMaxWidth(),

                        horizontalArrangement =
                            Arrangement.spacedBy(12.dp)
                    ) {

                        VolleyballButton(

                            text =
                                "${liveData.teamAName} Timeout",

                            modifier =
                                Modifier.weight(1f)

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

                            text =
                                "${liveData.teamBName} Timeout",

                            modifier =
                                Modifier.weight(1f)

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
            }
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

                Card(

                    modifier =
                        Modifier.fillMaxWidth(),

                    colors =
                        CardDefaults.cardColors(

                            containerColor =

                                if (
                                    liveData.matchCompleted
                                )
                                    Color.Gray

                                else
                                    Color(0xFFD32F2F)
                        )
                ) {

                    Text(

                        text =

                            if (
                                liveData.matchCompleted
                            )
                                "MATCH COMPLETED"

                            else
                                "LIVE • ${liveData.servingTeam} SERVING",

                        color =
                            Color.White,

                        modifier =
                            Modifier.padding(14.dp),

                        fontWeight =
                            FontWeight.Bold
                    )
                }
            }

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
                                "SET ${liveData.currentSet}",

                            style =
                                MaterialTheme.typography.titleLarge,

                            fontWeight =
                                FontWeight.ExtraBold
                        )

                        TeamScoreCard(

                            teamName =
                                liveData.teamAName,

                            sets =
                                liveData.teamASets,

                            points =
                                liveData.teamAPoints
                        )

                        TeamScoreCard(

                            teamName =
                                liveData.teamBName,

                            sets =
                                liveData.teamBSets,

                            points =
                                liveData.teamBPoints
                        )
                    }
                }
            }

            item {

                GlassmorphismCard(

                    modifier =
                        Modifier.fillMaxWidth()
                ) {

                    Column(
                        modifier =
                            Modifier.padding(18.dp)
                    ) {

                        Text(

                            text =
                                "Recent Points",

                            fontWeight =
                                FontWeight.Bold
                        )

                        Spacer(
                            modifier =
                                Modifier.height(12.dp)
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
                }
            }

            if (
                liveData.setResults.isNotEmpty()
            ) {

                item {

                    GlassmorphismCard(

                        modifier =
                            Modifier.fillMaxWidth()
                    ) {

                        Column(

                            modifier =
                                Modifier.padding(18.dp),

                            verticalArrangement =
                                Arrangement.spacedBy(10.dp)
                        ) {

                            Text(

                                text =
                                    "Set Results",

                                fontWeight =
                                    FontWeight.Bold
                            )

                            liveData.setResults.forEach {

                                Text(text = it)
                            }
                        }
                    }
                }
            }

            if (
                liveData.matchCompleted
            ) {

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
                                Modifier.padding(20.dp),

                            horizontalAlignment =
                                Alignment.CenterHorizontally
                        ) {

                            Text(

                                text =
                                    "MATCH RESULT",

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
                        Modifier.height(120.dp)
                )
            }
        }
    }
}

@Composable
fun TeamScoreCard(

    teamName: String,

    sets: Int,

    points: Int
) {

    Card {

        Row(

            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),

            horizontalArrangement =
                Arrangement.SpaceBetween,

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Column {

                Text(

                    text =
                        teamName,

                    fontWeight =
                        FontWeight.Bold
                )

                Text(
                    text =
                        "Sets: $sets"
                )
            }

            Text(

                text =
                    points.toString(),

                style =
                    MaterialTheme.typography.displaySmall,

                fontWeight =
                    FontWeight.ExtraBold
            )
        }
    }
}

@Composable
fun VolleyballButton(

    text: String,

    modifier: Modifier =
        Modifier,

    onClick: () -> Unit
) {

    Button(

        onClick = onClick,

        modifier =
            modifier.height(58.dp),

        shape =
            RoundedCornerShape(14.dp)
    ) {

        Text(

            text = text,

            fontWeight =
                FontWeight.Bold
        )
    }
}