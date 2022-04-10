package com.sanmiade.composemealdbapp.ui.features.mealCategories

sealed interface MealCategoriesEvent {
    object ErrorDismissed : MealCategoriesEvent
}
sealed interface MealCategoriesNavigationEvent {
    object ShowMealCategory : MealCategoriesNavigationEvent
}
