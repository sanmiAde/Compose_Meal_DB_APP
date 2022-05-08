package com.sanmiade.composemealdbapp

import MealScreen
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.sanmiade.composemealdbapp.ui.components.BottomBar
import com.sanmiade.composemealdbapp.ui.components.TopBar
import com.sanmiade.composemealdbapp.ui.features.mealCategories.MealCategoriesNavigationEvent
import com.sanmiade.composemealdbapp.ui.features.mealCategories.MealCategoriesScreen
import com.sanmiade.composemealdbapp.ui.features.meals.MealsNavigationEvent
import com.sanmiade.composemealdbapp.ui.features.meals.MealsScreen
import com.sanmiade.composemealdbapp.ui.features.savedMeals.SavedMeals
import com.sanmiade.composemealdbapp.ui.features.searchMeals.SearchMealScreen
import com.sanmiade.composemealdbapp.ui.features.searchMeals.SearchNavigationEvent
import com.sanmiade.composemealdbapp.ui.features.searchMeals.SearchViewModel
import com.sanmiade.composemealdbapp.ui.theme.ComposeMealDBAPpTheme

const val MEAL_CATEGORIES_NAME = "meal_category_name"
const val MEAL_NAME = "meal_name"

sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int,
    val title: String,
    val icon: ImageVector? = null
) {
    open fun createRoute(arg: String): String = route

    object MealCategories : Screen(
        "meal_categories", R.string.bottom_bar_meals_categories,
        "Categories",
        Icons.Default.List
    )

    object Meals : Screen(
        "meal_category/{meal_category_name}",
        R.string.bottom_bar_meals,
        "Meals",
    ) {
        override fun createRoute(arg: String) = "meal_category/${arg}"
    }

    object SavedMeals :
        Screen(
            "saved_meals", R.string.bottom_bar_saved_meals,
            "Saved Meals",
            Icons.Default.Favorite
        )

    object SearchMeals :
        Screen(
            "search_meals", R.string.bottom_bar_search_meals,
            "Search Meals",
            Icons.Default.Search
        ) {
    }

    object Meal : Screen(
        "meal/{meal_name}",
        R.string.bottom_bar_meals,
        "Meal",
    ) {
        override fun createRoute(arg: String) = "meal/${arg}"
    }

}

val bottomBarScreens = listOf(Screen.MealCategories, Screen.SearchMeals, Screen.SavedMeals)

@Composable
fun ComposeMealDbAppRoot(appState: ComposeMealDbAppState = rememberComposeMealDbAppState()) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val searchViewModel = hiltViewModel<SearchViewModel>()

    ComposeMealDBAPpTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            scaffoldState = scaffoldState,
            topBar = {
                val navBackStackEntry by appState.navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                currentDestination?.route?.let {
                    TopBar(modifier = Modifier, route = it, searchViewModel = searchViewModel) {
                        appState.navigateBack()
                    }
                }
            },
            bottomBar = {
                BottomBar(appState)
            }
        ) { paddingValues ->
            NavHost(
                navController = appState.navController,
                startDestination = Screen.MealCategories.route,
                modifier = Modifier.padding(paddingValues)
            ) {
                composable(Screen.MealCategories.route) {
                    MealCategoriesScreen(scaffoldState.snackbarHostState) { event: MealCategoriesNavigationEvent ->
                        when (event) {
                            is MealCategoriesNavigationEvent.ShowMealCategory -> {
                                appState.navController.navigate(
                                    Screen.Meals.createRoute(
                                        event.mealId
                                    )
                                )
                            }
                        }
                    }
                }
                composable(
                    Screen.Meals.route,
                    listOf(navArgument(MEAL_CATEGORIES_NAME) {
                        type = NavType.StringType
                    })
                ) {
                    MealsScreen(scaffoldState.snackbarHostState) { mealsNavigationEvent ->
                        when (mealsNavigationEvent) {
                            is MealsNavigationEvent.ShowMeal -> {
                                appState.navController.navigate(
                                    Screen.Meal.createRoute(
                                        mealsNavigationEvent.id
                                    )
                                )
                            }
                        }
                    }
                }

                composable(
                    route = Screen.SearchMeals.route,
                ) {
                    SearchMealScreen(searchViewModel) { searchNavigationEvent: SearchNavigationEvent ->
                        when (searchNavigationEvent) {
                            is SearchNavigationEvent.ShowMeal -> {
                                appState.navController.navigate(
                                    Screen.Meal.createRoute(
                                        searchNavigationEvent.id
                                    )
                                )
                            }
                        }
                    }
                }

                composable(Screen.SavedMeals.route) {
                    SavedMeals()
                }

                composable(Screen.Meal.route,
                    listOf(navArgument(MEAL_NAME) {
                    type = NavType.StringType
                })) {
                    MealScreen()
                }
            }
        }
    }
}