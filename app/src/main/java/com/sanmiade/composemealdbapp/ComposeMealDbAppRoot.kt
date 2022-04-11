package com.sanmiade.composemealdbapp

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sanmiade.composemealdbapp.ui.components.BottomBar
import com.sanmiade.composemealdbapp.ui.features.mealCategories.MealCategoriesNavigationEvent
import com.sanmiade.composemealdbapp.ui.features.mealCategories.MealCategoriesScreen
import com.sanmiade.composemealdbapp.ui.features.mealCategory.MealScreen
import com.sanmiade.composemealdbapp.ui.features.savedMeals.SavedMeals
import com.sanmiade.composemealdbapp.ui.features.searchMeals.SearchMealScreen
import com.sanmiade.composemealdbapp.ui.theme.ComposeMealDBAPpTheme

sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector? = null
) {
    object MealCategories : Screen(
        "meal_categories", R.string.bottom_bar_meals_categories,
        Icons.Default.List
    )

    object MealCategory : Screen(
        "meal_category",
        R.string.bottom_bar_meals
    )

    object SavedMeals :
        Screen(
            "saved_meals", R.string.bottom_bar_saved_meals,
            Icons.Default.Favorite
        )

    object SearchMeals :
        Screen(
            "search_meals", R.string.bottom_bar_search_meals,
            Icons.Default.Search
        )
}

val bottomBarScreens = listOf(Screen.MealCategories, Screen.SearchMeals, Screen.SavedMeals)

@Composable
fun ComposeMealDbAppRoot(appState: ComposeMealDbAppState = rememberComposeMealDbAppState()) {
    ComposeMealDBAPpTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                BottomBar(appState)
            }
        ) {
            NavHost(
                navController = appState.navController,
                startDestination = Screen.MealCategories.route,
                modifier = Modifier.padding(it)
            ) {
                composable(Screen.MealCategories.route) {
                    MealCategoriesScreen { event: MealCategoriesNavigationEvent ->
                        when (event) {
                            is MealCategoriesNavigationEvent.ShowMealCategory -> {
                                appState.navController.navigate(Screen.MealCategory.route)
                            }
                        }
                    }
                }
                composable(Screen.MealCategory.route) {
                    MealScreen()
                }

                composable(Screen.SearchMeals.route) {
                    SearchMealScreen()
                }

                composable(Screen.SavedMeals.route) {
                    SavedMeals()
                }
            }
        }
    }
}