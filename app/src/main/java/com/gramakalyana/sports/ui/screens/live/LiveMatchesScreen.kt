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
import androidx.compose.runtime.LaunchedEffect
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
import com.gramakalyana.sports.viewmodel.KabaddiLiveViewModel
import com.gramakalyana.sports.viewmodel.MatchViewModel
import com.gramakalyana.sports.viewmodel.VolleyballLiveViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LiveMatchesScreen(
    navController: NavController
) {

    val matchViewModel:
            MatchViewModel =
        viewModel()

    val allMatches by
    matchViewModel.matches.collectAsState()

    val selectedZone =
        SelectedZone.selectedZone

    val selectedSport =
        SelectedZone.selectedSport

    val filteredMatches =
        remember(
            allMatches,
            selectedZone,
            selectedSport
        ) {

            allMatches.filter { match ->

                val zoneMatches =

                    if (
                        selectedZone == "ALL"
                    ) {
                        true
                    }

                    else {

                        match.zone
                            .trim()
                            .lowercase() ==

                                selectedZone
                                    .trim()
                                    .lowercase()
                    }

                val sportMatches =

                    if (
                        selectedSport == "ALL"
                    ) {
                        true
                    }

                    else {

                        match.sportType
                            .trim()
                            .lowercase() ==

                                selectedSport
                                    .trim()
                                    .lowercase()
                    }

                zoneMatches && sportMatches
            }
        }

    val liveMatches =
        filteredMatches.filter {

            it.status
                .trim()
                .uppercase() == "LIVE"
        }

    val upcomingMatches =
        filteredMatches.filter {

            it.status
                .trim()
                .uppercase() == "UPCOMING"
        }

    val completedMatches =
        filteredMatches.filter {

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

                            if (
                                selectedSport == "ALL"
                            ) {

                                "$selectedZone Matches"

                            }

                            else {

                                "$selectedSport • $selectedZone"
                            },

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

        else if (filteredMatches.isEmpty()) {

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

                        LiveMatchCard(
                            match = match,
                            navController = navController
                        )
                    }
                }

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

                            resultText = match.winner,

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
fun LiveMatchCard(

    match: Match,

    navController: NavController
) {

    val cricketViewModel:
            CricketLiveViewModel =
        viewModel()

    val kabaddiViewModel:
            KabaddiLiveViewModel =
        viewModel()

    val volleyballViewModel:
            VolleyballLiveViewModel =
        viewModel()

    val cricketLive by
    cricketViewModel.liveMatch.collectAsState()

    val kabaddiLive by
    kabaddiViewModel.liveMatch.collectAsState()

    val volleyballLive by
    volleyballViewModel.liveMatch.collectAsState()

    LaunchedEffect(match.matchId) {

        when (match.sportType) {

            "Cricket" -> {

                cricketViewModel.observeLiveMatch(
                    match.matchId
                )
            }

            "Kabaddi" -> {

                kabaddiViewModel.observeLiveMatch(
                    match.matchId
                )
            }

            else -> {

                volleyballViewModel.observeLiveMatch(
                    match.matchId
                )
            }
        }
    }

    val liveScore =

        when (match.sportType) {

            "Cricket" -> {

                if (
                    cricketLive.matchId ==
                    match.matchId
                ) {

                    "${cricketLive.runs}/${cricketLive.wickets} (${cricketLive.overs})"

                } else ""
            }

            "Kabaddi" -> {

                if (
                    kabaddiLive.matchId ==
                    match.matchId
                ) {

                    "${kabaddiLive.teamAScore} - ${kabaddiLive.teamBScore} | Half ${kabaddiLive.currentHalf}"

                } else ""
            }

            else -> {

                if (
                    volleyballLive.matchId ==
                    match.matchId
                ) {

                    "${volleyballLive.teamAPoints} - ${volleyballLive.teamBPoints} | Sets ${volleyballLive.teamASets}-${volleyballLive.teamBSets}"

                } else ""
            }
        }

    val resultText =

        when (match.sportType) {

            "Cricket" -> {

                if (
                    cricketLive.matchId ==
                    match.matchId
                ) {

                    cricketLive.resultText

                } else ""
            }

            "Kabaddi" -> {

                if (
                    kabaddiLive.matchId ==
                    match.matchId
                ) {

                    kabaddiLive.resultText

                } else ""
            }

            else -> {

                if (
                    volleyballLive.matchId ==
                    match.matchId
                ) {

                    volleyballLive.resultText

                } else ""
            }
        }

    RealtimeMatchCard(

        match = match,

        liveScore = liveScore,

        resultText = resultText,

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
                Arrangement.spacedBy(10.dp)
        ) {

            Row(
                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Text(

                    text = when (match.sportType) {

                        "Cricket" ->
                            "🏏 Cricket Match"

                        "Kabaddi" ->
                            "🤼 Kabaddi Match"

                        else ->
                            "🏐 Volleyball Match"
                    },

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
                    match.status == "LIVE"
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