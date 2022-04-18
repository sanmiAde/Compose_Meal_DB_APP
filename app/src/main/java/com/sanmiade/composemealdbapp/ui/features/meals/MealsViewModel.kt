package com.sanmiade.composemealdbapp.ui.features.meals

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanmiade.composemealdbapp.Screen
import com.sanmiade.composemealdbapp.domain.usecase.GetMealsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealsViewModel @Inject constructor(
    private val getMealsUseCase: GetMealsUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val categoryName: String = savedStateHandle.get(Screen.MealCategory.mealCategoryName)!!
    private val _mealsUiState = MutableStateFlow(MealsUiState())
    val mealsUiState = _mealsUiState.asStateFlow()

    init {
        getMeals(categoryName)
    }

    fun handleEvent(mealsEvent: MealsEvent) {
        when (mealsEvent) {
            MealsEvent.ErrorDismissed -> {
                _mealsUiState.update { it.copy(error = null) }
            }
        }
    }

    private fun getMeals(categoryName: String) {
        viewModelScope.launch {
            _mealsUiState.update(MealsUiState::toLoading)
            getMealsUseCase.getMeals(categoryName).onSuccess { meals ->
                _mealsUiState.update {
                    it.toSuccess(meals)
                }
            }.onFailure {
                _mealsUiState.update {
                    it.toError()
                }
            }
        }
    }
}