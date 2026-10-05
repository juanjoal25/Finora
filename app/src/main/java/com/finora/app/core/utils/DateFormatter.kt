package com.finora.app.core.utils

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

/**
 * Centralized java.time formatting so date logic ("Hoy" / "Ayer" / "10 sep 2026") is never
 * duplicated across screens.
 */
object DateFormatter {

    private val MONTHS_ES = listOf(
        "ene", "feb", "mar", "abr", "may", "jun",
        "jul", "ago", "sep", "oct", "nov", "dic",
    )
    private val MONTHS_ES_CAP = MONTHS_ES.map { it.replaceFirstChar(Char::titlecase) }
    private val INPUT_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    fun friendly(date: LocalDate): String {
        val today = LocalDate.now()
        return when (date) {
            today -> "Hoy"
            today.minusDays(1) -> "Ayer"
            else -> fullDate(date)
        }
    }

    fun fullDate(date: LocalDate): String {
        val month = MONTHS_ES[date.monthValue - 1]
        return "${date.dayOfMonth} $month ${date.year}"
    }

    fun fullDateTime(instant: Instant): String {
        val zoned = instant.atZone(ZoneId.systemDefault())
        val time = "%02d:%02d".format(zoned.hour, zoned.minute)
        return "${fullDate(zoned.toLocalDate())} · $time"
    }

    fun monthYear(instant: Instant): String {
        val zoned = instant.atZone(ZoneId.systemDefault())
        return "${MONTHS_ES_CAP[zoned.monthValue - 1]} ${zoned.year}"
    }

    fun parseInput(text: String): LocalDate? = runCatching { LocalDate.parse(text, INPUT_FORMATTER) }.getOrNull()

    fun formatInput(date: LocalDate): String = date.format(INPUT_FORMATTER)
}
