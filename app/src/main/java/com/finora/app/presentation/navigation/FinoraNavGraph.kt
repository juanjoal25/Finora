package com.finora.app.presentation.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.finora.app.domain.model.ThemeMode
import com.finora.app.presentation.auth.login.LoginScreen
import com.finora.app.presentation.auth.register.RegisterScreen
import com.finora.app.presentation.components.FinanceBottomSheet
import com.finora.app.presentation.components.LoadingState
import com.finora.app.presentation.home.HomeScreen
import com.finora.app.presentation.movements.detail.TransactionDetailScreen
import com.finora.app.presentation.movements.list.MovementsScreen
import com.finora.app.presentation.profile.AboutScreen
import com.finora.app.presentation.profile.CurrencyScreen
import com.finora.app.presentation.profile.PersonalInfoScreen
import com.finora.app.presentation.profile.ProfileScreen
import com.finora.app.presentation.profile.SecurityScreen
import com.finora.app.presentation.profile.SettingsScreen
import com.finora.app.presentation.theme.FinoraTheme
import com.finora.app.presentation.theme.LocalCurrency
import com.finora.app.presentation.transaction.expense.AddExpenseScreen
import com.finora.app.presentation.transaction.income.AddIncomeScreen

@Composable
fun FinoraApp(rootViewModel: RootViewModel = hiltViewModel()) {
    val sessionState by rootViewModel.sessionState.collectAsState()
    val themeMode by rootViewModel.themeMode.collectAsState()
    val currency by rootViewModel.currency.collectAsState()
    val isSystemDark = isSystemInDarkTheme()

    val isDarkTheme = when (themeMode) {
        ThemeMode.LIGHT -> false
        ThemeMode.DARK -> true
        ThemeMode.SYSTEM -> isSystemDark
    }

    FinoraTheme(darkTheme = isDarkTheme) {
        CompositionLocalProvider(LocalCurrency provides currency) {
            when (val state = sessionState) {
                is SessionState.Loading -> LoadingState(modifier = Modifier.fillMaxSize())
                else -> FinoraNavGraph(isLoggedIn = state is SessionState.LoggedIn)
            }
        }
    }
}

