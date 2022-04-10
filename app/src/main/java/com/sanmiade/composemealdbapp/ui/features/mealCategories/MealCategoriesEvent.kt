package com.sanmiade.composemealdbapp.ui.features.mealCategories

sealed interface MealCategoriesEvent {
    object ErrorDismissed : MealCategoriesEvent
}