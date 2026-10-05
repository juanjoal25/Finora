package com.finora.app.data.mapper

import com.finora.app.data.local.entities.UserEntity
import com.finora.app.domain.model.User

fun UserEntity.toDomain(): User = User(id = id, name = name, email = email, createdAt = createdAt)
