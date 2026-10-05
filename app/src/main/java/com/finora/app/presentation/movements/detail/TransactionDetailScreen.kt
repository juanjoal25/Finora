package com.finora.app.presentation.movements.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.finora.app.core.utils.DateFormatter
import com.finora.app.domain.model.TransactionType
import com.finora.app.presentation.components.ConfirmDialog
import com.finora.app.presentation.components.ErrorState
import com.finora.app.presentation.components.LoadingState
import com.finora.app.presentation.components.TransactionCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionDetailScreen(
    onNavigateBack: () -> Unit,
    onEdit: (transactionId: String, isIncome: Boolean) -> Unit,
    onDeleted: () -> Unit,
    viewModel: TransactionDetailViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    var showDeleteDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.deleted.collect { onDeleted() }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle de movimiento") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
            )
        },
    ) { padding ->
        when (val state = uiState) {
            is TransactionDetailUiState.Loading -> LoadingState(modifier = Modifier.padding(padding))
            is TransactionDetailUiState.Error ->
                ErrorState(message = state.message, modifier = Modifier.padding(padding))
            is TransactionDetailUiState.Success -> {
                val transaction = state.transaction
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(24.dp),
                ) {
                    TransactionCard(transaction = transaction)
                    Spacer(modifier = Modifier.height(20.dp))
                    if (!transaction.description.isNullOrBlank()) {
                        Text(text = "Descripción", style = MaterialTheme.typography.labelLarge)
                        Text(text = transaction.description, style = MaterialTheme.typography.bodyMedium)
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                    Text(text = "Fecha de creación", style = MaterialTheme.typography.labelLarge)
                    Text(
                        text = DateFormatter.fullDateTime(transaction.createdAt),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        OutlinedButton(
                            onClick = { onEdit(transaction.id, transaction.type == TransactionType.INCOME) },
                            modifier = Modifier.weight(1f),
                        ) { Text("Editar") }
                        Button(
                            onClick = { showDeleteDialog = true },
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                            modifier = Modifier.weight(1f),
                        ) { Text("Eliminar") }
                    }
                }
            }
        }
    }

    if (showDeleteDialog) {
        ConfirmDialog(
            title = "¿Eliminar movimiento?",
            message = "Esta acción no se puede deshacer.",
            confirmText = "Eliminar",
            isDestructive = true,
            onConfirm = {
                showDeleteDialog = false
                viewModel.onDeleteConfirmed()
            },
            onDismiss = { showDeleteDialog = false },
        )
    }
}
