package com.finora.app.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.finora.app.domain.model.Currency
import com.finora.app.domain.usecase.profile.ObserveCurrencyUseCase
import com.finora.app.domain.usecase.profile.SetCurrencyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    observeCurrencyUseCase: ObserveCurrencyUseCase,
    private val setCurrencyUseCase: SetCurrencyUseCase,
) : ViewModel() {

    val currency: StateFlow<Currency> = observeCurrencyUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), Currency.COP)

    fun onCurrencySelected(currency: Currency) {
        viewModelScope.launch { setCurrencyUseCase(currency) }
    }
}
