package com.gramakalyana.sports.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

sealed class BottomNavItem(
    var title: String,
    var icon: ImageVector,
    var route: String
) {

    object Home :
        BottomNavItem(
            "Home",
            Icons.Default.Home,
            "home"
        )

    object Live :
        BottomNavItem(
            "Live",
            Icons.Default.PlayArrow,
            "live_matches"
        )
}

@Composable
fun AnimatedBottomBar(

    currentRoute: String?,

    onNavigate: (String) -> Unit
) {

    val items = listOf(

        BottomNavItem.Home,

        BottomNavItem.Live
    )

    Row(

        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(
                horizontal = 18.dp,
                vertical = 10.dp
            )
            .clip(
                RoundedCornerShape(26.dp)
            )
            .background(
                MaterialTheme.colorScheme.surface.copy(
                    alpha = 0.96f
                )
            )
            .padding(
                horizontal = 10.dp,
                vertical = 8.dp
            ),

        horizontalArrangement =
            Arrangement.SpaceEvenly,

        verticalAlignment =
            Alignment.CenterVertically
    ) {

        items.forEach { item ->

            val isSelected =
                currentRoute == item.route

            BottomBarItem(

                item = item,

                isSelected = isSelected,

                onClick = {

                    onNavigate(item.route)
                }
            )
        }
    }
}

@Composable
fun BottomBarItem(

    item: BottomNavItem,

    isSelected: Boolean,

    onClick: () -> Unit
) {

    val background =

        if (isSelected)

            MaterialTheme.colorScheme.primary.copy(
                alpha = 0.15f
            )

        else
            Color.Transparent

    val contentColor =

        if (isSelected)

            MaterialTheme.colorScheme.primary

        else

            MaterialTheme.colorScheme
                .onSurface
                .copy(alpha = 0.55f)

    Box(

        modifier = Modifier
            .clip(
                RoundedCornerShape(18.dp)
            )
            .background(background)
            .clickable(
                onClick = onClick
            )
            .padding(
                horizontal = 18.dp,
                vertical = 10.dp
            )
    ) {

        Row(

            verticalAlignment =
                Alignment.CenterVertically,

            horizontalArrangement =
                Arrangement.Center
        ) {

            Icon(

                imageVector =
                    item.icon,

                contentDescription =
                    item.title,

                tint = contentColor
            )

            AnimatedVisibility(
                visible = isSelected
            ) {

                Text(

                    text =
                        item.title,

                    color =
                        contentColor,

                    modifier =
                        Modifier.padding(
                            start = 8.dp
                        ),

                    style =
                        MaterialTheme.typography.labelLarge,

                    fontWeight =
                        FontWeight.Bold
                )
            }
        }
    }
}