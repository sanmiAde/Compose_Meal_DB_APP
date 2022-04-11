package com.sanmiade.composemealdbapp.ui.features.mealCategories


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sanmiade.composemealdbapp.ui.components.MealCategories

@Composable
fun MealCategoriesScreen(
    viewModel: MealCategoriesViewModel,
    navigationEvent: (event: MealCategoriesNavigationEvent) -> Unit
) {
    val mealCategoriesUiState by viewModel.mealCategoriesUiState.collectAsState()
    if (mealCategoriesUiState.showProgress) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        MealCategories(
            modifier = Modifier,
            mealCategoryModels = mealCategoriesUiState.categories,
            event = navigationEvent
        )
    }
}