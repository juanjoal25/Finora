package com.finora.app.domain.usecase.auth

import com.finora.app.core.result.Result
import com.finora.app.testutil.FakeAuthRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class RegisterUserUseCaseTest {

    private lateinit var authRepository: FakeAuthRepository
    private lateinit var registerUserUseCase: RegisterUserUseCase

    @Before
    fun setUp() {
        authRepository = FakeAuthRepository()
        registerUserUseCase = RegisterUserUseCase(authRepository)
    }

    @Test
    fun `blank name fails validation`() = runTest {
        val result = registerUserUseCase("", "user@finora.com", "secret123", "secret123")
        assertTrue(result is Result.Error)
    }

    @Test
    fun `mismatched password confirmation fails validation`() = runTest {
        val result = registerUserUseCase("Juan", "user@finora.com", "secret123", "different")
        assertTrue(result is Result.Error)
    }

    @Test
    fun `valid data registers successfully`() = runTest {
        val result = registerUserUseCase("Juan", "user@finora.com", "secret123", "secret123")
        assertTrue(result is Result.Success)
    }

    @Test
    fun `duplicate email fails`() = runTest {
        registerUserUseCase("Juan", "user@finora.com", "secret123", "secret123")

        val result = registerUserUseCase("Otro", "user@finora.com", "secret123", "secret123")

        assertTrue(result is Result.Error)
    }
}
