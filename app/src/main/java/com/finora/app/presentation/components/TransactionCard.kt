package com.finora.app.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.finora.app.core.utils.CurrencyFormatter
import com.finora.app.core.utils.DateFormatter
import com.finora.app.domain.model.Transaction
import com.finora.app.domain.model.TransactionType
import com.finora.app.presentation.theme.FinoraShapes
import com.finora.app.presentation.theme.LocalCurrency

@Composable
fun TransactionCard(transaction: Transaction, modifier: Modifier = Modifier) {
    val isIncome = transaction.type == TransactionType.INCOME
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = FinoraShapes.large,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = if (isIncome) "Ingreso" else "Gasto",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                text = CurrencyFormatter.formatSigned(transaction.amount, isIncome, LocalCurrency.current),
                style = MaterialTheme.typography.headlineMedium,
                color = if (isIncome) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.error,
            )
            Text(
                text = "${transaction.category} · ${DateFormatter.fullDate(transaction.date)}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}
