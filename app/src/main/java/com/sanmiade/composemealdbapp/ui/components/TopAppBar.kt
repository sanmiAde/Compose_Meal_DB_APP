package com.sanmiade.composemealdbapp.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.sanmiade.composemealdbapp.Screen
import com.sanmiade.composemealdbapp.bottomBarScreens
import java.lang.IllegalStateException

@Composable
fun Topbar(
    modifier: Modifier,
    route: String,
    onNavigationIconClick: () -> Unit
) {
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
    if (title in bottomBarScreens.map { it.title }) {
        androidx.compose.material.TopAppBar(
            modifier = modifier,
            title = { Text(text = title) },
        )
    } else {
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