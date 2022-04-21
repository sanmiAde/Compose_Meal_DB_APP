package com.sanmiade.composemealdbapp.ui.features.searchMeals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanmiade.composemealdbapp.domain.usecase.SaveMealUseCase
import com.sanmiade.composemealdbapp.domain.usecase.SearchMealUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
@OptIn(FlowPreview::class)
class SearchViewModel @Inject constructor(private val searchMealUseCase: SearchMealUseCase) :
    ViewModel() {
    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()
    private val _searchMealUiState = MutableStateFlow(SearchMealUiState())
    val searchMealUiState = _searchMealUiState.asStateFlow()

    init {
        viewModelScope.launch {
            _query.filterNot { it.isEmpty() }.debounce(300).collect {
                searchMeal(it)
            }
        }
    }

    fun handleEvent(searchMealEvent: SearchMealEvent) {
        when (searchMealEvent) {
            is SearchMealEvent.SearchMeal -> {
                _query.value = searchMealEvent.query
            }
            is SearchMealEvent.DismissError -> {
                dismissError()
            }
        }
    }

    private fun searchMeal(query: String) {
        viewModelScope.launch {
            _searchMealUiState.update { it.toLoading(query = query) }
            searchMealUseCase(query).onSuccess { mealDetails ->
                _searchMealUiState.update {
                    if (mealDetails.isEmpty()) {
                        it.toNotFound("Could not find meal")
                    } else {
                        it.toSuccess(mealDetails)
                    }
                }
            }.onFailure { e ->
                _searchMealUiState.update {
                    it.toError(e.localizedMessage)
                }
            }
        }
    }

    private fun dismissError() {
        _searchMealUiState.update { it.copy(error = null) }
    }
}