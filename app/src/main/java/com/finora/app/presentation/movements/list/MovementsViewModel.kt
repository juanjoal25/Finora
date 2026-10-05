package com.finora.app.presentation.movements.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.finora.app.domain.model.TransactionType
import com.finora.app.domain.usecase.auth.GetCurrentUserUseCase
import com.finora.app.domain.usecase.transaction.GetTransactionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovementsViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val getTransactionsUseCase: GetTransactionsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<MovementsUiState>(MovementsUiState.Loading)
    val uiState: StateFlow<MovementsUiState> = _uiState.asStateFlow()

    private val _filter = MutableStateFlow(MovementsFilter.ALL)
    private val _query = MutableStateFlow("")

    init {
        viewModelScope.launch {
            val user = getCurrentUserUseCase()
            if (user == null) {
                _uiState.value = MovementsUiState.Error("No se pudo cargar tu sesión")
                return@launch
            }
            combine(getTransactionsUseCase(user.id), _filter, _query) { transactions, filter, query ->
                val byType = when (filter) {
                    MovementsFilter.ALL -> transactions
                    MovementsFilter.INCOME -> transactions.filter { it.type == TransactionType.INCOME }
                    MovementsFilter.EXPENSE -> transactions.filter { it.type == TransactionType.EXPENSE }
                }
                val filtered = if (query.isBlank()) {
                    byType
                } else {
                    byType.filter {
                        it.category.contains(query, ignoreCase = true) ||
                            (it.description?.contains(query, ignoreCase = true) == true)
                    }
                }
                Triple(transactions.isEmpty(), filtered, filter to query)
            }.collect { (noTransactionsAtAll, filtered, filterAndQuery) ->
                _uiState.value = if (noTransactionsAtAll) {
                    MovementsUiState.Empty
                } else {
                    MovementsUiState.Success(filtered, filterAndQuery.first, filterAndQuery.second)
                }
            }
        }
    }

    fun onFilterChange(filter: MovementsFilter) {
        _filter.value = filter
    }

    fun onQueryChange(query: String) {
        _query.value = query
    }
}
