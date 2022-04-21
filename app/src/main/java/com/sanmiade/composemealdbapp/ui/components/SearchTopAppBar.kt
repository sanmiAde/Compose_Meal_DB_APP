package com.sanmiade.composemealdbapp.ui.components

import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.sanmiade.composemealdbapp.ui.features.searchMeals.SearchMealEvent
import com.sanmiade.composemealdbapp.ui.features.searchMeals.SearchViewModel

@Composable
fun SearchTopAppBar(modifier: Modifier, searchViewModel: SearchViewModel) {
    var query by remember {
        mutableStateOf(searchViewModel.query.value)
    }
    // TopAppBar(modifier = modifier, title = "", navigationIcon = )
    OutlinedTextField(value = query, onValueChange = {
        query = it
        searchViewModel.handleEvent(SearchMealEvent.SearchMeal(it))
    })
}