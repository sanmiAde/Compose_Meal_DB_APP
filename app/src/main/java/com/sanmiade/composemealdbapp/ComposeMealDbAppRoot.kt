package com.sanmiade.composemealdbapp

import MealScreen
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.sanmiade.composemealdbapp.Screen.Meals.mealCategoryName
import com.sanmiade.composemealdbapp.ui.components.BottomBar
import com.sanmiade.composemealdbapp.ui.features.mealCategories.MealCategoriesNavigationEvent
import com.sanmiade.composemealdbapp.ui.features.mealCategories.MealCategoriesScreen
import com.sanmiade.composemealdbapp.ui.features.meals.MealsNavigationEvent
import com.sanmiade.composemealdbapp.ui.features.meals.MealsScreen
import com.sanmiade.composemealdbapp.ui.features.savedMeals.SavedMeals
import com.sanmiade.composemealdbapp.ui.features.searchMeals.SearchMealScreen
import com.sanmiade.composemealdbapp.ui.theme.ComposeMealDBAPpTheme
import java.lang.IllegalStateException

sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector? = null
) {
    object MealCategories : Screen(
        "meal_categories", R.string.bottom_bar_meals_categories,
        Icons.Default.List
    )

    object Meals : Screen(
        "meal_category/{meal_category_name}",
        R.string.bottom_bar_meals
    ) {
        val mealCategoryName = "meal_category_name"
        fun createRoute(mealCategoryName: String) = "meal_category/${mealCategoryName}"
    }

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

    object Meal : Screen(
        "meal/{name}",
        R.string.bottom_bar_meals
    ) {
        val mealName = "name"
        fun createRoute(name: String) = "meal/${name}"
    }

}

val bottomBarScreens = listOf(Screen.MealCategories, Screen.SearchMeals, Screen.SavedMeals)

@Composable
fun ComposeMealDbAppRoot(appState: ComposeMealDbAppState = rememberComposeMealDbAppState()) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    ComposeMealDBAPpTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            scaffoldState = scaffoldState,
            topBar = {
                val navBackStackEntry by appState.navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                currentDestination?.route?.let {
                    when (it) {
                        Screen.MealCategories.route -> {
                            com.sanmiade.composemealdbapp.ui.components.TopAppBar(
                                modifier = Modifier,
                                title = "Categories",
                                showNavigationIcon = false
                            ) {
                                appState.navigateBack()
                            }

                        }
                        Screen.Meals.route -> {
                            com.sanmiade.composemealdbapp.ui.components.TopAppBar(
                                modifier = Modifier,
                                title = "Meals",
                                navigationIcon = Icons.Default.ArrowBack
                            ) {
                                appState.navigateBack()
                            }
                        }
                        Screen.Meal.route -> {
                            com.sanmiade.composemealdbapp.ui.components.TopAppBar(
                                modifier = Modifier,
                                title = "Meal",
                                navigationIcon = Icons.Default.ArrowBack
                            ) {
                                appState.navigateBack()
                            }
                        }
                        Screen.SearchMeals.route -> {
                            com.sanmiade.composemealdbapp.ui.components.TopAppBar(
                                modifier = Modifier,
                                title = "Search",
                                showNavigationIcon = false
                            )
                        }
                        Screen.SavedMeals.route -> {
                            com.sanmiade.composemealdbapp.ui.components.TopAppBar(
                                modifier = Modifier,
                                title = "Saved meals",
                                showNavigationIcon = false
                            )
                        }
                        else -> {
                            throw IllegalStateException("Top app bar for this destination doesn't exist")
                        }
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
                    listOf(navArgument(Screen.Meals.mealCategoryName) {
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

                composable(Screen.SearchMeals.route) {
                    SearchMealScreen()
                }

                composable(Screen.SavedMeals.route) {
                    SavedMeals()
                }

                composable(Screen.Meal.route) {
                    MealScreen()
                }
            }
        }
    }
}