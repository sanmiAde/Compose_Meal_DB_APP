package com.sanmiade.composemealdbapp.ui.features.meals

import com.sanmiade.composemealdbapp.domain.model.MealModel

sealed interface MealsEvent {
    object ErrorDismissed : MealsEvent
    data class SaveMeal(val mealModel: MealModel) : MealsEvent
}

sealed interface MealsNavigationEvent {
    data class ShowMeal(val id: String) : MealsNavigationEvent
}