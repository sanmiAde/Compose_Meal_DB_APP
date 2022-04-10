package com.sanmiade.composemealdbapp

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
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
    object MealCategories : Screen("meal_categories", R.string.bottom_bar_meals_categories,Icons.Default.List)
    object MealCategory : Screen("meal_category", R.string.bottom_bar_meals)
    object SavedMeals : Screen("saved_meals", R.string.bottom_bar_saved_meals, Icons.Default.Favorite )
    object SearchMeals : Screen("search_meals", R.string.bottom_bar_search_meals, Icons.Default.Search )
}

val screens = listOf(Screen.MealCategories, Screen.SearchMeals, Screen.SavedMeals)

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
                startDestination = Screen.MealCategories.route
            ) {
                composable(Screen.MealCategories.route) {
                    MealCategoriesScreen { event: MealCategoriesNavigationEvent ->
                        when (event) {
                            MealCategoriesNavigationEvent.ShowMealCategory -> {
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

@Composable
private fun BottomBar(appState: ComposeMealDbAppState) {
    BottomAppBar {
        val navBackStackEntry by appState.navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        screens.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(screen.icon!!, contentDescription = null) },
                label = { Text(stringResource(screen.resourceId)) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    appState.navController.navigate(screen.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(appState.navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}