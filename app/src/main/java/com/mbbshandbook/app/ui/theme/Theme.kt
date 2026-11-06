package com.mbbshandbook.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// ---------------------------------------------------------------------------
// Dark fintech-inspired palette (locked dark, matching the design reference):
// near-black teal background, dark elevated surfaces, glowing teal gradient
// cards and a bright mint/cyan accent for actions and highlights.
// ---------------------------------------------------------------------------

val TealBackground = Color(0xFF0A1411)
val TealSurface = Color(0xFF111D18)
val TealSurfaceHigh = Color(0xFF172620)
val TealOutline = Color(0xFF27362F)
val MintAccent = Color(0xFF2BE3C3)
val MintDim = Color(0xFF8FE9D6)
val TextPrimaryDark = Color(0xFFECF4F0)
val TextSecondaryDark = Color(0xFF9DB4AB)
val AmberDark = Color(0xFFFFC66B)
val GlowGradient = listOf(Color(0xFF1F5244), Color(0xFF12352C), Color(0xFF0C221C))

private val FintechDarkColors = darkColorScheme(
    primary = MintAccent,
    onPrimary = Color(0xFF03211B),
    primaryContainer = Color(0xFF12352C),
    onPrimaryContainer = Color(0xFFB5F4E4),
    inversePrimary = Color(0xFF0B6E4F),
    secondary = MintDim,
    onSecondary = Color(0xFF03211B),
    secondaryContainer = Color(0xFF182C25),
    onSecondaryContainer = Color(0xFFCFEEE3),
    tertiary = AmberDark,
    onTertiary = Color(0xFF2B1700),
    tertiaryContainer = Color(0xFF33270F),
    onTertiaryContainer = Color(0xFFFFE3B3),
    background = TealBackground,
    onBackground = TextPrimaryDark,
    surface = TealSurface,
    onSurface = TextPrimaryDark,
    surfaceVariant = TealSurfaceHigh,
    onSurfaceVariant = TextSecondaryDark,
    surfaceBright = Color(0xFF2A3A32),
    surfaceDim = TealBackground,
    surfaceContainerLowest = Color(0xFF060F0C),
    surfaceContainerLow = TealSurface,
    surfaceContainer = Color(0xFF14211B),
    surfaceContainerHigh = TealSurfaceHigh,
    surfaceContainerHighest = Color(0xFF1C2E26),
    surfaceTint = MintAccent,
    inverseSurface = Color(0xFFECF4F0),
    inverseOnSurface = Color(0xFF0A1411),
    outline = Color(0xFF5F7A70),
    outlineVariant = TealOutline,
    scrim = Color(0xFF000000)
)

/**
 * The app ships a single dark fintech-style theme to match the design
 * reference, regardless of the system light/dark setting.
 */
@Composable
fun MbbsHandbookTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = FintechDarkColors,
        typography = MbbsTypography,
        content = content
    )
}
