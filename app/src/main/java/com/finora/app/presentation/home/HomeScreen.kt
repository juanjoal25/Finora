package com.finora.app.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.finora.app.presentation.components.BalanceCard
import com.finora.app.presentation.components.EmptyState
import com.finora.app.presentation.components.ErrorState
import com.finora.app.presentation.components.FinanceButton
import com.finora.app.presentation.components.FinoraTopBar
import com.finora.app.presentation.components.IncomeExpenseCard
import com.finora.app.presentation.components.LoadingState
import com.finora.app.presentation.components.TransactionItem

@Composable
fun HomeScreen(
    onViewAllMovements: () -> Unit,
    onTransactionClick: (String) -> Unit,
    onAddMovement: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    Scaffold(topBar = { FinoraTopBar(title = "Inicio") }) { padding ->
        when (val state = uiState) {
            is HomeUiState.Loading -> LoadingState(modifier = Modifier.padding(padding))
            is HomeUiState.Error -> ErrorState(message = state.message, modifier = Modifier.padding(padding))
            is HomeUiState.Empty -> HomeEmptyContent(
                userName = state.userName,
                onAddMovement = onAddMovement,
                modifier = Modifier.padding(padding),
            )
            is HomeUiState.Success -> HomeContent(
                state = state,
                onToggleBalanceVisibility = viewModel::onToggleBalanceVisibility,
                onViewAllMovements = onViewAllMovements,
                onTransactionClick = onTransactionClick,
                modifier = Modifier.padding(padding),
            )
        }
    }
}

@Composable
private fun HomeEmptyContent(userName: String, onAddMovement: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
    ) {
        Text(text = "Hola, $userName 👋", style = MaterialTheme.typography.headlineSmall)
        EmptyState(
            title = "Aún no tienes movimientos",
            message = "Registra tu primer ingreso o gasto.",
            modifier = Modifier.fillMaxWidth(),
            action = { FinanceButton(text = "Agregar movimiento", onClick = onAddMovement) },
        )
    }
}

@Composable
private fun HomeContent(
    state: HomeUiState.Success,
    onToggleBalanceVisibility: () -> Unit,
    onViewAllMovements: () -> Unit,
    onTransactionClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
    ) {
        item {
            Text(text = "Hola, ${state.userName} 👋", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(16.dp))
            BalanceCard(
                balance = state.balance,
                isVisible = state.isBalanceVisible,
                onToggleVisibility = onToggleBalanceVisibility,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                IncomeExpenseCard(
                    title = "Ingresos",
                    amount = state.income,
                    isIncome = true,
                    modifier = Modifier.weight(1f),
                )
                IncomeExpenseCard(
                    title = "Gastos",
                    amount = state.expenses,
                    isIncome = false,
                    modifier = Modifier.weight(1f),
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(text = "Últimos movimientos", style = MaterialTheme.typography.titleMedium)
                TextButton(onClick = onViewAllMovements) { Text("Ver todos") }
            }
        }
        items(state.recentTransactions, key = { it.id }) { transaction ->
            TransactionItem(transaction = transaction, onClick = { onTransactionClick(transaction.id) })
        }
    }
}