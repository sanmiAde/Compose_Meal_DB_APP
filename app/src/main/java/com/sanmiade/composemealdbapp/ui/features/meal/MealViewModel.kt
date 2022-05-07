package com.sanmiade.composemealdbapp.ui.features.meal

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.sanmiade.composemealdbapp.MEAL_NAME
import com.sanmiade.composemealdbapp.domain.usecase.SearchMealUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MealViewModel @Inject constructor(
    private val searchMealUseCase: SearchMealUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val categoryName: String = savedStateHandle.get(MEAL_NAME)!!
}