@Composable
private fun FinoraNavGraph(isLoggedIn: Boolean) {
    val navController = rememberNavController()
    var showAddSheet by remember { mutableStateOf(false) }

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    val showBottomBar = currentRoute in setOf(Routes.Home.route, Routes.Movements.route, Routes.Profile.route)

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                FinoraBottomBar(
                    currentRoute = currentRoute,
                    onItemSelected = { item ->
                        navController.navigate(item.route) {
                            popUpTo(Routes.Home.route) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    onAddClick = { showAddSheet = true },
                )
            }
        },
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = if (isLoggedIn) Routes.Home.route else Routes.Login.route,
            modifier = Modifier.padding(padding),
        ) {
            composable(Routes.Login.route) {
                LoginScreen(
                    onLoginSuccess = {
                        navController.navigate(Routes.Home.route) {
                            popUpTo(Routes.Login.route) { inclusive = true }
                        }
                    },
                    onNavigateToRegister = { navController.navigate(Routes.Register.route) },
                )
            }
            composable(Routes.Register.route) {
                RegisterScreen(
                    onRegisterSuccess = {
                        navController.navigate(Routes.Login.route) {
                            popUpTo(Routes.Register.route) { inclusive = true }
                        }
                    },
                    onNavigateBack = { navController.popBackStack() },
                )
            }
            composable(Routes.Home.route) {
                HomeScreen(
                    onViewAllMovements = { navController.navigate(Routes.Movements.route) },
                    onTransactionClick = { id -> navController.navigate(Routes.TransactionDetail.createRoute(id)) },
                    onAddMovement = { showAddSheet = true },
                )
            }
            composable(Routes.Movements.route) {
                MovementsScreen(
                    onTransactionClick = { id -> navController.navigate(Routes.TransactionDetail.createRoute(id)) },
                )
            }
            composable(
                route = Routes.TransactionDetail.route,
                arguments = listOf(
                    navArgument(Routes.TransactionDetail.ARG_TRANSACTION_ID) { type = NavType.StringType },
                ),
            ) {
                TransactionDetailScreen(
                    onNavigateBack = { navController.popBackStack() },
                    onEdit = { id, isIncome ->
                        val route = if (isIncome) Routes.AddIncome.createRoute(id) else Routes.AddExpense.createRoute(id)
                        navController.navigate(route)
                    },
                    onDeleted = { navController.popBackStack() },
                )
            }
            composable(
                route = Routes.AddIncome.route,
                arguments = listOf(
                    navArgument(Routes.AddIncome.ARG_TRANSACTION_ID) {
                        type = NavType.StringType
                        nullable = true
                    },
                ),
            ) {
                AddIncomeScreen(
                    onSaved = { navController.popBackStack() },
                    onNavigateBack = { navController.popBackStack() },
                )
            }
            composable(
                route = Routes.AddExpense.route,
                arguments = listOf(
                    navArgument(Routes.AddExpense.ARG_TRANSACTION_ID) {
                        type = NavType.StringType
                        nullable = true
                    },
                ),
            ) {
                AddExpenseScreen(
                    onSaved = { navController.popBackStack() },
                    onNavigateBack = { navController.popBackStack() },
                )
            }
            composable(Routes.Profile.route) {
                ProfileScreen(
                    onNavigateToPersonalInfo = { navController.navigate(Routes.PersonalInfo.route) },
                    onNavigateToSettings = { navController.navigate(Routes.Settings.route) },
                    onNavigateToSecurity = { navController.navigate(Routes.Security.route) },
                    onNavigateToCurrency = { navController.navigate(Routes.Currency.route) },
                    onNavigateToAbout = { navController.navigate(Routes.About.route) },
                    onLoggedOut = {
                        navController.navigate(Routes.Login.route) {
                            popUpTo(0) { inclusive = true }
                        }
                    },
                )
            }
            composable(Routes.PersonalInfo.route) {
                PersonalInfoScreen(onNavigateBack = { navController.popBackStack() })
            }
            composable(Routes.Settings.route) {
                SettingsScreen(onNavigateBack = { navController.popBackStack() })
            }
            composable(Routes.Security.route) {
                SecurityScreen(onNavigateBack = { navController.popBackStack() })
            }
            composable(Routes.Currency.route) {
                CurrencyScreen(onNavigateBack = { navController.popBackStack() })
            }
            composable(Routes.About.route) {
                AboutScreen(onNavigateBack = { navController.popBackStack() })
            }
        }
    }

    if (showAddSheet) {
        FinanceBottomSheet(onDismissRequest = { showAddSheet = false }) {
            AddMovementSheetContent(
                onIncomeSelected = {
                    showAddSheet = false
                    navController.navigate(Routes.AddIncome.createRoute())
                },
                onExpenseSelected = {
                    showAddSheet = false
                    navController.navigate(Routes.AddExpense.createRoute())
                },
            )
        }
    }
}

@Composable
private fun FinoraBottomBar(
    currentRoute: String?,
    onItemSelected: (BottomNavItem) -> Unit,
    onAddClick: () -> Unit,
) {
    NavigationBar {
        NavBarTab(BottomNavItem.HOME, currentRoute, onItemSelected)
        NavBarTab(BottomNavItem.MOVEMENTS, currentRoute, onItemSelected)
        NavigationBarItem(
            selected = false,
            onClick = onAddClick,
            icon = {
                FloatingActionButton(onClick = onAddClick) {
                    Icon(Icons.Filled.Add, contentDescription = "Agregar")
                }
            },
            label = { Text("Agregar") },
        )
        NavBarTab(BottomNavItem.PROFILE, currentRoute, onItemSelected)
    }
}

@Composable
private fun RowScope.NavBarTab(
    item: BottomNavItem,
    currentRoute: String?,
    onItemSelected: (BottomNavItem) -> Unit,
) {
    val isSelected = currentRoute == item.route
    NavigationBarItem(
        selected = isSelected,
        onClick = { onItemSelected(item) },
        icon = {
            Icon(
                imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                contentDescription = item.label,
            )
        },
        label = { Text(item.label) },
    )
}

@Composable
private fun AddMovementSheetContent(onIncomeSelected: () -> Unit, onExpenseSelected: () -> Unit) {
    Column(modifier = Modifier.padding(24.dp)) {
        Text(text = "¿Qué deseas registrar?", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))
        ListItem(
            headlineContent = { Text("Ingreso") },
            leadingContent = { Text("💰", style = MaterialTheme.typography.headlineSmall) },
            modifier = Modifier.clickable(onClick = onIncomeSelected),
        )
        ListItem(
            headlineContent = { Text("Gasto") },
            leadingContent = { Text("💸", style = MaterialTheme.typography.headlineSmall) },
            modifier = Modifier.clickable(onClick = onExpenseSelected),
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}
