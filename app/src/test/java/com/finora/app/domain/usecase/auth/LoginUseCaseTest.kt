package com.finora.app.domain.usecase.auth

import com.finora.app.core.result.Result
import com.finora.app.testutil.FakeAuthRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class LoginUseCaseTest {

    private lateinit var authRepository: FakeAuthRepository
    private lateinit var loginUseCase: LoginUseCase

    @Before
    fun setUp() {
        authRepository = FakeAuthRepository()
        loginUseCase = LoginUseCase(authRepository)
    }

    @Test
    fun `invalid email returns validation error`() = runTest {
        val result = loginUseCase("not-an-email", "123456")
        assertTrue(result is Result.Error)
    }

    @Test
    fun `blank password returns validation error`() = runTest {
        val result = loginUseCase("user@finora.com", "")
        assertTrue(result is Result.Error)
    }

    @Test
    fun `valid credentials for a registered user succeed`() = runTest {
        authRepository.register("Juan Perez", "juan@finora.com", "secret123")

        val result = loginUseCase("juan@finora.com", "secret123")

        assertTrue(result is Result.Success)
    }

    @Test
    fun `wrong password fails with invalid credentials`() = runTest {
        authRepository.register("Juan Perez", "juan@finora.com", "secret123")

        val result = loginUseCase("juan@finora.com", "wrong-password")

        assertTrue(result is Result.Error)
    }
}
