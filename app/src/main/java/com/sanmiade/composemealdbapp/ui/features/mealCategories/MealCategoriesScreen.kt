package com.sanmiade.composemealdbapp.ui.features.mealCategories

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun MealCategoriesScreen(navigationEvent: (event: MealCategoriesNavigationEvent) -> Unit) {
    Button(onClick = { navigationEvent(MealCategoriesNavigationEvent.ShowMealCategory) }) {
        Text(text = "Categories")
    }
}