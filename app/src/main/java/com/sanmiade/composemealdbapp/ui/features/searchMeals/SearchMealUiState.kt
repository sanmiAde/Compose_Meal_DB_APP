package com.sanmiade.composemealdbapp.ui.features.searchMeals


import com.sanmiade.composemealdbapp.domain.model.MealModel

data class SearchMealUiState(
    val searchInProgress: Boolean = false,
    val result: List<MealModel>? = null,
    val query: String = "",
    val error: String? = null
)

fun SearchMealUiState.toLoading(query: String) = copy(searchInProgress = true, query = query)

fun SearchMealUiState.toSuccess(data: List<MealModel>) =
    copy(searchInProgress = false, result = data, error = null)

fun SearchMealUiState.toError(error: String) =
    copy(searchInProgress = false, error = error)

fun SearchMealUiState.toNotFound(message: String) =
    copy(searchInProgress = false, error = message)