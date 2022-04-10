package com.sanmiade.composemealdbapp.ui.features.mealCategories

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanmiade.composemealdbapp.domain.usecase.GetMealCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealCategoriesViewModel @Inject constructor(private val getMealCategoriesUseCase: GetMealCategoriesUseCase) :
    ViewModel() {
    fun click() {
        viewModelScope.launch {
            getMealCategoriesUseCase().onFailure {
                Log.d("Meal", it.toString())
            }.onSuccess {
                Log.d("Meal", it.toString())
            }
        }
    }
}