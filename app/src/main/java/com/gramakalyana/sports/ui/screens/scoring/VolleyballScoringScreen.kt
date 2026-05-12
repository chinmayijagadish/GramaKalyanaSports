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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gramakalyana.sports.logic.VolleyballLogic
import com.gramakalyana.sports.logic.VolleyballState
import com.gramakalyana.sports.ui.components.GlassmorphismCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VolleyballScoringScreen(

    navController: NavController,

    matchId: String?
) {

    val logic = remember {

        VolleyballLogic()
    }

    var state by remember {

        mutableStateOf(
            VolleyballState()
        )
    }

    Scaffold(

        topBar = {

            TopAppBar(

                title = {

                    Text(
                        "Volleyball Panel",

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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    MaterialTheme.colorScheme.background
                )
                .padding(paddingValues)
                .padding(16.dp)
        ) {

            VolleyballScoreboardHeader(
                state
            )

            Spacer(
                modifier =
                    Modifier.height(24.dp)
            )

            VolleyballScoringControls(

                onTeamA = {

                    state =
                        logic.addTeamAPoint()
                },

                onTeamB = {

                    state =
                        logic.addTeamBPoint()
                },

                onEndSet = {

                    state =
                        logic.endSet()
                }
            )
        }
    }
}

@Composable
fun VolleyballScoreboardHeader(
    state: VolleyballState
) {

    GlassmorphismCard(
        modifier =
            Modifier.fillMaxWidth()
    ) {

        Column(
            modifier =
                Modifier.padding(24.dp)
        ) {

            Text(

                "Set ${state.currentSet}",

                style =
                    MaterialTheme.typography.titleMedium,

                fontWeight =
                    FontWeight.Bold,

                modifier =
                    Modifier.align(
                        Alignment.CenterHorizontally
                    )
            )

            Spacer(
                modifier =
                    Modifier.height(8.dp)
            )

            Row(
                modifier =
                    Modifier.fillMaxWidth(),

                horizontalArrangement =
                    Arrangement.SpaceBetween,

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Column(
                    horizontalAlignment =
                        Alignment.CenterHorizontally
                ) {

                    Text(

                        "Spikers",

                        style =
                            MaterialTheme.typography.titleMedium,

                        color =
                            MaterialTheme.colorScheme.primary
                    )

                    Text(
                        "Sets: ${state.teamASets}"
                    )

                    Text(

                        "${state.teamAPoints}",

                        style =
                            MaterialTheme.typography.displayLarge,

                        fontWeight =
                            FontWeight.Bold
                    )
                }

                Text(
                    "vs"
                )

                Column(
                    horizontalAlignment =
                        Alignment.CenterHorizontally
                ) {

                    Text(

                        "Blockers",

                        style =
                            MaterialTheme.typography.titleMedium,

                        color =
                            MaterialTheme.colorScheme.secondary
                    )

                    Text(
                        "Sets: ${state.teamBSets}"
                    )

                    Text(

                        "${state.teamBPoints}",

                        style =
                            MaterialTheme.typography.displayLarge,

                        fontWeight =
                            FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun VolleyballScoringControls(

    onTeamA: () -> Unit,

    onTeamB: () -> Unit,

    onEndSet: () -> Unit
) {

    Column(
        modifier =
            Modifier.fillMaxSize()
    ) {

        Row(
            modifier =
                Modifier.fillMaxWidth(),

            horizontalArrangement =
                Arrangement.SpaceBetween
        ) {

            Button(

                onClick = {

                    onTeamA()
                },

                modifier = Modifier
                    .weight(1f)
                    .height(80.dp),

                shape =
                    RoundedCornerShape(12.dp)
            ) {

                Text(
                    "+1 Spikers",

                    style =
                        MaterialTheme.typography.titleMedium
                )
            }

            Spacer(
                modifier =
                    Modifier.padding(8.dp)
            )

            Button(

                onClick = {

                    onTeamB()
                },

                modifier = Modifier
                    .weight(1f)
                    .height(80.dp),

                shape =
                    RoundedCornerShape(12.dp),

                colors =
                    ButtonDefaults.buttonColors(

                        containerColor =
                            MaterialTheme.colorScheme.secondary
                    )
            ) {

                Text(

                    "+1 Blockers",

                    style =
                        MaterialTheme.typography.titleMedium
                )
            }
        }

        Spacer(
            modifier =
                Modifier.height(32.dp)
        )

        Row(
            modifier =
                Modifier.fillMaxWidth(),

            horizontalArrangement =
                Arrangement.SpaceBetween
        ) {

            Button(

                onClick = {},

                modifier =
                    Modifier.weight(1f),

                shape =
                    RoundedCornerShape(12.dp),

                colors =
                    ButtonDefaults.buttonColors(

                        containerColor =
                            MaterialTheme.colorScheme.surfaceVariant
                    )
            ) {

                Text(
                    "Timeout Spikers"
                )
            }

            Spacer(
                modifier =
                    Modifier.padding(8.dp)
            )

            Button(

                onClick = {},

                modifier =
                    Modifier.weight(1f),

                shape =
                    RoundedCornerShape(12.dp),

                colors =
                    ButtonDefaults.buttonColors(

                        containerColor =
                            MaterialTheme.colorScheme.surfaceVariant
                    )
            ) {

                Text(
                    "Timeout Blockers"
                )
            }
        }

        Spacer(
            modifier =
                Modifier.height(16.dp)
        )

        Button(

            onClick = {

                onEndSet()
            },

            modifier =
                Modifier.fillMaxWidth(),

            shape =
                RoundedCornerShape(12.dp),

            colors =
                ButtonDefaults.buttonColors(

                    containerColor =
                        MaterialTheme.colorScheme.tertiary
                )
        ) {

            Text(

                "End Set",

                fontWeight =
                    FontWeight.Bold
            )
        }
    }
}