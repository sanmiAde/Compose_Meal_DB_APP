package com.sanmiade.composemealdbapp.ui.features.mealCategories

import androidx.annotation.StringRes
import com.sanmiade.composemealdbapp.domain.model.MealCategoryModel

data class MealCategoriesUiState(
    val categories: List<MealCategoryModel> = emptyList(),
    val showProgress: Boolean = false,
    val showError: Boolean = false,
    @StringRes val error: Int? = null
)

fun MealCategoriesUiState.toLoading() = copy(showProgress = false, showError = false, error = null)

fun MealCategoriesUiState.toSuccess(categories: List<MealCategoryModel>) =
    copy(categories = categories, showProgress = false, showError = false, error = null)

fun MealCategoriesUiState.toError(errorRes: Int?) =
    copy(error = errorRes, showProgress = false, showError = true)