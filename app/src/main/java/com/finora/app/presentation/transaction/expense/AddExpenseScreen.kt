package com.finora.app.presentation.transaction.expense

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
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
import com.finora.app.presentation.components.AmountTextField
import com.finora.app.presentation.components.CategorySelector
import com.finora.app.presentation.components.FinanceButton
import com.finora.app.presentation.components.FinanceTextField
import java.time.Instant
import java.time.ZoneOffset

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExpenseScreen(
    onSaved: () -> Unit,
    onNavigateBack: () -> Unit,
    viewModel: AddExpenseViewModel = hiltViewModel(),
) {
    val formState by viewModel.formState.collectAsState()
    val uiState by viewModel.uiState.collectAsState()
    var showDatePicker by remember { mutableStateOf(false) }

    LaunchedEffect(uiState) {
        if (uiState is AddExpenseUiState.Saved) onSaved()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (formState.isEditMode) "Editar gasto" else "Nuevo gasto") },
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
                .padding(padding)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Spacer(modifier = Modifier.height(4.dp))
            AmountTextField(
                value = formState.amount,
                onValueChange = viewModel::onAmountChange,
                errorMessage = formState.amountError,
            )
            CategorySelector(
                categories = formState.categories,
                selectedCategoryId = formState.category?.id,
                onCategorySelected = viewModel::onCategorySelected,
            )
            if (formState.categoryError != null) {
                Text(
                    text = formState.categoryError.orEmpty(),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
            OutlinedTextField(
                value = DateFormatter.formatInput(formState.date),
                onValueChange = {},
                readOnly = true,
                label = { Text("Fecha") },
                trailingIcon = {
                    IconButton(onClick = { showDatePicker = true }) {
                        Icon(Icons.Filled.CalendarToday, contentDescription = null)
                    }
                },
                isError = formState.dateError != null,
                modifier = Modifier.fillMaxWidth(),
            )
            FinanceTextField(
                value = formState.description,
                onValueChange = viewModel::onDescriptionChange,
                label = "Descripción (opcional)",
            )

            val errorState = uiState
            if (errorState is AddExpenseUiState.Error) {
                Text(
                    text = errorState.message,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                )
            }

            FinanceButton(
                text = "Guardar gasto",
                onClick = viewModel::onSaveClick,
                isLoading = uiState is AddExpenseUiState.Loading,
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    }

    if (showDatePicker) {
        val initialMillis = formState.date.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli()
        val datePickerState = rememberDatePickerState(initialSelectedDateMillis = initialMillis)
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    val millis = datePickerState.selectedDateMillis
                    if (millis != null) {
                        val date = Instant.ofEpochMilli(millis).atZone(ZoneOffset.UTC).toLocalDate()
                        viewModel.onDateChange(date)
                    }
                    showDatePicker = false
                }) { Text("Aceptar") }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) { Text("Cancelar") }
            },
        ) {
            DatePicker(state = datePickerState)
        }
    }
}
