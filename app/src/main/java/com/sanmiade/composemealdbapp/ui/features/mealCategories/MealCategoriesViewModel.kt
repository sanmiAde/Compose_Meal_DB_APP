package com.sanmiade.composemealdbapp.ui.features.mealCategories

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanmiade.composemealdbapp.R
import com.sanmiade.composemealdbapp.domain.usecase.GetMealCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealCategoriesViewModel @Inject constructor(private val getMealCategoriesUseCase: GetMealCategoriesUseCase) :
    ViewModel() {

    private val _mealCategoriesUiState = MutableStateFlow(MealCategoriesUiState())
    val mealCategoriesUiState = _mealCategoriesUiState.asStateFlow()

    init {
        getMealCategories()
    }

    fun handleError(mealCategoriesEvent: MealCategoriesEvent) {
        when (mealCategoriesEvent) {
            MealCategoriesEvent.ErrorDismissed -> {
                dismissError()
            }
        }
    }

    private fun dismissError() {
        _mealCategoriesUiState.update {
            it.copy(error = null)
        }
    }

    private fun getMealCategories() {
        viewModelScope.launch {
            _mealCategoriesUiState.update(MealCategoriesUiState::toLoading)
            getMealCategoriesUseCase().onSuccess { categories ->
                _mealCategoriesUiState.update {
                    it.toSuccess(categories)
                }
            }.onFailure {
                _mealCategoriesUiState.update {
                    it.toError(R.string.meal_categories_error)
                }
            }
        }
    }
}