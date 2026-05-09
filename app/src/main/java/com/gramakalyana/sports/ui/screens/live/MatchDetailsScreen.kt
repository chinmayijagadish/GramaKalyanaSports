package com.gramakalyana.sports.ui.screens.live

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gramakalyana.sports.ui.components.GlassmorphismCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchDetailsScreen(
    navController: NavController,
    matchId: String
) {

    Scaffold(

        topBar = {

            TopAppBar(
                title = {
                    Text(
                        text = "Match Details",
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
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
                .padding(16.dp),

            verticalArrangement = Arrangement.spacedBy(22.dp)
        ) {

            GlassmorphismCard(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier.padding(22.dp),
                    verticalArrangement = Arrangement.spacedBy(18.dp)
                ) {

                    Text(
                        text = "Cricket - Final",

                        color = MaterialTheme.colorScheme.primary,

                        style = MaterialTheme.typography.labelLarge
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),

                        horizontalArrangement =
                            Arrangement.spacedBy(20.dp),

                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Column(
                            modifier = Modifier.weight(1f),

                            horizontalAlignment =
                                Alignment.CenterHorizontally,

                            verticalArrangement =
                                Arrangement.spacedBy(6.dp)
                        ) {

                            Text(
                                text = "Riders FC",

                                fontWeight = FontWeight.Bold,

                                style = MaterialTheme.typography.titleLarge
                            )

                            Text(
                                text = "143/5",

                                color = MaterialTheme.colorScheme.primary,

                                style = MaterialTheme.typography.headlineSmall,

                                fontWeight = FontWeight.ExtraBold
                            )

                            Text(
                                text = "(18.1)",

                                style = MaterialTheme.typography.bodyMedium
                            )
                        }

                        Text(
                            text = "vs",

                            color = MaterialTheme.colorScheme.outline
                        )

                        Column(
                            modifier = Modifier.weight(1f),

                            horizontalAlignment =
                                Alignment.CenterHorizontally,

                            verticalArrangement =
                                Arrangement.spacedBy(6.dp)
                        ) {

                            Text(
                                text = "Vikings",

                                fontWeight = FontWeight.Bold,

                                style = MaterialTheme.typography.titleLarge
                            )

                            Text(
                                text = "-",

                                color = MaterialTheme.colorScheme.secondary,

                                style = MaterialTheme.typography.headlineSmall,

                                fontWeight = FontWeight.ExtraBold
                            )

                            Text(
                                text = "Yet to bat",

                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }

                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {

                        Text(
                            text = "Current Run Rate",

                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Text(
                            text = "CRR: 7.85",

                            fontWeight = FontWeight.Bold,

                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Text(
                    text = "Recent Deliveries",

                    style = MaterialTheme.typography.titleMedium,

                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Last Over",

                    style = MaterialTheme.typography.bodyMedium,

                    color = MaterialTheme.colorScheme.onSurface.copy(
                        alpha = 0.7f
                    )
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),

                    horizontalArrangement =
                        Arrangement.spacedBy(10.dp)
                ) {

                    listOf("1", "4", "W", "0", "2", "6")
                        .forEach { run ->

                            BallIndicator(text = run)
                        }
                }
            }
        }
    }
}

@Composable
fun BallIndicator(text: String) {

    val backgroundColor =
        when (text) {

            "W" -> MaterialTheme.colorScheme.error

            "4", "6" ->
                MaterialTheme.colorScheme.primary

            else ->
                MaterialTheme.colorScheme.surfaceVariant
        }

    val contentColor =
        if (
            text == "W" ||
            text == "4" ||
            text == "6"
        )
            Color.White
        else
            MaterialTheme.colorScheme.onSurfaceVariant

    Box(
        modifier = Modifier
            .size(42.dp)
            .background(
                backgroundColor,
                CircleShape
            ),

        contentAlignment = Alignment.Center
    ) {

        Text(
            text = text,

            color = contentColor,

            fontWeight = FontWeight.Bold
        )
    }
}