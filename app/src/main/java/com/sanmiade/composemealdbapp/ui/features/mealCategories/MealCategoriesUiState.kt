package com.sanmiade.composemealdbapp.ui.features.mealCategories

import androidx.annotation.StringRes
import com.sanmiade.composemealdbapp.R
import com.sanmiade.composemealdbapp.domain.model.MealCategoryModel

data class MealCategoriesUiState(
    val categories: List<MealCategoryModel> = emptyList(),
    val showProgress: Boolean = false,
    @StringRes val error: Int? = null
)

fun MealCategoriesUiState.toLoading() = copy(showProgress = true, error = null)

fun MealCategoriesUiState.toSuccess(categories: List<MealCategoryModel>) =
    copy(categories = categories, showProgress = false, error = null)

fun MealCategoriesUiState.toError() =
    copy(error = R.string.meal_categories_error, showProgress = false)