package com.sanmiade.composemealdbapp.ui.features.meal

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanmiade.composemealdbapp.MEAL_NAME
import com.sanmiade.composemealdbapp.domain.usecase.GetMealUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealViewModel @Inject constructor(
    private val getMealUseCase: GetMealUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val name: String = savedStateHandle.get(MEAL_NAME)!!
    private val _mealUiState = MutableStateFlow(MealUiState())
    val mealsUiState = _mealUiState.asStateFlow()

    init {
        getMeal(name)
    }

    private fun getMeal(name: String) {
        viewModelScope.launch {
            _mealUiState.update(MealUiState::toLoading)
            getMealUseCase(name).onSuccess { meal ->
                _mealUiState.update {
                    it.toSuccess(meal)
                }
            }.onFailure {
                _mealUiState.update {
                    it.toError()
                }
            }
        }
    }
}