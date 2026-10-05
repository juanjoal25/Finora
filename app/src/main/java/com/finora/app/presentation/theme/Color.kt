package com.finora.app.presentation.theme

import androidx.compose.ui.graphics.Color

/**
 * Exact color tokens from the "Fintech Precision" design system
 * (Diseño - Finora/logo/DESIGN.md). These are the authoritative brand values — do not
 * substitute approximate colors elsewhere in the app.
 */

// Light scheme — taken verbatim from DESIGN.md
val LightPrimary = Color(0xFF000F22)
val LightOnPrimary = Color(0xFFFFFFFF)
val LightPrimaryContainer = Color(0xFF0A2540)
val LightOnPrimaryContainer = Color(0xFF768DAD)
val LightInversePrimary = Color(0xFFB0C8EB)

val LightSecondary = Color(0xFF006C4A)
val LightOnSecondary = Color(0xFFFFFFFF)
val LightSecondaryContainer = Color(0xFF82F5C1)
val LightOnSecondaryContainer = Color(0xFF00714E)

val LightTertiary = Color(0xFF030046)
val LightOnTertiary = Color(0xFFFFFFFF)
val LightTertiaryContainer = Color(0xFF0A0085)
val LightOnTertiaryContainer = Color(0xFF797DFF)

val LightError = Color(0xFFBA1A1A)
val LightOnError = Color(0xFFFFFFFF)
val LightErrorContainer = Color(0xFFFFDAD6)
val LightOnErrorContainer = Color(0xFF93000A)

val LightBackground = Color(0xFFFAF8FF)
val LightOnBackground = Color(0xFF131B2E)
val LightSurface = Color(0xFFFAF8FF)
val LightSurfaceDim = Color(0xFFD2D9F4)
val LightSurfaceBright = Color(0xFFFAF8FF)
val LightSurfaceContainerLowest = Color(0xFFFFFFFF)
val LightSurfaceContainerLow = Color(0xFFF2F3FF)
val LightSurfaceContainer = Color(0xFFEAEDFF)
val LightSurfaceContainerHigh = Color(0xFFE2E7FF)
val LightSurfaceContainerHighest = Color(0xFFDAE2FD)
val LightSurfaceVariant = Color(0xFFDAE2FD)
val LightOnSurface = Color(0xFF131B2E)
val LightOnSurfaceVariant = Color(0xFF43474D)
val LightOutline = Color(0xFF74777E)
val LightOutlineVariant = Color(0xFFC4C6CE)
val LightInverseSurface = Color(0xFF283044)
val LightInverseOnSurface = Color(0xFFEEF0FF)
val LightSurfaceTint = Color(0xFF49607E)

// "Fixed" tokens — used as the source for a derived dark scheme (DESIGN.md defines these
// as light-independent brand anchors, which is exactly what M3 dark schemes need).
val PrimaryFixed = Color(0xFFD2E4FF)
val PrimaryFixedDim = Color(0xFFB0C8EB)
val OnPrimaryFixed = Color(0xFF001C37)
val OnPrimaryFixedVariant = Color(0xFF314865)

val SecondaryFixed = Color(0xFF85F8C4)
val SecondaryFixedDim = Color(0xFF68DBA9)
val OnSecondaryFixed = Color(0xFF002114)
val OnSecondaryFixedVariant = Color(0xFF005137)

val TertiaryFixed = Color(0xFFE1E0FF)
val TertiaryFixedDim = Color(0xFFC0C1FF)
val OnTertiaryFixed = Color(0xFF07006C)
val OnTertiaryFixedVariant = Color(0xFF2F2EBE)

// Standard M3 baseline dark error pair (DESIGN.md only specifies light error tokens).
val DarkError = Color(0xFFFFB4AB)
val DarkOnError = Color(0xFF690005)
val DarkErrorContainer = Color(0xFF93000A)
val DarkOnErrorContainer = Color(0xFFFFDAD6)

// Logo-only accent colors (used exclusively by ic_finora_logo / launcher icon gradients).
val LogoTileBackground = Color(0xFF0A2540)
val LogoBarBlueStart = Color(0xFF3B82F6)
val LogoBarBlueMid = Color(0xFF2563EB)
val LogoBarGreenEnd = Color(0xFF10B981)
val LogoBarNavy = Color(0xFF1E3A8A)
val LogoTrendLine = Color(0xFF60A5FA)
val LogoTrendDot = Color(0xFF34D399)
val LogoHorizonLine = Color(0xFF1E293B)
