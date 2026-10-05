package com.finora.app.presentation.theme

import androidx.compose.runtime.compositionLocalOf
import com.finora.app.domain.model.Currency

/** Provided once at the app root (see [com.finora.app.presentation.navigation.FinoraApp]) from the user's stored preference. */
val LocalCurrency = compositionLocalOf { Currency.COP }
