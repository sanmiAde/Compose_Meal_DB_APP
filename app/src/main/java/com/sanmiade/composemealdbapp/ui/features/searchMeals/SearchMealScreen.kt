package com.sanmiade.composemealdbapp.ui.features.searchMeals

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sanmiade.composemealdbapp.ui.components.Meals

@Composable
fun SearchMealScreen(
    searchViewModel: SearchViewModel,
    handleNavigationEvent: (SearchNavigationEvent) -> Unit
) {
    val searchMealUiState by searchViewModel.searchMealUiState.collectAsState()
    SearchMealContent(
        modifier = Modifier.fillMaxSize(),
        searchMealUiState = searchMealUiState,
        handleNavigationEvent = handleNavigationEvent
    )
}

@Composable
fun SearchMealContent(
    modifier: Modifier,
    searchMealUiState: SearchMealUiState,
    handleNavigationEvent: (SearchNavigationEvent) -> Unit
) {
    if (searchMealUiState.searchInProgress) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        searchMealUiState.result?.let { result ->
            Meals(modifier = modifier,
                mealCategoryModels = result,
                onDoubleCardClick = {

                }, onCardClick = {
                    handleNavigationEvent(SearchNavigationEvent.ShowMeal(it))
                })
        }
    }
}