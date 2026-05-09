package com.gramakalyana.sports.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import com.gramakalyana.sports.ui.theme.DarkGradientEnd
import com.gramakalyana.sports.ui.theme.DarkGradientStart
import com.gramakalyana.sports.ui.theme.GradientEnd
import com.gramakalyana.sports.ui.theme.GradientStart

@Composable
fun GradientBackground(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    val isDark = isSystemInDarkTheme()
    val startColor = if (isDark) DarkGradientStart else GradientStart
    val endColor = if (isDark) DarkGradientEnd else GradientEnd

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(startColor, endColor)
                )
            )
    ) {
        content()
    }
}
