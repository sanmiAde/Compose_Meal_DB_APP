package com.sanmiade.composemealdbapp.ui.features.searchMeals

interface SearchMealEvent {
    data class SearchMeal(val query: String) : SearchMealEvent
    object DismissError : SearchMealEvent
}

interface SearchNavigationEvent {
   data class ShowMeal(val id: String) : SearchNavigationEvent
}