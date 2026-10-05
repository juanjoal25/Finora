package com.finora.app.core.utils

import java.security.SecureRandom
import java.security.spec.KeySpec
import java.util.Base64
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

/**
 * Local, non-production password hashing for the v1 simulated auth (no backend yet).
 * Uses PBKDF2WithHmacSHA256 (pure JDK, no extra dependency) with a random per-user salt
 * and a deliberately slow iteration count, which is meaningfully more brute-force
 * resistant than a bare single-pass hash. This is NOT a substitute for a real backend's
 * auth (no pepper, no server-side rate limiting) — acceptable only because v1 has no
 * real server and [com.finora.app.domain.repository.AuthRepository] is designed so this
 * whole local implementation can be swapped out later without touching the rest of the app.
 */
object PasswordHasher {

    private const val ALGORITHM = "PBKDF2WithHmacSHA256"
    private const val ITERATIONS = 120_000
    private const val KEY_LENGTH_BITS = 256
    private const val SALT_LENGTH_BYTES = 16

    data class HashedPassword(val hash: String, val salt: String)

    fun hash(password: String): HashedPassword {
        val salt = ByteArray(SALT_LENGTH_BYTES).also { SecureRandom().nextBytes(it) }
        val derived = deriveKey(password, salt)
        return HashedPassword(
            hash = Base64.getEncoder().encodeToString(derived),
            salt = Base64.getEncoder().encodeToString(salt),
        )
    }

    fun verify(password: String, hash: String, salt: String): Boolean {
        val saltBytes = Base64.getDecoder().decode(salt)
        val expected = Base64.getDecoder().decode(hash)
        val actual = deriveKey(password, saltBytes)
        return expected.contentEquals(actual)
    }

    private fun deriveKey(password: String, salt: ByteArray): ByteArray {
        val spec: KeySpec = PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH_BITS)
        val factory = SecretKeyFactory.getInstance(ALGORITHM)
        return factory.generateSecret(spec).encoded
    }
}
