package com.finora.app.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.finora.app.domain.model.CreditsInfo
import com.finora.app.domain.usecase.auth.GetCurrentUserUseCase
import com.finora.app.domain.usecase.profile.ObserveCreditsUseCase
import com.finora.app.domain.usecase.profile.SetCreditsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AboutViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val observeCreditsUseCase: ObserveCreditsUseCase,
    private val setCreditsUseCase: SetCreditsUseCase,
) : ViewModel() {

    private val _credits = MutableStateFlow(CreditsInfo(name = "", email = ""))
    val credits: StateFlow<CreditsInfo> = _credits.asStateFlow()

    init {
        viewModelScope.launch {
            val currentUser = getCurrentUserUseCase()
            observeCreditsUseCase().collect { stored ->
                _credits.value = CreditsInfo(
                    name = stored.name.ifBlank { currentUser?.name.orEmpty() },
                    email = stored.email.ifBlank { currentUser?.email.orEmpty() },
                )
            }
        }
    }

    fun onCreditsSaved(name: String, email: String) {
        viewModelScope.launch { setCreditsUseCase(CreditsInfo(name = name, email = email)) }
    }
}
