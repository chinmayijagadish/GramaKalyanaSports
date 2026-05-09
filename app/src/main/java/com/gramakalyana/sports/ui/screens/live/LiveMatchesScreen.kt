package com.gramakalyana.sports.ui.screens.live

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
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
import com.gramakalyana.sports.navigation.Screen
import com.gramakalyana.sports.ui.components.GlassmorphismCard
import com.gramakalyana.sports.ui.components.LiveBadge

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LiveMatchesScreen(navController: NavController) {

    var selectedFilter by remember {
        mutableStateOf("All")
    }

    val filters = listOf(
        "All",
        "Cricket",
        "Kabaddi",
        "Volleyball"
    )

    val matches = listOf(
        "Cricket",
        "Kabaddi",
        "Volleyball",
        "Cricket",
        "Kabaddi"
    )

    val filteredMatches =
        if (selectedFilter == "All")
            matches
        else
            matches.filter { it == selectedFilter }

    Scaffold(

        topBar = {

            TopAppBar(
                title = {
                    Text(
                        text = "Live Matches",
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
        ) {

            LazyRow(
                modifier = Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 12.dp
                ),

                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                items(filters) { filter ->

                    FilterChip(
                        selected = selectedFilter == filter,

                        onClick = {
                            selectedFilter = filter
                        },

                        label = {
                            Text(filter)
                        },

                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor =
                                MaterialTheme.colorScheme.primary,

                            selectedLabelColor =
                                MaterialTheme.colorScheme.onPrimary
                        )
                    )
                }
            }

            Text(
                text = "Watch ongoing village tournaments live",
                style = MaterialTheme.typography.bodyMedium,

                color = MaterialTheme.colorScheme.onSurface.copy(
                    alpha = 0.7f
                ),

                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(14.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),

                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {

                items(filteredMatches.size) { index ->

                    DetailedLiveMatchCard(

                        sportType = filteredMatches[index],

                        onClick = {

                            navController.navigate(
                                Screen.MatchDetails.createRoute(
                                    "match_$index"
                                )
                            )
                        }
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(90.dp))
                }
            }
        }
    }
}

@Composable
fun DetailedLiveMatchCard(
    sportType: String,
    onClick: () -> Unit
) {

    GlassmorphismCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {

        Column(
            modifier = Modifier.padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = "$sportType - Semi Final",
                    style = MaterialTheme.typography.labelMedium,

                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.weight(1f))

                LiveBadge()
            }

            Row(
                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement =
                    Arrangement.SpaceBetween,

                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Riders FC",

                    style = MaterialTheme.typography.titleLarge,

                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "vs",

                    color = MaterialTheme.colorScheme.onSurface.copy(
                        alpha = 0.5f
                    )
                )

                Text(
                    text = "Vikings",

                    style = MaterialTheme.typography.titleLarge,

                    fontWeight = FontWeight.Bold
                )
            }

            if (sportType == "Cricket") {

                Row(
                    modifier = Modifier.fillMaxWidth(),

                    horizontalArrangement =
                        Arrangement.SpaceBetween
                ) {

                    Text(
                        text = "143/5 (18.1)",

                        style = MaterialTheme.typography.titleMedium,

                        color = MaterialTheme.colorScheme.primary,

                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Yet to bat",

                        style = MaterialTheme.typography.bodyMedium
                    )
                }

            } else if (sportType == "Kabaddi") {

                Row(
                    modifier = Modifier.fillMaxWidth(),

                    horizontalArrangement =
                        Arrangement.SpaceBetween
                ) {

                    Text(
                        text = "24 Points",

                        style = MaterialTheme.typography.titleMedium,

                        color = MaterialTheme.colorScheme.primary,

                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "19 Points",

                        style = MaterialTheme.typography.titleMedium,

                        color = MaterialTheme.colorScheme.secondary,

                        fontWeight = FontWeight.Bold
                    )
                }

            } else {

                Row(
                    modifier = Modifier.fillMaxWidth(),

                    horizontalArrangement =
                        Arrangement.SpaceBetween
                ) {

                    Text(
                        text = "2 Sets",

                        style = MaterialTheme.typography.titleMedium,

                        color = MaterialTheme.colorScheme.primary,

                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "1 Set",

                        style = MaterialTheme.typography.titleMedium,

                        color = MaterialTheme.colorScheme.secondary,

                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}