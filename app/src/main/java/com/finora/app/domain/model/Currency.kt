package com.finora.app.domain.model

import java.util.Locale

enum class Currency(val code: String, val symbol: String, val displayLabel: String, val locale: Locale) {
    COP("COP", "$", "Peso colombiano", Locale("es", "CO")),
    USD("USD", "US$", "Dólar estadounidense", Locale("en", "US")),
    EUR("EUR", "€", "Euro", Locale("es", "ES")),
    MXN("MXN", "MX$", "Peso mexicano", Locale("es", "MX")),
    ARS("ARS", "AR$", "Peso argentino", Locale("es", "AR")),
    PEN("PEN", "S/", "Sol peruano", Locale("es", "PE")),
}
