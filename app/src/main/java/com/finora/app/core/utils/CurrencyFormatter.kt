package com.finora.app.core.utils

import com.finora.app.domain.model.Currency
import java.text.NumberFormat

/**
 * Centralized currency formatting so no screen ever shows a raw Double (e.g. "2450000.0").
 * Takes the user's chosen [Currency] explicitly rather than a fixed locale, since v1 lets
 * the user pick their display currency from the Profile screen.
 */
object CurrencyFormatter {

    private val formatters = mutableMapOf<Currency, NumberFormat>()

    private fun formatterFor(currency: Currency): NumberFormat = formatters.getOrPut(currency) {
        // Plain number formatting (not getCurrencyInstance): several of the supported
        // currencies share "$" as their locale-default symbol (COP/USD/MXN/ARS), which would
        // make amounts in different currencies indistinguishable. currency.symbol is prefixed
        // manually instead so the symbol always matches what the rest of the app (Moneda
        // screen, AmountTextField) shows for that currency.
        NumberFormat.getNumberInstance(currency.locale).apply {
            maximumFractionDigits = 0
            minimumFractionDigits = 0
        }
    }

    fun format(amount: Double, currency: Currency): String =
        "${currency.symbol}${formatterFor(currency).format(amount)}"

    fun formatSigned(amount: Double, isIncome: Boolean, currency: Currency): String {
        val prefix = if (isIncome) "+" else "-"
        return "$prefix${format(kotlin.math.abs(amount), currency)}"
    }
}
