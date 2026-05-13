package com.gramakalyana.sports.ui.screens.live

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.gramakalyana.sports.ui.components.GlassmorphismCard
import com.gramakalyana.sports.viewmodel.CricketLiveViewModel
import com.gramakalyana.sports.viewmodel.KabaddiLiveViewModel
import com.gramakalyana.sports.viewmodel.MatchViewModel
import com.gramakalyana.sports.viewmodel.VolleyballLiveViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchDetailsScreen(

    navController: NavController,

    matchId: String
) {

    val matchViewModel:
            MatchViewModel =
        viewModel()

    val cricketViewModel:
            CricketLiveViewModel =
        viewModel()

    val kabaddiViewModel:
            KabaddiLiveViewModel =
        viewModel()

    val volleyballViewModel:
            VolleyballLiveViewModel =
        viewModel()

    val matches by
    matchViewModel.matches.collectAsState()

    val cricketLive by
    cricketViewModel.liveMatch.collectAsState()

    val kabaddiLive by
    kabaddiViewModel.liveMatch.collectAsState()

    val volleyballLive by
    volleyballViewModel.liveMatch.collectAsState()

    val match =
        remember(matches) {

            matches.find {

                it.matchId == matchId
            }
        }

    LaunchedEffect(match) {

        match?.let {

            when (it.sportType) {

                "Cricket" -> {

                    cricketViewModel
                        .observeLiveMatch(
                            matchId
                        )
                }

                "Kabaddi" -> {

                    kabaddiViewModel
                        .observeLiveMatch(
                            matchId
                        )
                }

                "Volleyball" -> {

                    volleyballViewModel
                        .observeLiveMatch(
                            matchId
                        )
                }
            }
        }
    }

    Scaffold(

        topBar = {

            TopAppBar(

                title = {

                    Text(

                        text =

                            when (match?.sportType) {

                                "Cricket" ->
                                    "🏏 Cricket Match"

                                "Kabaddi" ->
                                    "🤼 Kabaddi Match"

                                else ->
                                    "🏐 Volleyball Match"
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

                Row(
                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .background(

                                if (
                                    match?.status == "COMPLETED"
                                )
                                    Color.Gray

                                else
                                    Color.Red,

                                CircleShape
                            )
                    )

                    Spacer(
                        modifier =
                            Modifier.width(8.dp)
                    )

                    Text(

                        text =

                            if (
                                match?.status == "COMPLETED"
                            )
                                "MATCH COMPLETED"

                            else
                                "LIVE",

                        fontWeight =
                            FontWeight.Bold
                    )
                }
            }

            when (match?.sportType) {

                "Cricket" -> {

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
                                        cricketLive.battingTeamName,

                                    style =
                                        MaterialTheme.typography.titleLarge,

                                    fontWeight =
                                        FontWeight.ExtraBold
                                )

                                Text(

                                    text =
                                        "${cricketLive.runs}/${cricketLive.wickets}",

                                    style =
                                        MaterialTheme.typography.displayLarge,

                                    fontWeight =
                                        FontWeight.ExtraBold
                                )

                                Text(
                                    text =
                                        "Overs: ${cricketLive.overs}"
                                )

                                Text(
                                    text =
                                        "CRR: ${
                                            String.format(
                                                "%.2f",
                                                cricketLive.currentRunRate
                                            )
                                        }"
                                )

                                Text(
                                    text =
                                        "★ ${cricketLive.strikerName} ${cricketLive.strikerRuns} (${cricketLive.strikerBalls})"
                                )

                                Text(
                                    text =
                                        "${cricketLive.nonStrikerName} ${cricketLive.nonStrikerRuns} (${cricketLive.nonStrikerBalls})"
                                )

                                Text(
                                    text =
                                        "Bowler: ${cricketLive.bowlerName}"
                                )

                                Text(

                                    text =
                                        "This Over",

                                    fontWeight =
                                        FontWeight.Bold
                                )

                                FlowRow(

                                    horizontalArrangement =
                                        Arrangement.spacedBy(8.dp)
                                ) {

                                    cricketLive.thisOver
                                        .forEach {

                                            Card(

                                                shape =
                                                    RoundedCornerShape(10.dp),

                                                colors =
                                                    CardDefaults.cardColors(

                                                        containerColor =
                                                            MaterialTheme.colorScheme.primary
                                                    )
                                            ) {

                                                Text(

                                                    text = it,

                                                    color =
                                                        Color.White,

                                                    modifier =
                                                        Modifier.padding(
                                                            horizontal = 12.dp,
                                                            vertical = 8.dp
                                                        ),

                                                    fontWeight =
                                                        FontWeight.Bold
                                                )
                                            }
                                        }
                                }
                            }
                        }
                    }
                }

                "Kabaddi" -> {

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
                                        "${kabaddiLive.teamAName} vs ${kabaddiLive.teamBName}",

                                    style =
                                        MaterialTheme.typography.titleLarge,

                                    fontWeight =
                                        FontWeight.ExtraBold
                                )

                                Text(

                                    text =
                                        "${kabaddiLive.teamAScore} - ${kabaddiLive.teamBScore}",

                                    style =
                                        MaterialTheme.typography.displayLarge,

                                    fontWeight =
                                        FontWeight.ExtraBold
                                )

                                Text(
                                    text =
                                        "Half: ${kabaddiLive.currentHalf}"
                                )

                                Text(
                                    text =
                                        "Raiding Team: ${kabaddiLive.currentRaidingTeam}"
                                )

                                Text(
                                    text =
                                        "Bonus ${kabaddiLive.bonusPointsA} - ${kabaddiLive.bonusPointsB}"
                                )

                                Text(
                                    text =
                                        "Tackle ${kabaddiLive.tacklePointsA} - ${kabaddiLive.tacklePointsB}"
                                )

                                Text(
                                    text =
                                        "All Outs ${kabaddiLive.allOutCountA} - ${kabaddiLive.allOutCountB}"
                                )

                                Text(

                                    text =
                                        "Recent Raids",

                                    fontWeight =
                                        FontWeight.Bold
                                )

                                Column(

                                    verticalArrangement =
                                        Arrangement.spacedBy(6.dp)
                                ) {

                                    kabaddiLive.recentRaids
                                        .takeLast(5)
                                        .reversed()
                                        .forEach {

                                            Card(

                                                shape =
                                                    RoundedCornerShape(10.dp),

                                                colors =
                                                    CardDefaults.cardColors(

                                                        containerColor =
                                                            MaterialTheme.colorScheme.surfaceVariant
                                                    )
                                            ) {

                                                Text(

                                                    text = it,

                                                    modifier =
                                                        Modifier.padding(
                                                            horizontal = 12.dp,
                                                            vertical = 10.dp
                                                        )
                                                )
                                            }
                                        }
                                }
                            }
                        }
                    }
                }

                "Volleyball" -> {

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
                                        "${volleyballLive.teamAName} vs ${volleyballLive.teamBName}",

                                    style =
                                        MaterialTheme.typography.titleLarge,

                                    fontWeight =
                                        FontWeight.ExtraBold
                                )

                                Text(

                                    text =
                                        "${volleyballLive.teamAPoints} - ${volleyballLive.teamBPoints}",

                                    style =
                                        MaterialTheme.typography.displayLarge,

                                    fontWeight =
                                        FontWeight.ExtraBold
                                )

                                Text(
                                    text =
                                        "Sets ${volleyballLive.teamASets} - ${volleyballLive.teamBSets}"
                                )

                                Text(
                                    text =
                                        "Current Set ${volleyballLive.currentSet}"
                                )

                                Text(
                                    text =
                                        "Serving: ${volleyballLive.servingTeam}"
                                )

                                Text(
                                    text =
                                        "Timeouts ${volleyballLive.timeoutA} - ${volleyballLive.timeoutB}"
                                )

                                Text(

                                    text =
                                        "Recent Points",

                                    fontWeight =
                                        FontWeight.Bold
                                )

                                Column(

                                    verticalArrangement =
                                        Arrangement.spacedBy(6.dp)
                                ) {

                                    volleyballLive.recentPoints
                                        .takeLast(5)
                                        .reversed()
                                        .forEach {

                                            Card(

                                                shape =
                                                    RoundedCornerShape(10.dp),

                                                colors =
                                                    CardDefaults.cardColors(

                                                        containerColor =
                                                            MaterialTheme.colorScheme.surfaceVariant
                                                    )
                                            ) {

                                                Text(

                                                    text = it,

                                                    modifier =
                                                        Modifier.padding(
                                                            horizontal = 12.dp,
                                                            vertical = 10.dp
                                                        )
                                                )
                                            }
                                        }
                                }
                            }
                        }
                    }
                }
            }

            item {

                Spacer(
                    modifier =
                        Modifier.height(40.dp)
                )
            }
        }
    }
}