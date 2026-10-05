package com.finora.app.domain.model

import java.time.Instant

data class User(
    val id: String,
    val name: String,
    val email: String,
    val createdAt: Instant,
)
