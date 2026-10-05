package com.finora.app.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * The three top-level tabs. "Agregar" is intentionally not here — it's a visually
 * emphasized center action that opens a bottom sheet rather than a nav destination.
 */
enum class BottomNavItem(
    val route: String,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
) {
    HOME(Routes.Home.route, "Inicio", Icons.Filled.Home, Icons.Outlined.Home),
    MOVEMENTS(Routes.Movements.route, "Movimientos", Icons.Filled.List, Icons.Outlined.List),
    PROFILE(Routes.Profile.route, "Perfil", Icons.Filled.AccountCircle, Icons.Outlined.AccountCircle),
}
