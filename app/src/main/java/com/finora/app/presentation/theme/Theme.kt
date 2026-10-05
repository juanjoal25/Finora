package com.finora.app.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

private val LightColorScheme = lightColorScheme(
    primary = LightPrimary,
    onPrimary = LightOnPrimary,
    primaryContainer = LightPrimaryContainer,
    onPrimaryContainer = LightOnPrimaryContainer,
    inversePrimary = LightInversePrimary,
    secondary = LightSecondary,
    onSecondary = LightOnSecondary,
    secondaryContainer = LightSecondaryContainer,
    onSecondaryContainer = LightOnSecondaryContainer,
    tertiary = LightTertiary,
    onTertiary = LightOnTertiary,
    tertiaryContainer = LightTertiaryContainer,
    onTertiaryContainer = LightOnTertiaryContainer,
    background = LightBackground,
    onBackground = LightOnBackground,
    surface = LightSurface,
    onSurface = LightOnSurface,
    surfaceVariant = LightSurfaceVariant,
    onSurfaceVariant = LightOnSurfaceVariant,
    surfaceTint = LightSurfaceTint,
    inverseSurface = LightInverseSurface,
    inverseOnSurface = LightInverseOnSurface,
    error = LightError,
    onError = LightOnError,
    errorContainer = LightErrorContainer,
    onErrorContainer = LightOnErrorContainer,
    outline = LightOutline,
    outlineVariant = LightOutlineVariant,
    surfaceBright = LightSurfaceBright,
    surfaceDim = LightSurfaceDim,
    surfaceContainer = LightSurfaceContainer,
    surfaceContainerHigh = LightSurfaceContainerHigh,
    surfaceContainerHighest = LightSurfaceContainerHighest,
    surfaceContainerLow = LightSurfaceContainerLow,
    surfaceContainerLowest = LightSurfaceContainerLowest,
)

// DESIGN.md only specifies light-mode tokens; the dark scheme is derived from its
// "*-fixed" anchors, which is exactly what those tokens are meant for in Material 3.
private val DarkColorScheme = darkColorScheme(
    primary = PrimaryFixedDim,
    onPrimary = OnPrimaryFixed,
    primaryContainer = OnPrimaryFixedVariant,
    onPrimaryContainer = PrimaryFixed,
    inversePrimary = LightPrimary,
    secondary = SecondaryFixedDim,
    onSecondary = OnSecondaryFixed,
    secondaryContainer = OnSecondaryFixedVariant,
    onSecondaryContainer = SecondaryFixed,
    tertiary = TertiaryFixedDim,
    onTertiary = OnTertiaryFixed,
    tertiaryContainer = OnTertiaryFixedVariant,
    onTertiaryContainer = TertiaryFixed,
    background = LightInverseSurface,
    onBackground = LightInverseOnSurface,
    surface = LightInverseSurface,
    onSurface = LightInverseOnSurface,
    surfaceVariant = LightOnSurfaceVariant,
    onSurfaceVariant = LightOutlineVariant,
    surfaceTint = PrimaryFixedDim,
    inverseSurface = LightSurface,
    inverseOnSurface = LightOnSurface,
    error = DarkError,
    onError = DarkOnError,
    errorContainer = DarkErrorContainer,
    onErrorContainer = DarkOnErrorContainer,
    outline = LightOutline,
    outlineVariant = LightOnSurfaceVariant,
)

/**
 * Corner radius scale from DESIGN.md's `rounded` tokens (sm/DEFAULT/md/lg/xl/full), mapped
 * onto M3's 5-step Shapes scale: 4/8/12/16/24dp. Cards use 16dp, bottom sheets 24dp and
 * chips use the pill shape. Primary = navy (0xFF000F22), secondary = green (0xFF006C4A).
 * Components whose M3 defaults don't already pull from this (buttons, text fields, chips,
 * cards, bottom sheets) set their shape explicitly using these.
 */
val FinoraShapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp),
    extraLarge = RoundedCornerShape(24.dp),
)

val FinoraPillShape = RoundedCornerShape(50)

/** Shape used by filter and category chips (same pill shape, named for intent). */
val FinoraChipShape = FinoraPillShape

@Composable
fun FinoraTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = FinoraTypography,
        shapes = FinoraShapes,
        content = content,
    )
}