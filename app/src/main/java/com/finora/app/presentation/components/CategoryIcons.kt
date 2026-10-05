package com.finora.app.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.MovieFilter
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.Work
import androidx.compose.ui.graphics.vector.ImageVector
import com.finora.app.core.constants.DefaultCategories

/**
 * Maps a [com.finora.app.domain.model.Category.icon] key (a plain string, kept
 * framework-free in domain) to a concrete Material icon — this lookup is the only place
 * that needs to know about it.
 */
object CategoryIcons {
    fun iconFor(key: String): ImageVector = when (key) {
        "food" -> Icons.Filled.Fastfood
        "transport" -> Icons.Filled.DirectionsBus
        "housing" -> Icons.Filled.Home
        "health" -> Icons.Filled.LocalHospital
        "education" -> Icons.Filled.School
        "entertainment" -> Icons.Filled.MovieFilter
        "shopping" -> Icons.Filled.ShoppingBag
        "services" -> Icons.Filled.Storefront
        "salary" -> Icons.Filled.Work
        "freelance" -> Icons.Filled.AttachMoney
        "investment" -> Icons.Filled.TrendingUp
        "sales" -> Icons.Filled.MonetizationOn
        "gift" -> Icons.Filled.CardGiftcard
        else -> Icons.Filled.Menu
    }

    /**
     * [com.finora.app.domain.model.Transaction.category] only stores the category name
     * (not its id/icon key), so list/detail screens resolve the icon by name against the
     * seeded default categories.
     */
    fun iconForCategoryName(name: String): ImageVector {
        val key = DefaultCategories.ALL.firstOrNull { it.name == name }?.icon ?: "other"
        return iconFor(key)
    }
}
