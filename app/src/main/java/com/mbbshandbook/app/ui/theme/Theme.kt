package com.mbbshandbook.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val GreenPrimary = Color(0xFF0B6E4F)
private val GreenDark = Color(0xFF084C37)
private val GreenLight = Color(0xFFD7EDE4)
private val AmberAccent = Color(0xFFB26A00)

private val LightColors = lightColorScheme(
    primary = GreenPrimary,
    onPrimary = Color.White,
    primaryContainer = GreenLight,
    onPrimaryContainer = GreenDark,
    secondary = Color(0xFF4C6358),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFCFE9DB),
    onSecondaryContainer = Color(0xFF092016),
    tertiary = AmberAccent,
    tertiaryContainer = Color(0xFFFFDDB3),
    onTertiaryContainer = Color(0xFF2B1700),
    background = Color(0xFFF7FBF7),
    onBackground = Color(0xFF181D1A),
    surface = Color(0xFFF7FBF7),
    onSurface = Color(0xFF181D1A),
    surfaceVariant = Color(0xFFDCE5DD),
    onSurfaceVariant = Color(0xFF404943),
    outline = Color(0xFF707973)
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFF8ED6B5),
    onPrimary = Color(0xFF003826),
    primaryContainer = Color(0xFF005138),
    onPrimaryContainer = Color(0xFFA9F2D0),
    secondary = Color(0xFFB3CCBE),
    onSecondary = Color(0xFF1F352A),
    secondaryContainer = Color(0xFF354B40),
    onSecondaryContainer = Color(0xFFCFE9DB),
    tertiary = Color(0xFFFFB951),
    tertiaryContainer = Color(0xFF653E00),
    onTertiaryContainer = Color(0xFFFFDDB3),
    background = Color(0xFF101512),
    onBackground = Color(0xFFDFE4DF),
    surface = Color(0xFF101512),
    onSurface = Color(0xFFDFE4DF),
    surfaceVariant = Color(0xFF404943),
    onSurfaceVariant = Color(0xFFC0C9C1),
    outline = Color(0xFF8A938C)
)

@Composable
fun MbbsHandbookTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = MbbsTypography,
        content = content
    )
}
