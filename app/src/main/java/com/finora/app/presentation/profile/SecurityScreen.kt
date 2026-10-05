package com.finora.app.presentation.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.Password
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Placeholder for v1: the screens are wired up and reachable, but "Cambiar contraseña" and
 * biometrics are not functional yet since there is no real backend/auth to secure.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecurityScreen(onNavigateBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Seguridad") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            ListItem(
                headlineContent = { Text("Cambiar contraseña") },
                supportingContent = { Text("Disponible próximamente") },
                leadingContent = { Icon(Icons.Filled.Password, contentDescription = null) },
            )
            ListItem(
                headlineContent = { Text("Biometría") },
                supportingContent = { Text("Disponible próximamente") },
                leadingContent = { Icon(Icons.Filled.Fingerprint, contentDescription = null) },
            )
        }
    }
}
