package com.finora.app.presentation.movements.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.finora.app.presentation.components.EmptyState
import com.finora.app.presentation.components.ErrorState
import com.finora.app.presentation.components.FinoraTopBar
import com.finora.app.presentation.components.LoadingState
import com.finora.app.presentation.components.TransactionItem
import com.finora.app.presentation.theme.FinoraPillShape

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovementsScreen(
    onTransactionClick: (String) -> Unit,
    viewModel: MovementsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { FinoraTopBar(title = "Movimientos") },
    ) { padding ->
        when (val state = uiState) {
            is MovementsUiState.Loading -> LoadingState(modifier = Modifier.padding(padding))
            is MovementsUiState.Error -> ErrorState(message = state.message, modifier = Modifier.padding(padding))
            is MovementsUiState.Empty -> EmptyState(
                title = "Aún no tienes movimientos",
                message = "Registra tu primer ingreso o gasto desde Inicio.",
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
            )
            is MovementsUiState.Success -> MovementsContent(
                state = state,
                onFilterChange = viewModel::onFilterChange,
                onQueryChange = viewModel::onQueryChange,
                onTransactionClick = onTransactionClick,
                modifier = Modifier.padding(padding),
            )
        }
    }
}

@Composable
private fun MovementsContent(
    state: MovementsUiState.Success,
    onFilterChange: (MovementsFilter) -> Unit,
    onQueryChange: (String) -> Unit,
    onTransactionClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
    ) {
        OutlinedTextField(
            value = state.query,
            onValueChange = onQueryChange,
            placeholder = { Text("Buscar movimientos") },
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            FilterChip(
                selected = state.filter == MovementsFilter.ALL,
                onClick = { onFilterChange(MovementsFilter.ALL) },
                label = { Text("Todos") },
                shape = FinoraPillShape,
            )
            FilterChip(
                selected = state.filter == MovementsFilter.INCOME,
                onClick = { onFilterChange(MovementsFilter.INCOME) },
                label = { Text("Ingresos") },
                shape = FinoraPillShape,
            )
            FilterChip(
                selected = state.filter == MovementsFilter.EXPENSE,
                onClick = { onFilterChange(MovementsFilter.EXPENSE) },
                label = { Text("Gastos") },
                shape = FinoraPillShape,
            )
        }
        if (state.transactions.isEmpty()) {
            Text(
                text = "No se encontraron movimientos",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        } else {
            LazyColumn(contentPadding = PaddingValues(bottom = 24.dp)) {
                items(state.transactions, key = { it.id }) { transaction ->
                    TransactionItem(transaction = transaction, onClick = { onTransactionClick(transaction.id) })
                }
            }
        }
    }
}