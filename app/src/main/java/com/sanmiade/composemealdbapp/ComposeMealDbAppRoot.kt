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
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sanmiade.composemealdbapp.ui.components.BottomBar
import com.sanmiade.composemealdbapp.ui.features.mealCategories.MealCategoriesNavigationEvent
import com.sanmiade.composemealdbapp.ui.features.mealCategories.MealCategoriesScreen
import com.sanmiade.composemealdbapp.ui.features.meals.MealsScreen
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
                TopAppBar(title = { Text(text = "Hello") })
            },
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
                    MealCategoriesScreen(scaffoldState) { event: MealCategoriesNavigationEvent ->
                        when (event) {
                            is MealCategoriesNavigationEvent.ShowMealCategory -> {
                                appState.navController.navigate(
                                    Screen.MealCategory.createRoute(
                                        event.mealId
                                    )
                                )
                            }
                        }
                    }
                }
                composable(
                    Screen.MealCategory.route,
                    listOf(navArgument(Screen.MealCategory.mealCategoryName) {
                        type = NavType.StringType
                    })
                ) { navBackStackEntry: NavBackStackEntry ->
                    val mealCategoryId =
                        navBackStackEntry.arguments?.getString(Screen.MealCategory.mealCategoryName)!!
                    MealsScreen(mealCategoryName = mealCategoryId, scaffoldState) {

                    }
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