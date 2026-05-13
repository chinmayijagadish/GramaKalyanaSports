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
import com.gramakalyana.sports.viewmodel.KabaddiLiveViewModel
import com.gramakalyana.sports.viewmodel.MatchViewModel

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

    val matchViewModel:
            MatchViewModel =
        viewModel()

    LaunchedEffect(Unit) {

        kabaddiViewModel.observeLiveMatch(
            matchId
        )

        matchViewModel.updateMatchStatus(
            matchId,
            "LIVE"
        )
    }

    val liveData by
    kabaddiViewModel.liveMatch.collectAsState()

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
                                "Kabaddi Scoring",

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

                        KabaddiButton(

                            text = "Raid +1",

                            modifier =
                                Modifier.weight(1f)

                        ) {

                            var updated =
                                liveData

                            if (
                                updated.currentRaidingTeam == "A"
                            ) {

                                updated =
                                    updated.copy(

                                        teamAScore =
                                            updated.teamAScore + 1,

                                        touchPointsA =
                                            updated.touchPointsA + 1,

                                        currentRaidingTeam = "B",

                                        recentRaids =
                                            (
                                                    updated.recentRaids +
                                                            "${updated.teamAName} Raid +1"
                                                    ).takeLast(12)
                                    )
                            }

                            else {

                                updated =
                                    updated.copy(

                                        teamBScore =
                                            updated.teamBScore + 1,

                                        touchPointsB =
                                            updated.touchPointsB + 1,

                                        currentRaidingTeam = "A",

                                        recentRaids =
                                            (
                                                    updated.recentRaids +
                                                            "${updated.teamBName} Raid +1"
                                                    ).takeLast(12)
                                    )
                            }

                            kabaddiViewModel
                                .updateLiveMatch(updated)
                        }

                        KabaddiButton(

                            text = "Tackle +1",

                            modifier =
                                Modifier.weight(1f)

                        ) {

                            var updated =
                                liveData

                            if (
                                updated.currentRaidingTeam == "A"
                            ) {

                                updated =
                                    updated.copy(

                                        teamBScore =
                                            updated.teamBScore + 1,

                                        tacklePointsB =
                                            updated.tacklePointsB + 1,

                                        currentRaidingTeam = "B",

                                        recentRaids =
                                            (
                                                    updated.recentRaids +
                                                            "${updated.teamBName} Tackle +1"
                                                    ).takeLast(12)
                                    )
                            }

                            else {

                                updated =
                                    updated.copy(

                                        teamAScore =
                                            updated.teamAScore + 1,

                                        tacklePointsA =
                                            updated.tacklePointsA + 1,

                                        currentRaidingTeam = "A",

                                        recentRaids =
                                            (
                                                    updated.recentRaids +
                                                            "${updated.teamAName} Tackle +1"
                                                    ).takeLast(12)
                                    )
                            }

                            kabaddiViewModel
                                .updateLiveMatch(updated)
                        }
                    }

                    Row(
                        modifier =
                            Modifier.fillMaxWidth(),

                        horizontalArrangement =
                            Arrangement.spacedBy(12.dp)
                    ) {

                        KabaddiButton(

                            text = "Super Raid +3",

                            modifier =
                                Modifier.weight(1f)

                        ) {

                            var updated =
                                liveData

                            if (
                                updated.currentRaidingTeam == "A"
                            ) {

                                updated =
                                    updated.copy(

                                        teamAScore =
                                            updated.teamAScore + 3,

                                        superRaid = true,

                                        currentRaidingTeam = "B",

                                        recentRaids =
                                            (
                                                    updated.recentRaids +
                                                            "${updated.teamAName} Super Raid +3"
                                                    ).takeLast(12)
                                    )
                            }

                            else {

                                updated =
                                    updated.copy(

                                        teamBScore =
                                            updated.teamBScore + 3,

                                        superRaid = true,

                                        currentRaidingTeam = "A",

                                        recentRaids =
                                            (
                                                    updated.recentRaids +
                                                            "${updated.teamBName} Super Raid +3"
                                                    ).takeLast(12)
                                    )
                            }

                            kabaddiViewModel
                                .updateLiveMatch(updated)
                        }

                        KabaddiButton(

                            text = "Empty Raid",

                            modifier =
                                Modifier.weight(1f)

                        ) {

                            kabaddiViewModel
                                .updateLiveMatch(

                                    liveData.copy(

                                        currentRaidingTeam =

                                            if (
                                                liveData.currentRaidingTeam == "A"
                                            )
                                                "B"

                                            else
                                                "A",

                                        recentRaids =
                                            (
                                                    liveData.recentRaids +
                                                            "Empty Raid"
                                                    ).takeLast(12)
                                    )
                                )
                        }
                    }

                    Row(
                        modifier =
                            Modifier.fillMaxWidth(),

                        horizontalArrangement =
                            Arrangement.spacedBy(12.dp)
                    ) {

                        if (liveData.currentHalf == 1) {

                            KabaddiButton(

                                text = "START 2ND HALF",

                                modifier =
                                    Modifier.weight(1f)

                            ) {

                                kabaddiViewModel
                                    .updateLiveMatch(

                                        liveData.copy(
                                            currentHalf = 2
                                        )
                                    )
                            }
                        }

                        else {

                            KabaddiButton(

                                text = "FINISH MATCH",

                                modifier =
                                    Modifier.weight(1f)

                            ) {

                                kabaddiViewModel
                                    .finishMatch(liveData)

                                val winner =

                                    when {

                                        liveData.teamAScore >
                                                liveData.teamBScore ->

                                            liveData.teamAName

                                        liveData.teamBScore >
                                                liveData.teamAScore ->

                                            liveData.teamBName

                                        else ->
                                            "DRAW"
                                    }

                                matchViewModel
                                    .finishMatch(

                                        liveData.matchId,

                                        winner
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
                                "LIVE • ${liveData.currentRaidingTeam} RAIDING",

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
                            Modifier.padding(20.dp)
                    ) {

                        Text(

                            text =
                                if (liveData.currentHalf == 1)
                                    "FIRST HALF"

                                else
                                    "SECOND HALF",

                            style =
                                MaterialTheme.typography.titleLarge,

                            fontWeight =
                                FontWeight.ExtraBold
                        )

                        Spacer(
                            modifier =
                                Modifier.height(18.dp)
                        )

                        TeamKabaddiCard(

                            teamName =
                                liveData.teamAName,

                            score =
                                liveData.teamAScore,

                            touchPoints =
                                liveData.touchPointsA,

                            tacklePoints =
                                liveData.tacklePointsA
                        )

                        Spacer(
                            modifier =
                                Modifier.height(14.dp)
                        )

                        TeamKabaddiCard(

                            teamName =
                                liveData.teamBName,

                            score =
                                liveData.teamBScore,

                            touchPoints =
                                liveData.touchPointsB,

                            tacklePoints =
                                liveData.tacklePointsB
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
                                "Recent Raids",

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
                        Modifier.height(150.dp)
                )
            }
        }
    }
}

@Composable
fun TeamKabaddiCard(

    teamName: String,

    score: Int,

    touchPoints: Int,

    tacklePoints: Int
) {

    Card {

        Column(
            modifier =
                Modifier.padding(16.dp)
        ) {

            Row(
                modifier =
                    Modifier.fillMaxWidth(),

                horizontalArrangement =
                    Arrangement.SpaceBetween
            ) {

                Text(

                    text =
                        teamName,

                    fontWeight =
                        FontWeight.Bold
                )

                Text(

                    text =
                        score.toString(),

                    style =
                        MaterialTheme.typography.displaySmall,

                    fontWeight =
                        FontWeight.ExtraBold
                )
            }

            Spacer(
                modifier =
                    Modifier.height(10.dp)
            )

            Text(
                text =
                    "Raid Points: $touchPoints"
            )

            Text(
                text =
                    "Tackle Points: $tacklePoints"
            )
        }
    }
}

@Composable
fun KabaddiButton(

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