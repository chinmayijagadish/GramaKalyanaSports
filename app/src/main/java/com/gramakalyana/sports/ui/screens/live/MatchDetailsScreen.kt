package com.gramakalyana.sports.ui.screens.live

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.gramakalyana.sports.data.model.CricketLiveData
import com.gramakalyana.sports.ui.components.GlassmorphismCard
import com.gramakalyana.sports.viewmodel.CricketLiveViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchDetailsScreen(

    navController: NavController,

    matchId: String
) {

    val cricketViewModel:
            CricketLiveViewModel =
        viewModel()

    val context =
        LocalContext.current

    LaunchedEffect(Unit) {

        cricketViewModel.observeLiveMatch(
            matchId
        )
    }

    val liveData by
    cricketViewModel.liveMatch.collectAsState()

    Scaffold(

        topBar = {

            TopAppBar(

                title = {

                    Text(
                        text = "Live Match",

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
                                    liveData.matchCompleted
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
                                liveData.matchCompleted
                            )
                                "MATCH COMPLETED"
                            else
                                "LIVE",

                        fontWeight =
                            FontWeight.Bold,

                        color =

                            if (
                                liveData.matchCompleted
                            )
                                Color.Gray
                            else
                                Color.Red
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
                                liveData.battingTeamName,

                            style =
                                MaterialTheme.typography.titleLarge,

                            fontWeight =
                                FontWeight.ExtraBold
                        )

                        Text(

                            text =
                                "${liveData.runs}/${liveData.wickets}",

                            style =
                                MaterialTheme.typography.displayLarge,

                            fontWeight =
                                FontWeight.ExtraBold
                        )

                        Row(
                            horizontalArrangement =
                                Arrangement.spacedBy(18.dp)
                        ) {

                            Text(
                                text =
                                    "${liveData.overs}/${liveData.maxOvers} Overs"
                            )

                            Text(
                                text =
                                    "CRR ${
                                        String.format(
                                            "%.2f",
                                            liveData.currentRunRate
                                        )
                                    }"
                            )
                        }

                        if (liveData.secondInnings) {

                            val rrr =
                                if (
                                    liveData.requiredRunRate < 0
                                )
                                    0.0
                                else
                                    liveData.requiredRunRate

                            Row(
                                horizontalArrangement =
                                    Arrangement.spacedBy(18.dp)
                            ) {

                                Text(
                                    text =
                                        "Target ${liveData.target}"
                                )

                                Text(
                                    text =
                                        "RRR ${
                                            String.format(
                                                "%.2f",
                                                rrr
                                            )
                                        }"
                                )
                            }
                        }

                        Card(
                            shape =
                                RoundedCornerShape(14.dp),

                            colors =
                                CardDefaults.cardColors(

                                    containerColor =
                                        MaterialTheme.colorScheme.surface.copy(
                                            alpha = 0.3f
                                        )
                                )
                        ) {

                            Column(
                                modifier =
                                    Modifier.padding(14.dp),

                                verticalArrangement =
                                    Arrangement.spacedBy(10.dp)
                            ) {

                                Text(

                                    text =
                                        "Batters",

                                    fontWeight =
                                        FontWeight.Bold
                                )

                                Row(
                                    modifier =
                                        Modifier.fillMaxWidth(),

                                    horizontalArrangement =
                                        Arrangement.SpaceBetween
                                ) {

                                    Text(

                                        text =
                                            "★ ${liveData.strikerName}",

                                        fontWeight =
                                            FontWeight.Bold
                                    )

                                    Text(
                                        text =
                                            "${liveData.strikerRuns} (${liveData.strikerBalls})"
                                    )
                                }

                                Row(
                                    modifier =
                                        Modifier.fillMaxWidth(),

                                    horizontalArrangement =
                                        Arrangement.SpaceBetween
                                ) {

                                    Text(
                                        text =
                                            liveData.nonStrikerName
                                    )

                                    Text(
                                        text =
                                            "${liveData.nonStrikerRuns} (${liveData.nonStrikerBalls})"
                                    )
                                }
                            }
                        }

                        Card(
                            shape =
                                RoundedCornerShape(14.dp),

                            colors =
                                CardDefaults.cardColors(

                                    containerColor =
                                        MaterialTheme.colorScheme.surface.copy(
                                            alpha = 0.3f
                                        )
                                )
                        ) {

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(14.dp),

                                horizontalArrangement =
                                    Arrangement.SpaceBetween
                            ) {

                                Text(
                                    text =
                                        liveData.bowlerName
                                )

                                Text(

                                    text =
                                        "${liveData.bowlerWickets}W • ${liveData.bowlerRuns}R",

                                    fontWeight =
                                        FontWeight.Bold
                                )
                            }
                        }

                        Row(
                            modifier =
                                Modifier.fillMaxWidth(),

                            horizontalArrangement =
                                Arrangement.SpaceBetween
                        ) {

                            MiniStatCard(
                                title = "4s",
                                value =
                                    liveData.fours.toString()
                            )

                            MiniStatCard(
                                title = "6s",
                                value =
                                    liveData.sixes.toString()
                            )

                            MiniStatCard(
                                title = "Extras",
                                value =
                                    liveData.extras.toString()
                            )
                        }
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
                            Modifier.padding(18.dp),

                        verticalArrangement =
                            Arrangement.spacedBy(14.dp)
                    ) {

                        Text(

                            text =
                                "This Over",

                            style =
                                MaterialTheme.typography.titleMedium,

                            fontWeight =
                                FontWeight.Bold
                        )

                        if (
                            liveData.thisOver.isEmpty()
                        ) {

                            Text("-")
                        }

                        else {

                            FlowRow(

                                horizontalArrangement =
                                    Arrangement.spacedBy(10.dp),

                                verticalArrangement =
                                    Arrangement.spacedBy(10.dp)
                            ) {

                                liveData.thisOver
                                    .forEach { ball ->

                                        BallIndicator(
                                            text = ball
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

                item {

                    Card(

                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .clickable {

                                    shareMatchResult(
                                        context,
                                        liveData
                                    )
                                },

                        shape =
                            RoundedCornerShape(18.dp),

                        colors =
                            CardDefaults.cardColors(

                                containerColor =
                                    MaterialTheme.colorScheme.primary
                            )
                    ) {

                        Box(

                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .padding(18.dp),

                            contentAlignment =
                                Alignment.Center
                        ) {

                            Text(

                                text =
                                    "SHARE SCORECARD",

                                color =
                                    Color.White,

                                fontWeight =
                                    FontWeight.Bold
                            )
                        }
                    }
                }
            }

            item {

                Spacer(
                    modifier =
                        Modifier.height(24.dp)
                )
            }
        }
    }
}

@Composable
fun MiniStatCard(
    title: String,
    value: String
) {

    Card(
        shape =
            RoundedCornerShape(12.dp),

        colors =
            CardDefaults.cardColors(

                containerColor =
                    MaterialTheme.colorScheme.surface.copy(
                        alpha = 0.3f
                    )
            )
    ) {

        Column(
            modifier =
                Modifier.padding(
                    horizontal = 18.dp,
                    vertical = 12.dp
                ),

            horizontalAlignment =
                Alignment.CenterHorizontally
        ) {

            Text(
                text = title
            )

            Text(

                text = value,

                fontWeight =
                    FontWeight.Bold
            )
        }
    }
}

@Composable
fun BallIndicator(
    text: String
) {

    val backgroundColor =

        when (text) {

            "W" ->
                MaterialTheme.colorScheme.error

            "4", "6" ->
                MaterialTheme.colorScheme.primary

            "WD", "NB" ->
                Color(0xFFFF9800)

            else ->
                MaterialTheme.colorScheme.surfaceVariant
        }

    val textColor =

        if (
            text == "W" ||
            text == "4" ||
            text == "6" ||
            text == "WD" ||
            text == "NB"
        )
            Color.White

        else
            MaterialTheme.colorScheme.onSurface

    Box(
        modifier = Modifier
            .size(42.dp)
            .background(
                backgroundColor,
                CircleShape
            ),

        contentAlignment =
            Alignment.Center
    ) {

        Text(

            text = text,

            color = textColor,

            fontWeight =
                FontWeight.Bold
        )
    }
}

fun shareMatchResult(

    context: Context,

    liveData: CricketLiveData
) {

    val shareText =

        """
🏏 Grama Kalyana Sports

${liveData.battingTeamName}

Score:
${liveData.runs}/${liveData.wickets}

Overs:
${liveData.overs}/${liveData.maxOvers}

Result:
${liveData.resultText}

Powered by Grama Kalyana Sports
        """.trimIndent()

    val sendIntent =
        Intent().apply {

            action =
                Intent.ACTION_SEND

            putExtra(
                Intent.EXTRA_TEXT,
                shareText
            )

            type = "text/plain"
        }

    val shareIntent =
        Intent.createChooser(
            sendIntent,
            "Share Scorecard"
        )

    context.startActivity(
        shareIntent
    )
}