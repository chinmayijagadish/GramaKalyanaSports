package com.gramakalyana.sports.ui.screens.live

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.gramakalyana.sports.data.model.Match
import com.gramakalyana.sports.navigation.Screen
import com.gramakalyana.sports.ui.components.GlassmorphismCard
import com.gramakalyana.sports.ui.components.LiveBadge
import com.gramakalyana.sports.utils.SelectedZone
import com.gramakalyana.sports.viewmodel.CricketLiveViewModel
import com.gramakalyana.sports.viewmodel.MatchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LiveMatchesScreen(
    navController: NavController
) {

    val matchViewModel:
            MatchViewModel =
        viewModel()

    val cricketViewModel:
            CricketLiveViewModel =
        viewModel()

    val allMatches by
    matchViewModel.matches.collectAsState()

    val liveData by
    cricketViewModel.liveMatch.collectAsState()

    val selectedZone =
        SelectedZone.selectedZone

    val zoneMatches =
        remember(allMatches) {

            allMatches.filter {

                it.zone
                    .trim()
                    .lowercase() ==

                        selectedZone
                            .trim()
                            .lowercase()
            }
        }

    val liveMatches =
        zoneMatches.filter {

            it.status
                .trim()
                .uppercase() == "LIVE"
        }

    val upcomingMatches =
        zoneMatches.filter {

            it.status
                .trim()
                .uppercase() == "UPCOMING"
        }

    val completedMatches =
        zoneMatches.filter {

            it.status
                .trim()
                .uppercase() == "COMPLETED"
        }

    Scaffold(

        topBar = {

            TopAppBar(

                title = {

                    Text(

                        text =
                            "$selectedZone Matches",

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

        if (allMatches.isEmpty()) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),

                contentAlignment =
                    Alignment.Center
            ) {

                Text(
                    text =
                        "Loading matches..."
                )
            }
        }

        else if (zoneMatches.isEmpty()) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),

                contentAlignment =
                    Alignment.Center
            ) {

                Text(
                    text =
                        "No matches available"
                )
            }
        }

        else {

            LazyColumn(

                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        MaterialTheme.colorScheme.background
                    )
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),

                verticalArrangement =
                    Arrangement.spacedBy(22.dp)
            ) {

                // LIVE

                item {

                    MatchSectionTitle(
                        title = "LIVE MATCHES"
                    )
                }

                if (liveMatches.isEmpty()) {

                    item {

                        EmptySectionText()
                    }

                } else {

                    items(liveMatches) { match ->

                        RealtimeMatchCard(

                            match = match,

                            liveScore =

                                if (
                                    liveData.matchId ==
                                    match.matchId
                                ) {

                                    "${liveData.runs}/${liveData.wickets} (${liveData.overs})"

                                } else {

                                    ""
                                },

                            resultText =

                                if (
                                    liveData.matchId ==
                                    match.matchId
                                ) {

                                    liveData.resultText

                                } else {

                                    ""
                                },

                            onClick = {

                                navController.navigate(

                                    Screen.MatchDetails
                                        .createRoute(
                                            match.matchId
                                        )
                                )
                            }
                        )
                    }
                }

                // UPCOMING

                item {

                    MatchSectionTitle(
                        title = "UPCOMING MATCHES"
                    )
                }

                if (upcomingMatches.isEmpty()) {

                    item {

                        EmptySectionText()
                    }

                } else {

                    items(upcomingMatches) { match ->

                        RealtimeMatchCard(

                            match = match,

                            liveScore = "",

                            resultText = "",

                            onClick = {

                                navController.navigate(

                                    Screen.MatchDetails
                                        .createRoute(
                                            match.matchId
                                        )
                                )
                            }
                        )
                    }
                }

                // COMPLETED

                item {

                    MatchSectionTitle(
                        title = "COMPLETED MATCHES"
                    )
                }

                if (completedMatches.isEmpty()) {

                    item {

                        EmptySectionText()
                    }

                } else {

                    items(completedMatches) { match ->

                        RealtimeMatchCard(

                            match = match,

                            liveScore = "",

                            resultText = "",

                            onClick = {

                                navController.navigate(

                                    Screen.MatchDetails
                                        .createRoute(
                                            match.matchId
                                        )
                                )
                            }
                        )
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
}

@Composable
fun MatchSectionTitle(
    title: String
) {

    Text(

        text = title,

        style =
            MaterialTheme.typography.titleLarge,

        fontWeight =
            FontWeight.ExtraBold,

        color =
            MaterialTheme.colorScheme.primary
    )
}

@Composable
fun EmptySectionText() {

    Text(

        text = "No matches",

        color =
            MaterialTheme.colorScheme.onSurface.copy(
                alpha = 0.6f
            )
    )
}

@Composable
fun RealtimeMatchCard(

    match: Match,

    liveScore: String,

    resultText: String,

    onClick: () -> Unit
) {

    GlassmorphismCard(

        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = onClick
            )
    ) {

        Column(
            modifier =
                Modifier.padding(18.dp),

            verticalArrangement =
                Arrangement.spacedBy(12.dp)
        ) {

            Row(
                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Text(

                    text =
                        "${match.sportType} Match",

                    color =
                        MaterialTheme.colorScheme.primary,

                    fontWeight =
                        FontWeight.Bold
                )

                Spacer(
                    modifier =
                        Modifier.weight(1f)
                )

                if (
                    match.status
                        .trim()
                        .uppercase() == "LIVE"
                ) {

                    LiveBadge()
                }
            }

            Text(

                text =
                    "${match.teamAName} vs ${match.teamBName}",

                style =
                    MaterialTheme.typography.titleLarge,

                fontWeight =
                    FontWeight.Bold
            )

            if (liveScore.isNotBlank()) {

                Text(

                    text = liveScore,

                    style =
                        MaterialTheme.typography.headlineSmall,

                    fontWeight =
                        FontWeight.ExtraBold
                )
            }

            if (resultText.isNotBlank()) {

                Text(

                    text = resultText,

                    color =
                        MaterialTheme.colorScheme.primary,

                    fontWeight =
                        FontWeight.Bold
                )
            }

            Text(
                text =
                    "Venue: ${match.venue}"
            )

            Text(
                text =
                    "Date: ${match.matchDate}"
            )

            Text(
                text =
                    "Time: ${match.matchTime}"
            )

            Text(
                text =
                    "Status: ${match.status}"
            )
        }
    }
}