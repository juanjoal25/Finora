package com.finora.app.data.mapper

import com.finora.app.data.local.entities.CategoryEntity
import com.finora.app.domain.model.Category

fun CategoryEntity.toDomain(): Category = Category(id = id, name = name, type = type, icon = icon)
