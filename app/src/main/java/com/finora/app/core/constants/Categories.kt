package com.finora.app.core.constants

import com.finora.app.domain.model.TransactionType

/**
 * Default category seed data. Income categories are not specified by the product spec
 * (only expense categories are enumerated there), so a reasonable default set is used here.
 */
object DefaultCategories {

    data class Seed(val name: String, val type: TransactionType, val icon: String)

    val EXPENSE = listOf(
        Seed("Alimentación", TransactionType.EXPENSE, "food"),
        Seed("Transporte", TransactionType.EXPENSE, "transport"),
        Seed("Vivienda", TransactionType.EXPENSE, "housing"),
        Seed("Salud", TransactionType.EXPENSE, "health"),
        Seed("Educación", TransactionType.EXPENSE, "education"),
        Seed("Entretenimiento", TransactionType.EXPENSE, "entertainment"),
        Seed("Compras", TransactionType.EXPENSE, "shopping"),
        Seed("Servicios", TransactionType.EXPENSE, "services"),
        Seed("Otros", TransactionType.EXPENSE, "other"),
    )

    val INCOME = listOf(
        Seed("Salario", TransactionType.INCOME, "salary"),
        Seed("Freelance", TransactionType.INCOME, "freelance"),
        Seed("Inversiones", TransactionType.INCOME, "investment"),
        Seed("Ventas", TransactionType.INCOME, "sales"),
        Seed("Regalos", TransactionType.INCOME, "gift"),
        Seed("Otros", TransactionType.INCOME, "other"),
    )

    val ALL = EXPENSE + INCOME
}
