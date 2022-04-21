package com.sanmiade.composemealdbapp.ui.features.searchMeals

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.sanmiade.composemealdbapp.ui.components.Meals

@Composable
fun SearchMealScreen(searchViewModel: SearchViewModel) {
    val searchQeury by searchViewModel.searchMealUiState.collectAsState()
    if (searchQeury.searchInProgress) {
        CircularProgressIndicator()
    } else {
        Meals(modifier = Modifier,
            mealCategoryModels = searchQeury.result,
            onDoubleCardClick = {

            }, onCardClick = {

            })
    }
}