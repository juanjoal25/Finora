package com.finora.app.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.finora.app.core.constants.AppConstants
import com.finora.app.domain.usecase.auth.GetCurrentUserUseCase
import com.finora.app.domain.usecase.transaction.BalanceCalculator
import com.finora.app.domain.usecase.transaction.GetTransactionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val getTransactionsUseCase: GetTransactionsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _isBalanceVisible = MutableStateFlow(true)

    init {
        viewModelScope.launch {
            val user = getCurrentUserUseCase()
            if (user == null) {
                _uiState.value = HomeUiState.Error("No se pudo cargar tu sesión")
                return@launch
            }
            combine(getTransactionsUseCase(user.id), _isBalanceVisible) { transactions, isVisible ->
                if (transactions.isEmpty()) {
                    HomeUiState.Empty(user.name)
                } else {
                    val summary = BalanceCalculator.summarize(transactions)
                    HomeUiState.Success(
                        userName = user.name,
                        balance = summary.balance,
                        income = summary.income,
                        expenses = summary.expenses,
                        recentTransactions = transactions.take(AppConstants.RECENT_TRANSACTIONS_LIMIT),
                        isBalanceVisible = isVisible,
                    )
                }
            }.collect { _uiState.value = it }
        }
    }

    fun onToggleBalanceVisibility() {
        _isBalanceVisible.value = !_isBalanceVisible.value
    }
}
