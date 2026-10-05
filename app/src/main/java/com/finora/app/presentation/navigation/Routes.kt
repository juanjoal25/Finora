package com.finora.app.presentation.navigation

/**
 * Centralized route definitions — screens never hardcode navigation strings themselves.
 */
sealed class Routes(val route: String) {
    data object Login : Routes("login")
    data object Register : Routes("register")
    data object Home : Routes("home")
    data object Movements : Routes("movements")
    data object Profile : Routes("profile")
    data object PersonalInfo : Routes("profile/personal_info")
    data object Settings : Routes("profile/settings")
    data object Security : Routes("profile/security")
    data object Currency : Routes("profile/currency")
    data object About : Routes("profile/about")

    data object TransactionDetail : Routes("transaction_detail/{transactionId}") {
        const val ARG_TRANSACTION_ID = "transactionId"
        fun createRoute(transactionId: String) = "transaction_detail/$transactionId"
    }

    /** Edit mode reuses the same screen via an optional `transactionId` query arg. */
    data object AddIncome : Routes("add_income?transactionId={transactionId}") {
        const val ARG_TRANSACTION_ID = "transactionId"
        fun createRoute(transactionId: String? = null) =
            if (transactionId == null) "add_income" else "add_income?transactionId=$transactionId"
    }

    data object AddExpense : Routes("add_expense?transactionId={transactionId}") {
        const val ARG_TRANSACTION_ID = "transactionId"
        fun createRoute(transactionId: String? = null) =
            if (transactionId == null) "add_expense" else "add_expense?transactionId=$transactionId"
    }
}
