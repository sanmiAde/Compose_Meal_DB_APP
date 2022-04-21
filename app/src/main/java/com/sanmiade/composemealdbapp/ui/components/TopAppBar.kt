package com.sanmiade.composemealdbapp.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sanmiade.composemealdbapp.Screen
import com.sanmiade.composemealdbapp.ui.features.searchMeals.SearchViewModel

@Composable
fun TopBar(
    modifier: Modifier,
    route: String,
    searchViewModel: SearchViewModel,
    onNavigationIconClick: () -> Unit
) {
    val title = getTitle(route)
    when {
        Screen.SearchMeals.route == route -> {
            SearchTopAppBar(
                searchViewModel = searchViewModel
            )
        }
        title in listOf(Screen.SavedMeals.title, Screen.MealCategories.title) -> {
            androidx.compose.material.TopAppBar(
                modifier = modifier,
                title = { Text(text = title) },
            )
        }
        else -> {
            androidx.compose.material.TopAppBar(
                modifier = modifier,
                title = { Text(text = title) },
                navigationIcon = {
                    IconButton(onClick = { onNavigationIconClick() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Navigation icon"
                        )
                    }
                })
        }
    }
}

@Composable
private fun getTitle(route: String): String {
    val title = when (route) {
        Screen.SavedMeals.route -> {
            Screen.SavedMeals.title
        }
        Screen.SearchMeals.route -> {
            Screen.SearchMeals.title
        }
        Screen.MealCategories.route -> {
            Screen.MealCategories.title
        }
        Screen.Meals.route -> {
            Screen.Meals.title
        }
        Screen.Meal.route -> {
            Screen.Meal.title
        }
        else -> {
            throw IllegalStateException("Top app bar for this destination doesn't exist")
        }
    }
    return title
}