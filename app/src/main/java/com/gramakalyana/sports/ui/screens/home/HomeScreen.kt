package com.gramakalyana.sports.ui.screens.home

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gramakalyana.sports.navigation.Screen
import com.gramakalyana.sports.ui.components.AnimatedBottomBar
import com.gramakalyana.sports.utils.SelectedZone

@Composable
fun HomeScreen(
    navController: NavController
) {

    val scrollState =
        rememberScrollState()

    Scaffold(

        bottomBar = {

            AnimatedBottomBar(

                currentRoute =
                    Screen.Home.route,

                onNavigate = { route ->

                    navController.navigate(route) {

                        popUpTo(
                            Screen.Home.route
                        ) {

                            saveState = true
                        }

                        launchSingleTop = true

                        restoreState = true
                    }
                }
            )
        }

    ) { paddingValues ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .background(
                    MaterialTheme.colorScheme.background
                ),

            verticalArrangement =
                Arrangement.spacedBy(22.dp)
        ) {

            HomeHeader()

            SelectedZoneCard()

            QuickActions(
                navController
            )

            SportsCategories(
                navController
            )

            RecentHighlights()

            Spacer(
                modifier =
                    Modifier.height(100.dp)
            )
        }
    }
}

@Composable
fun HomeHeader() {

    Box(

        modifier = Modifier
            .fillMaxWidth()

            .clip(

                RoundedCornerShape(

                    bottomStart = 34.dp,

                    bottomEnd = 34.dp
                )
            )

            .background(
                MaterialTheme.colorScheme.primary
            )

            .padding(

                horizontal = 24.dp,

                vertical = 40.dp
            )
    ) {

        Column {

            Text(

                text =
                    "Grama-Kalyana Sports",

                style =
                    MaterialTheme.typography.headlineMedium,

                color = Color.White,

                fontWeight =
                    FontWeight.ExtraBold
            )

            Spacer(
                modifier =
                    Modifier.height(12.dp)
            )

            Text(

                text =
                    "Village Tournament Management & Live Scoring",

                style =
                    MaterialTheme.typography.bodyLarge,

                color =
                    Color.White.copy(
                        alpha = 0.9f
                    )
            )

            Spacer(
                modifier =
                    Modifier.height(18.dp)
            )

            Card(

                shape =
                    RoundedCornerShape(16.dp),

                colors =
                    CardDefaults.cardColors(

                        containerColor =
                            Color.White.copy(alpha = 0.15f)
                    )
            ) {

                Text(

                    text =
                        "🔥 LIVE Sports Experience",

                    modifier =
                        Modifier.padding(
                            horizontal = 16.dp,
                            vertical = 10.dp
                        ),

                    color =
                        Color.White,

                    fontWeight =
                        FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun SelectedZoneCard() {

    Card(

        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),

        shape =
            RoundedCornerShape(20.dp),

        colors =
            CardDefaults.cardColors(

                containerColor =
                    MaterialTheme.colorScheme.primaryContainer
            )
    ) {

        Column(
            modifier =
                Modifier.padding(18.dp)
        ) {

            Text(

                text =
                    "Selected Zone",

                fontWeight =
                    FontWeight.Bold
            )

            Spacer(
                modifier =
                    Modifier.height(8.dp)
            )

            Text(

                text =

                    if (
                        SelectedZone.selectedZone.isBlank()
                    )

                        "No zone selected"

                    else

                        SelectedZone.selectedZone,

                style =
                    MaterialTheme.typography.titleLarge,

                fontWeight =
                    FontWeight.ExtraBold
            )
        }
    }
}

@Composable
fun QuickActions(
    navController: NavController
) {

    Column(

        modifier =
            Modifier.padding(horizontal = 16.dp)
    ) {

        Text(

            text =
                "Quick Actions",

            style =
                MaterialTheme.typography.titleLarge,

            fontWeight =
                FontWeight.Bold
        )

        Spacer(
            modifier =
                Modifier.height(14.dp)
        )

        Row(

            modifier =
                Modifier.fillMaxWidth(),

            horizontalArrangement =
                Arrangement.spacedBy(14.dp)
        ) {

            HomeActionButton(

                text = "Select Zone",

                modifier =
                    Modifier.weight(1f)

            ) {

                navController.navigate(
                    Screen.ZoneSelection.route
                )
            }

            HomeActionButton(

                text = "Scorer Login",

                modifier =
                    Modifier.weight(1f)

            ) {

                navController.navigate(
                    Screen.ScorerLogin.route
                )
            }
        }
    }
}

@Composable
fun HomeActionButton(

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
            RoundedCornerShape(18.dp),

        colors =
            ButtonDefaults.buttonColors(

                containerColor =
                    MaterialTheme.colorScheme.secondary
            )
    ) {

        Text(

            text = text,

            fontWeight =
                FontWeight.Bold
        )
    }
}

@Composable
fun SportsCategories(
    navController: NavController
) {

    Column(

        modifier =
            Modifier.padding(horizontal = 16.dp)
    ) {

        Text(

            text =
                "Sports Categories",

            style =
                MaterialTheme.typography.titleLarge,

            fontWeight =
                FontWeight.Bold
        )

        Spacer(
            modifier =
                Modifier.height(14.dp)
        )

        Row(

            modifier =
                Modifier.fillMaxWidth(),

            horizontalArrangement =
                Arrangement.spacedBy(12.dp)
        ) {

            SportCard(

                emoji = "🏏",

                title = "Cricket",

                modifier =
                    Modifier.weight(1f)

            ) {

                SelectedZone.selectedSport =
                    "Cricket"

                navController.navigate(
                    Screen.LiveMatches.route
                )
            }

            SportCard(

                emoji = "🤼",

                title = "Kabaddi",

                modifier =
                    Modifier.weight(1f)

            ) {

                SelectedZone.selectedSport =
                    "Kabaddi"

                navController.navigate(
                    Screen.LiveMatches.route
                )
            }

            SportCard(

                emoji = "🏐",

                title = "Volleyball",

                modifier =
                    Modifier.weight(1f)

            ) {

                SelectedZone.selectedSport =
                    "Volleyball"

                navController.navigate(
                    Screen.LiveMatches.route
                )
            }
        }
    }
}

@Composable
fun SportCard(

    emoji: String,

    title: String,

    modifier: Modifier =
        Modifier,

    onClick: () -> Unit
) {

    Card(

        modifier = modifier
            .height(160.dp)
            .clickable(
                onClick = onClick
            ),

        shape =
            RoundedCornerShape(24.dp),

        colors =
            CardDefaults.cardColors(

                containerColor =
                    MaterialTheme.colorScheme.surfaceVariant
            )
    ) {

        Box(

            modifier =
                Modifier.fillMaxSize(),

            contentAlignment =
                Alignment.Center
        ) {

            Column(

                horizontalAlignment =
                    Alignment.CenterHorizontally
            ) {

                Text(

                    text = emoji,

                    style =
                        MaterialTheme.typography.headlineMedium
                )

                Spacer(
                    modifier =
                        Modifier.height(12.dp)
                )

                Text(

                    text = title,

                    style =
                        MaterialTheme.typography.titleMedium,

                    fontWeight =
                        FontWeight.Bold
                )

                Spacer(
                    modifier =
                        Modifier.height(8.dp)
                )

                Text(
                    text = "View Matches"
                )
            }
        }
    }
}

@Composable
fun RecentHighlights() {

    Column(

        modifier =
            Modifier.padding(horizontal = 16.dp)
    ) {

        Text(

            text =
                "Highlights",

            style =
                MaterialTheme.typography.titleLarge,

            fontWeight =
                FontWeight.Bold
        )

        Spacer(
            modifier =
                Modifier.height(14.dp)
        )

        Card(

            modifier =
                Modifier.fillMaxWidth(),

            shape =
                RoundedCornerShape(22.dp),

            colors =
                CardDefaults.cardColors(

                    containerColor =
                        MaterialTheme.colorScheme.primaryContainer
                )
        ) {

            Column(
                modifier =
                    Modifier.padding(20.dp)
            ) {

                Text(

                    text =
                        "🔥 Live Tournament Updates",

                    fontWeight =
                        FontWeight.ExtraBold
                )

                Spacer(
                    modifier =
                        Modifier.height(10.dp)
                )

                Text(
                    text =
                        "Track Cricket, Kabaddi and Volleyball matches in real-time with professional scoring dashboards."
                )
            }
        }
    }
}