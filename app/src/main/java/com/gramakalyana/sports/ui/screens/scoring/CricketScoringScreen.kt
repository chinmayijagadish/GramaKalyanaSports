package com.gramakalyana.sports.ui.screens.scoring

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.gramakalyana.sports.viewmodel.CricketLiveViewModel

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

    LaunchedEffect(Unit) {

        cricketViewModel.observeLiveMatch(
            matchId
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

        return ((runs.toDouble() /
                legalBalls) * 6)
    }

    fun rotateStrike(
        updatedData:
        com.gramakalyana.sports.data.model.CricketLiveData
    ):
            com.gramakalyana.sports.data.model.CricketLiveData {

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
        updatedData:
        com.gramakalyana.sports.data.model.CricketLiveData
    ) {

        val winner =

            if (
                updatedData.runs >=
                updatedData.target
            ) {

                updatedData.battingTeamName

            } else {

                updatedData.bowlingTeamName
            }

        val resultText =

            if (
                updatedData.runs >=
                updatedData.target
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
    }

    Scaffold(

        topBar = {

            TopAppBar(

                title = {

                    Text(
                        text = "Cricket Scoring",

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
        }

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    MaterialTheme.colorScheme.background
                )
                .padding(paddingValues)
                .padding(16.dp)
        ) {

            GlassmorphismCard(
                modifier =
                    Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier =
                        Modifier.padding(18.dp),

                    verticalArrangement =
                        Arrangement.spacedBy(8.dp)
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
                            "CRR: ${String.format("%.2f", liveData.currentRunRate)}"
                    )

                    if (liveData.secondInnings) {

                        Text(
                            text =
                                "Target: ${liveData.target}"
                        )

                        Text(
                            text =
                                "RRR: ${String.format("%.2f", liveData.requiredRunRate)}"
                        )
                    }

                    Spacer(
                        modifier =
                            Modifier.height(6.dp)
                    )

                    Text(
                        text =
                            "${liveData.strikerName} ${liveData.strikerRuns} (${liveData.strikerBalls})"
                    )

                    Text(
                        text =
                            "${liveData.nonStrikerName} ${liveData.nonStrikerRuns} (${liveData.nonStrikerBalls})"
                    )

                    Spacer(
                        modifier =
                            Modifier.height(6.dp)
                    )

                    Text(
                        text =
                            "${liveData.bowlerName} ${liveData.bowlerWickets}/${liveData.bowlerRuns}"
                    )

                    Spacer(
                        modifier =
                            Modifier.height(10.dp)
                    )

                    Text(
                        text =
                            "This Over: ${
                                liveData.thisOver.joinToString(" ")
                            }"
                    )
                }
            }

            Spacer(
                modifier =
                    Modifier.height(20.dp)
            )

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

            LazyVerticalGrid(

                columns =
                    GridCells.Fixed(4),

                horizontalArrangement =
                    Arrangement.spacedBy(10.dp),

                verticalArrangement =
                    Arrangement.spacedBy(10.dp),

                modifier =
                    Modifier.height(220.dp)
            ) {

                items(buttons) { value ->

                    Button(

                        onClick = {

                            if (
                                liveData.matchCompleted
                            ) return@Button

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

                                    val strikerRuns =
                                        updatedData.strikerRuns + run

                                    val strikerBalls =
                                        updatedData.strikerBalls + 1

                                    val overs =
                                        calculateOvers(
                                            legalBalls
                                        )

                                    val crr =
                                        calculateCRR(
                                            runs,
                                            legalBalls
                                        )

                                    updatedData =
                                        updatedData.copy(

                                            runs = runs,

                                            legalBalls =
                                                legalBalls,

                                            balls =
                                                updatedData.balls + 1,

                                            overs = overs,

                                            currentRunRate =
                                                crr,

                                            strikerRuns =
                                                strikerRuns,

                                            strikerBalls =
                                                strikerBalls,

                                            bowlerRuns =
                                                updatedData.bowlerRuns + run,

                                            fours =
                                                if (run == 4)
                                                    updatedData.fours + 1
                                                else
                                                    updatedData.fours,

                                            sixes =
                                                if (run == 6)
                                                    updatedData.sixes + 1
                                                else
                                                    updatedData.sixes,

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

                                    if (
                                        legalBalls % 6 == 0
                                    ) {

                                        updatedData =
                                            rotateStrike(
                                                updatedData
                                            )
                                    }
                                }
                            }

                            if (
                                updatedData.secondInnings
                            ) {

                                val ballsLeft =
                                    120 -
                                            updatedData.legalBalls

                                val runsNeeded =
                                    updatedData.target -
                                            updatedData.runs

                                val rrr =

                                    if (
                                        ballsLeft <= 0
                                    ) {

                                        0.0

                                    } else {

                                        ((runsNeeded.toDouble() /
                                                ballsLeft) * 6)
                                    }

                                updatedData =
                                    updatedData.copy(
                                        requiredRunRate = rrr
                                    )
                            }

                            cricketViewModel
                                .updateLiveMatch(
                                    updatedData
                                )

                            val inningsFinished =

                                updatedData.wickets >= 10 ||
                                        updatedData.legalBalls >= 120

                            if (
                                !updatedData.secondInnings &&
                                inningsFinished
                            ) {

                                cricketViewModel
                                    .updateLiveMatch(

                                        updatedData.copy(

                                            secondInnings = true,

                                            inningsCompleted = true,

                                            firstInningsScore =
                                                updatedData.runs,

                                            target =
                                                updatedData.runs + 1,

                                            runs = 0,

                                            wickets = 0,

                                            legalBalls = 0,

                                            balls = 0,

                                            overs = "0.0",

                                            currentRunRate = 0.0,

                                            requiredRunRate = 0.0,

                                            battingTeamId =
                                                updatedData.bowlingTeamId,

                                            bowlingTeamId =
                                                updatedData.battingTeamId,

                                            battingTeamName =
                                                updatedData.bowlingTeamName,

                                            bowlingTeamName =
                                                updatedData.battingTeamName,

                                            strikerRuns = 0,

                                            strikerBalls = 0,

                                            nonStrikerRuns = 0,

                                            nonStrikerBalls = 0,

                                            bowlerRuns = 0,

                                            bowlerWickets = 0,

                                            bowlerBalls = 0,

                                            thisOver =
                                                emptyList()
                                        )
                                    )
                            }

                            else if (
                                updatedData.secondInnings
                            ) {

                                val targetReached =
                                    updatedData.runs >=
                                            updatedData.target

                                val inningsOver =
                                    updatedData.wickets >= 10 ||
                                            updatedData.legalBalls >= 120

                                if (
                                    targetReached ||
                                    inningsOver
                                ) {

                                    finishMatch(
                                        updatedData
                                    )
                                }
                            }
                        },

                        shape =
                            RoundedCornerShape(12.dp)
                    ) {

                        Text(
                            text = value
                        )
                    }
                }
            }

            Spacer(
                modifier =
                    Modifier.height(16.dp)
            )

            Button(

                onClick = {

                    if (
                        liveData.matchCompleted
                    ) return@Button

                    if (
                        liveData.wickets >= 10
                    ) return@Button

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
                        legalBalls % 6 == 0
                    ) {

                        updatedData =
                            rotateStrike(
                                updatedData
                            )
                    }

                    cricketViewModel
                        .updateLiveMatch(
                            updatedData
                        )
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
                            MaterialTheme.colorScheme.error
                    )
            ) {

                Text(

                    text = "WICKET",

                    color = Color.White,

                    fontWeight =
                        FontWeight.Bold
                )
            }

            Spacer(
                modifier =
                    Modifier.height(20.dp)
            )

            if (
                liveData.matchCompleted
            ) {

                GlassmorphismCard(
                    modifier =
                        Modifier.fillMaxWidth()
                ) {

                    Column(
                        modifier =
                            Modifier.padding(20.dp),

                        horizontalAlignment =
                            Alignment.CenterHorizontally
                    ) {

                        Text(

                            text =
                                "MATCH COMPLETED",

                            fontWeight =
                                FontWeight.Bold,

                            color =
                                MaterialTheme.colorScheme.primary
                        )

                        Spacer(
                            modifier =
                                Modifier.height(10.dp)
                        )

                        Text(

                            text =
                                liveData.resultText,

                            style =
                                MaterialTheme.typography.titleLarge,

                            fontWeight =
                                FontWeight.ExtraBold
                        )
                    }
                }
            }
        }
    }
}