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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.gramakalyana.sports.data.model.CricketLiveData
import com.gramakalyana.sports.ui.components.GlassmorphismCard
import com.gramakalyana.sports.viewmodel.CricketLiveViewModel
import com.gramakalyana.sports.viewmodel.MatchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CricketScoringScreen(

    navController: NavController,

    matchId: String?
) {

    if (matchId == null) return

    val cricketViewModel:
            CricketLiveViewModel =
        viewModel()

    val matchViewModel:
            MatchViewModel =
        viewModel()

    LaunchedEffect(Unit) {

        cricketViewModel.observeLiveMatch(
            matchId
        )

        matchViewModel.updateMatchStatus(
            matchId,
            "LIVE"
        )
    }

    val liveData by
    cricketViewModel.liveMatch.collectAsState()

    fun calculateOvers(
        legalBalls: Int
    ): String {

        return "${legalBalls / 6}.${legalBalls % 6}"
    }

    fun calculateCRR(
        runs: Int,
        legalBalls: Int
    ): Double {

        if (legalBalls == 0)
            return 0.0

        return ((runs.toDouble() / legalBalls) * 6)
    }

    fun rotateStrike(
        updatedData: CricketLiveData
    ): CricketLiveData {

        return updatedData.copy(

            strikerName =
                updatedData.nonStrikerName,

            nonStrikerName =
                updatedData.strikerName,

            strikerRuns =
                updatedData.nonStrikerRuns,

            strikerBalls =
                updatedData.nonStrikerBalls,

            nonStrikerRuns =
                updatedData.strikerRuns,

            nonStrikerBalls =
                updatedData.strikerBalls
        )
    }

    fun finishMatch(
        updatedData: CricketLiveData
    ) {

        val winner =

            if (
                updatedData.secondInnings &&
                updatedData.runs >= updatedData.target
            ) {

                updatedData.battingTeamName

            } else {

                updatedData.bowlingTeamName
            }

        val resultText =

            if (
                updatedData.secondInnings &&
                updatedData.runs >= updatedData.target
            ) {

                "${updatedData.battingTeamName} won by ${10 - updatedData.wickets} wickets"

            } else {

                "${updatedData.bowlingTeamName} won by ${updatedData.target - updatedData.runs - 1} runs"
            }

        cricketViewModel.updateLiveMatch(

            updatedData.copy(

                matchCompleted = true,

                matchStatus = "COMPLETED",

                winner = winner,

                resultText = resultText
            )
        )

        matchViewModel.finishMatch(
            updatedData.matchId,
            winner
        )
    }

    Scaffold(

        topBar = {

            TopAppBar(

                title = {

                    Text(

                        text =
                            "Cricket Scoring",

                        fontWeight =
                            FontWeight.Bold
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
                                Icons.AutoMirrored.Filled.ArrowBack,

                            contentDescription =
                                "Back"
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

                    FlowRow(

                        horizontalArrangement =
                            Arrangement.spacedBy(10.dp),

                        verticalArrangement =
                            Arrangement.spacedBy(10.dp)
                    ) {

                        val buttons = listOf(
                            "0",
                            "1",
                            "2",
                            "3",
                            "4",
                            "6",
                            "WD",
                            "NB"
                        )

                        buttons.forEach { value ->

                            CricketButton(
                                text = value
                            ) {

                                var updatedData =
                                    liveData

                                when (value) {

                                    "WD" -> {

                                        updatedData =
                                            updatedData.copy(

                                                runs =
                                                    updatedData.runs + 1,

                                                extras =
                                                    updatedData.extras + 1,

                                                bowlerRuns =
                                                    updatedData.bowlerRuns + 1,

                                                thisOver =
                                                    (
                                                            updatedData.thisOver +
                                                                    "WD"
                                                            ).takeLast(6)
                                            )
                                    }

                                    "NB" -> {

                                        updatedData =
                                            updatedData.copy(

                                                runs =
                                                    updatedData.runs + 1,

                                                extras =
                                                    updatedData.extras + 1,

                                                bowlerRuns =
                                                    updatedData.bowlerRuns + 1,

                                                thisOver =
                                                    (
                                                            updatedData.thisOver +
                                                                    "NB"
                                                            ).takeLast(6)
                                            )
                                    }

                                    else -> {

                                        val run =
                                            value.toInt()

                                        val legalBalls =
                                            updatedData.legalBalls + 1

                                        val runs =
                                            updatedData.runs + run

                                        updatedData =
                                            updatedData.copy(

                                                runs = runs,

                                                legalBalls =
                                                    legalBalls,

                                                balls =
                                                    updatedData.balls + 1,

                                                overs =
                                                    calculateOvers(
                                                        legalBalls
                                                    ),

                                                currentRunRate =
                                                    calculateCRR(
                                                        runs,
                                                        legalBalls
                                                    ),

                                                strikerRuns =
                                                    updatedData.strikerRuns + run,

                                                strikerBalls =
                                                    updatedData.strikerBalls + 1,

                                                bowlerRuns =
                                                    updatedData.bowlerRuns + run,

                                                thisOver =
                                                    (
                                                            updatedData.thisOver +
                                                                    value
                                                            ).takeLast(6)
                                            )

                                        if (
                                            run % 2 != 0
                                        ) {

                                            updatedData =
                                                rotateStrike(
                                                    updatedData
                                                )
                                        }
                                    }
                                }

                                if (
                                    updatedData.secondInnings &&
                                    updatedData.runs >= updatedData.target
                                ) {

                                    finishMatch(updatedData)
                                }

                                else {

                                    cricketViewModel
                                        .updateLiveMatch(
                                            updatedData
                                        )
                                }
                            }
                        }
                    }

                    Row(
                        modifier =
                            Modifier.fillMaxWidth(),

                        horizontalArrangement =
                            Arrangement.spacedBy(12.dp)
                    ) {

                        CricketActionButton(

                            text = "WICKET",

                            modifier =
                                Modifier.weight(1f),

                            color =
                                MaterialTheme.colorScheme.error
                        ) {

                            if (
                                liveData.wickets >= 10
                            ) return@CricketActionButton

                            val legalBalls =
                                liveData.legalBalls + 1

                            var updatedData =

                                liveData.copy(

                                    wickets =
                                        liveData.wickets + 1,

                                    legalBalls =
                                        legalBalls,

                                    balls =
                                        liveData.balls + 1,

                                    overs =
                                        calculateOvers(
                                            legalBalls
                                        ),

                                    bowlerWickets =
                                        liveData.bowlerWickets + 1,

                                    strikerBalls =
                                        liveData.strikerBalls + 1,

                                    thisOver =
                                        (
                                                liveData.thisOver +
                                                        "W"
                                                ).takeLast(6)
                                )

                            if (
                                updatedData.wickets >= 10
                            ) {

                                finishMatch(updatedData)
                            }

                            else {

                                cricketViewModel
                                    .updateLiveMatch(
                                        updatedData
                                    )
                            }
                        }

                        CricketActionButton(

                            text = "END OVER",

                            modifier =
                                Modifier.weight(1f),

                            color =
                                MaterialTheme.colorScheme.primary
                        ) {

                            val updatedData =
                                rotateStrike(
                                    liveData
                                )

                            cricketViewModel
                                .updateLiveMatch(
                                    updatedData
                                )
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
                                "LIVE • ${liveData.battingTeamName} BATTING",

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
                            Modifier.padding(18.dp),

                        verticalArrangement =
                            Arrangement.spacedBy(10.dp)
                    ) {

                        Text(

                            text =
                                liveData.battingTeamName,

                            style =
                                MaterialTheme.typography.titleLarge,

                            fontWeight =
                                FontWeight.Bold
                        )

                        Text(

                            text =
                                "${liveData.runs}/${liveData.wickets}",

                            style =
                                MaterialTheme.typography.displayMedium,

                            fontWeight =
                                FontWeight.ExtraBold
                        )

                        Text(
                            text =
                                "Overs: ${liveData.overs}/${liveData.maxOvers}"
                        )

                        Text(
                            text =
                                "CRR: ${
                                    String.format(
                                        "%.2f",
                                        liveData.currentRunRate
                                    )
                                }"
                        )

                        if (liveData.secondInnings) {

                            Text(
                                text =
                                    "Target: ${liveData.target}"
                            )

                            Text(
                                text =
                                    "RRR: ${
                                        String.format(
                                            "%.2f",
                                            liveData.requiredRunRate
                                        )
                                    }"
                            )
                        }

                        Spacer(
                            modifier =
                                Modifier.height(8.dp)
                        )

                        Text(

                            text =
                                "★ ${liveData.strikerName} ${liveData.strikerRuns} (${liveData.strikerBalls})",

                            fontWeight =
                                FontWeight.Bold
                        )

                        Text(
                            text =
                                "${liveData.nonStrikerName} ${liveData.nonStrikerRuns} (${liveData.nonStrikerBalls})"
                        )

                        Spacer(
                            modifier =
                                Modifier.height(8.dp)
                        )

                        Text(
                            text =
                                "Bowler: ${liveData.bowlerName}"
                        )

                        Text(
                            text =
                                "${liveData.bowlerWickets}/${liveData.bowlerRuns}"
                        )

                        Spacer(
                            modifier =
                                Modifier.height(8.dp)
                        )

                        Text(
                            text =
                                "This Over: ${
                                    liveData.thisOver.joinToString(" ")
                                }"
                        )
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
                        Modifier.height(140.dp)
                )
            }
        }
    }
}

@Composable
fun CricketButton(

    text: String,

    onClick: () -> Unit
) {

    Button(

        onClick = onClick,

        modifier =
            Modifier
                .width(78.dp)
                .height(54.dp),

        shape =
            RoundedCornerShape(12.dp)
    ) {

        Text(
            text = text
        )
    }
}

@Composable
fun CricketActionButton(

    text: String,

    modifier: Modifier =
        Modifier,

    color: Color,

    onClick: () -> Unit
) {

    Button(

        onClick = onClick,

        modifier =
            modifier.height(56.dp),

        shape =
            RoundedCornerShape(14.dp),

        colors =
            ButtonDefaults.buttonColors(

                containerColor =
                    color
            )
    ) {

        Text(

            text = text,

            color = Color.White,

            fontWeight =
                FontWeight.Bold
        )
    }
}