package com.sanmiade.composemealdbapp.ui.features.meals

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.sanmiade.composemealdbapp.Screen.MealCategory.mealCategoryName
import com.sanmiade.composemealdbapp.ui.components.Meals
import com.sanmiade.composemealdbapp.ui.features.mealCategories.MealCategoriesEvent
import com.sanmiade.composemealdbapp.ui.features.mealCategories.MealCategoriesNavigationEvent
import com.sanmiade.composemealdbapp.ui.features.mealCategories.MealCategoriesUiState

@Composable
fun MealsScreen(
    mealCategoryName: String,
    scaffoldState: ScaffoldState,
    navigationEvent: (MealsNavigationEvent) -> Unit
) {
    val mealsViewModel: MealsViewModel = hiltViewModel()
    val mealsUiState by mealsViewModel.mealsUiState.collectAsState()

    MealsContent(
        modifier = Modifier,
        scaffoldState = scaffoldState,
        mealsUiState = mealsUiState,
        handleNavigationEvent = navigationEvent,
        handleEvent = mealsViewModel::handleEvent
    )
}

@Composable
fun MealsContent(
    modifier: Modifier,
    scaffoldState: ScaffoldState,
    mealsUiState: MealsUiState,
    handleNavigationEvent: (event: MealsNavigationEvent) -> Unit,
    handleEvent: (event: MealsEvent) -> Unit,
) {
    if (mealsUiState.showProgress) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Meals(modifier = modifier, mealCategoryModels = mealsUiState.meals) {

        }
    }
}