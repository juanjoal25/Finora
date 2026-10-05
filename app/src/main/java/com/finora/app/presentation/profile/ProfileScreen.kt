package com.finora.app.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.finora.app.core.utils.DateFormatter
import com.finora.app.domain.model.ThemeMode
import com.finora.app.presentation.components.ConfirmDialog
import com.finora.app.presentation.components.ErrorState

import com.finora.app.presentation.components.LoadingState
import com.finora.app.presentation.theme.FinoraPillShape
import com.finora.app.presentation.theme.FinoraShapes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onNavigateToPersonalInfo: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onNavigateToSecurity: () -> Unit,
    onNavigateToCurrency: () -> Unit,
    onNavigateToAbout: () -> Unit,
    onLoggedOut: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel(),
    settingsViewModel: SettingsViewModel = hiltViewModel(),
    currencyViewModel: CurrencyViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val themeMode by settingsViewModel.themeMode.collectAsState()
    val currency by currencyViewModel.currency.collectAsState()
    val isSystemDark = isSystemInDarkTheme()
    var showLogoutDialog by remember { mutableStateOf(false) }

    Scaffold(
    ) { padding ->
        when (val state = uiState) {
            is ProfileUiState.Loading -> LoadingState(modifier = Modifier.padding(padding))
            is ProfileUiState.Error -> ErrorState(message = state.message, modifier = Modifier.padding(padding))
            is ProfileUiState.Success -> Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp, vertical = 16.dp),
            ) {
                ProfileHeroCard(
                    name = state.user.name,
                    email = state.user.email,
                    memberSince = DateFormatter.monthYear(state.user.createdAt),
                    onEditClick = onNavigateToPersonalInfo,
                )
                Spacer(modifier = Modifier.height(24.dp))
                SectionLabel("CUENTA")
                ProfileMenuItem(
                    icon = Icons.Filled.Lock,
                    label = "Seguridad",
                    onClick = onNavigateToSecurity,
                )
                Spacer(modifier = Modifier.height(16.dp))
                SectionLabel("PREFERENCIAS")
                ProfileMenuItem(
                    icon = Icons.Filled.Notifications,
                    label = "Notificaciones",
                    subtitle = "Alertas de transacciones y cobros",
                    trailingText = "Próximamente",
                    enabled = false,
                )
                ProfileMenuItem(
                    icon = Icons.Filled.AttachMoney,
                    label = "Moneda",
                    subtitle = "Divisa principal para tus balances",
                    trailingText = "${currency.code} (${currency.symbol})",
                    onClick = onNavigateToCurrency,
                )
                ProfileMenuItem(
                    icon = Icons.Filled.Language,
                    label = "Idioma",
                    subtitle = "Idioma de la interfaz",
                    trailingText = "Próximamente",
                    enabled = false,
                )
                ProfileMenuItem(
                    icon = Icons.Filled.Palette,
                    label = "Apariencia",
                    subtitle = "Tema visual de la interfaz",
                    trailingText = appearanceLabel(themeMode, isSystemDark),
                    onClick = onNavigateToSettings,
                )
                Spacer(modifier = Modifier.height(16.dp))
                SectionLabel("SOPORTE")
                ProfileMenuItem(
                    icon = Icons.Filled.Info,
                    label = "Acerca de Finora",
                    subtitle = "Créditos y versión de la app",
                    onClick = onNavigateToAbout,
                )
                Spacer(modifier = Modifier.height(24.dp))
                LogoutCard(onClick = { showLogoutDialog = true })
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }

    if (showLogoutDialog) {
        ConfirmDialog(
            title = "¿Quieres cerrar sesión?",
            message = "Tendrás que iniciar sesión nuevamente para acceder a tu cuenta.",
            confirmText = "Cerrar sesión",
            isDestructive = true,
            onConfirm = {
                showLogoutDialog = false
                viewModel.onLogoutConfirmed()
                onLoggedOut()
            },
            onDismiss = { showLogoutDialog = false },
        )
    }
}

private fun appearanceLabel(themeMode: ThemeMode, isSystemDark: Boolean): String = when (themeMode) {
    ThemeMode.LIGHT -> "Claro"
    ThemeMode.DARK -> "Oscuro"
    ThemeMode.SYSTEM -> "${if (isSystemDark) "Oscuro" else "Claro"} (Automático)"
}

@Composable
private fun ProfileHeroCard(name: String, email: String, memberSince: String, onEditClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = FinoraShapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
        ),
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                OutlinedButton(onClick = onEditClick, shape = FinoraPillShape) {
                    Text("Editar perfil", color = MaterialTheme.colorScheme.primary)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondaryContainer),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier.size(48.dp),
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = name,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Text(
                text = email,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Spacer(modifier = Modifier.height(12.dp))
            Box(
                modifier = Modifier
                    .clip(FinoraPillShape)
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .padding(horizontal = 12.dp, vertical = 6.dp),
            ) {
                Text(
                    text = "Miembro desde $memberSince",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                )
            }
        }
    }
}

@Composable
private fun SectionLabel(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = Modifier.padding(vertical = 8.dp),
    )
}

@Composable
private fun ProfileMenuItem(
    icon: ImageVector,
    label: String,
    onClick: (() -> Unit)? = null,
    subtitle: String? = null,
    trailingText: String? = null,
    enabled: Boolean = true,
    tint: Color = MaterialTheme.colorScheme.onSurface,
) {
    val contentAlpha = if (enabled) 1f else 0.5f
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .let { if (enabled && onClick != null) it.clickable(onClick = onClick) else it }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(FinoraShapes.medium)
                    .background(MaterialTheme.colorScheme.surfaceContainerHighest.copy(alpha = contentAlpha)),
                contentAlignment = Alignment.Center,
            ) {
                Icon(imageVector = icon, contentDescription = null, tint = tint.copy(alpha = contentAlpha))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = label,
                    color = tint.copy(alpha = contentAlpha),
                    style = MaterialTheme.typography.bodyLarge,
                )
                if (subtitle != null) {
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = contentAlpha),
                    )
                }
            }
        }
        if (trailingText != null) {
            Text(
                text = trailingText,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = contentAlpha),
            )
            Spacer(modifier = Modifier.width(4.dp))
        }
        if (enabled && onClick != null) {
            Icon(
                imageVector = Icons.Filled.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

@Composable
private fun LogoutCard(onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = FinoraShapes.large,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.Logout,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onErrorContainer,
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = "Cerrar sesión",
                        color = MaterialTheme.colorScheme.onErrorContainer,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                    Text(
                        text = "Se cerrará la sesión en este dispositivo",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onErrorContainer,
                    )
                }
            }
            Icon(
                imageVector = Icons.Filled.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onErrorContainer,
            )
        }
    }
}
