package com.finora.app.presentation.auth.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.finora.app.presentation.components.FinanceButton
import com.finora.app.presentation.components.FinanceTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    onNavigateBack: () -> Unit,
    viewModel: RegisterViewModel = hiltViewModel(),
) {
    val formState by viewModel.formState.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState) {
        if (uiState is RegisterUiState.Success) onRegisterSuccess()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Crear cuenta") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            FinanceTextField(
                value = formState.name,
                onValueChange = viewModel::onNameChange,
                label = "Nombre completo",
                errorMessage = formState.nameError,
            )
            FinanceTextField(
                value = formState.email,
                onValueChange = viewModel::onEmailChange,
                label = "Correo electrónico",
                errorMessage = formState.emailError,
                keyboardType = KeyboardType.Email,
            )
            FinanceTextField(
                value = formState.password,
                onValueChange = viewModel::onPasswordChange,
                label = "Contraseña",
                errorMessage = formState.passwordError,
                keyboardType = KeyboardType.Password,
                visualTransformation = if (formState.isPasswordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                trailingIcon = {
                    IconButton(onClick = viewModel::onTogglePasswordVisibility) {
                        Icon(
                            imageVector = if (formState.isPasswordVisible) {
                                Icons.Filled.VisibilityOff
                            } else {
                                Icons.Filled.Visibility
                            },
                            contentDescription = null,
                        )
                    }
                },
            )
            FinanceTextField(
                value = formState.confirmPassword,
                onValueChange = viewModel::onConfirmPasswordChange,
                label = "Confirmar contraseña",
                errorMessage = formState.confirmPasswordError,
                keyboardType = KeyboardType.Password,
                visualTransformation = if (formState.isPasswordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
            )

            val errorState = uiState
            if (errorState is RegisterUiState.Error) {
                Text(
                    text = errorState.message,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                )
            }

            Spacer(modifier = Modifier.height(12.dp))
            FinanceButton(
                text = "Crear cuenta",
                onClick = viewModel::onRegisterClick,
                isLoading = uiState is RegisterUiState.Loading,
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
