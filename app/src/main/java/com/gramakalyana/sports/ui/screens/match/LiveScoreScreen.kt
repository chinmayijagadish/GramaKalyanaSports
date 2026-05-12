package com.gramakalyana.sports.ui.screens.match

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.gramakalyana.sports.viewmodel.MatchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LiveScoreScreen(

    navController: NavController,

    matchId: String
) {

    val matchViewModel:
            MatchViewModel = viewModel()

    val allMatches by
    matchViewModel.matches.collectAsState()

    val match = allMatches.find {

        it.matchId == matchId
    }

    if (match == null) {

        Scaffold(

            topBar = {

                TopAppBar(

                    title = {

                        Text(
                            text = "Live Score"
                        )
                    }
                )
            }

        ) { paddingValues ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),

                verticalArrangement =
                    Arrangement.Center,

                horizontalAlignment =
                    Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Loading match..."
                )
            }
        }

        return
    }

    var teamAScore by remember {
        mutableIntStateOf(match.teamAScore)
    }

    var teamBScore by remember {
        mutableIntStateOf(match.teamBScore)
    }

    Scaffold(

        topBar = {

            TopAppBar(

                title = {

                    Text(
                        text = "Live Score",

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
                .padding(paddingValues)
                .padding(16.dp),

            verticalArrangement =
                Arrangement.spacedBy(20.dp)
        ) {

            Card(

                modifier =
                    Modifier.fillMaxWidth(),

                shape =
                    RoundedCornerShape(18.dp),

                colors =
                    CardDefaults.cardColors(
                        containerColor =
                            MaterialTheme
                                .colorScheme
                                .primaryContainer
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
                            "${match.teamAName} vs ${match.teamBName}",

                        style =
                            MaterialTheme
                                .typography
                                .headlineSmall,

                        fontWeight =
                            FontWeight.Bold
                    )

                    Spacer(
                        modifier =
                            Modifier.height(12.dp)
                    )

                    Text(
                        text =
                            "$teamAScore : $teamBScore",

                        style =
                            MaterialTheme
                                .typography
                                .displayMedium,

                        fontWeight =
                            FontWeight.Bold
                    )

                    Spacer(
                        modifier =
                            Modifier.height(12.dp)
                    )

                    Text(
                        text =
                            "Venue: ${match.venue}"
                    )
                }
            }

            Text(
                text = match.teamAName,

                style =
                    MaterialTheme.typography.titleLarge,

                fontWeight = FontWeight.Bold
            )

            Button(

                onClick = {

                    teamAScore++

                    val updatedMatch =

                        match.copy(

                            teamAScore =
                                teamAScore,

                            teamBScore =
                                teamBScore,

                            status =
                                "Live"
                        )

                    matchViewModel.updateMatch(
                        updatedMatch
                    )
                },

                modifier =
                    Modifier.fillMaxWidth(),

                shape =
                    RoundedCornerShape(14.dp)
            ) {

                Text(
                    text = "ADD SCORE TO ${match.teamAName}"
                )
            }

            Text(
                text = match.teamBName,

                style =
                    MaterialTheme.typography.titleLarge,

                fontWeight = FontWeight.Bold
            )

            Button(

                onClick = {

                    teamBScore++

                    val updatedMatch =

                        match.copy(

                            teamAScore =
                                teamAScore,

                            teamBScore =
                                teamBScore,

                            status =
                                "Live"
                        )

                    matchViewModel.updateMatch(
                        updatedMatch
                    )
                },

                modifier =
                    Modifier.fillMaxWidth(),

                shape =
                    RoundedCornerShape(14.dp)
            ) {

                Text(
                    text = "ADD SCORE TO ${match.teamBName}"
                )
            }

            Spacer(
                modifier =
                    Modifier.height(20.dp)
            )

            Button(

                onClick = {

                    val winner =

                        when {

                            teamAScore > teamBScore ->

                                match.teamAName

                            teamBScore > teamAScore ->

                                match.teamBName

                            else -> "Draw"
                        }

                    val completedMatch =

                        match.copy(

                            teamAScore =
                                teamAScore,

                            teamBScore =
                                teamBScore,

                            winner =
                                winner,

                            status =
                                "Completed"
                        )

                    matchViewModel.updateMatch(
                        completedMatch
                    )

                    navController.popBackStack()
                },

                modifier =
                    Modifier.fillMaxWidth(),

                shape =
                    RoundedCornerShape(14.dp)
            ) {

                Text(
                    text = "FINISH MATCH"
                )
            }
        }
    }
}