package com.sanmiade.composemealdbapp.ui.features.mealCategories


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.sanmiade.composemealdbapp.ui.components.MealCategories

@Composable
fun MealCategoriesScreen(
    navigationEvent: (event: MealCategoriesNavigationEvent) -> Unit
) {
    val viewModel: MealCategoriesViewModel = hiltViewModel()
    val mealCategoriesUiState by viewModel.mealCategoriesUiState.collectAsState()
    MealCategoriesContent(
        modifier = Modifier,
        mealCategoriesUiState = mealCategoriesUiState,
        handleNavigationEvent = navigationEvent,
        handleEvent = viewModel::handleEvent
    )
}

@Composable
fun MealCategoriesContent(
    modifier: Modifier,
    mealCategoriesUiState: MealCategoriesUiState,
    handleNavigationEvent: (event: MealCategoriesNavigationEvent) -> Unit,
    handleEvent: (event: MealCategoriesEvent) -> Unit,
) {
    if (mealCategoriesUiState.showProgress) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        MealCategories(
            modifier = modifier,
            mealCategoryModels = mealCategoriesUiState.categories,
            onCardClick = { mealCategoryId: String ->
                handleNavigationEvent(MealCategoriesNavigationEvent.ShowMealCategory(mealCategoryId))
            },
        )
    }
}