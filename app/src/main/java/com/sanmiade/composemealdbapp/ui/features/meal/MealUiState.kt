package com.sanmiade.composemealdbapp.ui.features.meal

import androidx.annotation.StringRes
import com.sanmiade.composemealdbapp.R
import com.sanmiade.composemealdbapp.domain.model.MealDetailModel

data class MealUiState(
    val showProgress: Boolean = false,
    val mealDetail: MealDetailModel? = null,
    @StringRes val error: Int? = null
)

fun MealUiState.toLoading() = copy(showProgress = true)

fun MealUiState.toSuccess(mealDetail: MealDetailModel) =
    copy(mealDetail = mealDetail, showProgress = false)

fun MealUiState.toError() = copy(showProgress = false, error = R.string.meals_error)