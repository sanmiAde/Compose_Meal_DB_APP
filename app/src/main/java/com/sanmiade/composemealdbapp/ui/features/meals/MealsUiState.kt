package com.sanmiade.composemealdbapp.ui.features.meals

import androidx.annotation.StringRes
import com.sanmiade.composemealdbapp.R
import com.sanmiade.composemealdbapp.domain.model.MealModel

data class MealsUiState(
    val meals: List<MealModel>? = null,
    val showProgress: Boolean = false,
    @StringRes val error: Int? = null
)

fun MealsUiState.toLoading() = copy(showProgress = true)

fun MealsUiState.toSuccess(list: List<MealModel>) = copy(meals = list, showProgress = false)

fun MealsUiState.toError() = copy(showProgress = false, error = R.string.meals_error)