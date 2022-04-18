package com.sanmiade.composemealdbapp.ui.features.mealCategories


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.sanmiade.composemealdbapp.ui.components.MealCategories

@Composable
fun MealCategoriesScreen(
    snackBarHostState: SnackbarHostState,
    navigationEvent: (event: MealCategoriesNavigationEvent) -> Unit
) {
    val viewModel: MealCategoriesViewModel = hiltViewModel()
    val mealCategoriesUiState by viewModel.mealCategoriesUiState.collectAsState()
    MealCategoriesContent(
        modifier = Modifier,
        mealCategoriesUiState = mealCategoriesUiState,
        snackBarHostState = snackBarHostState,
        handleNavigationEvent = navigationEvent,
        handleEvent = viewModel::handleEvent
    )
}

@Composable
fun MealCategoriesContent(
    modifier: Modifier,
    snackBarHostState: SnackbarHostState,
    mealCategoriesUiState: MealCategoriesUiState,
    handleNavigationEvent: (event: MealCategoriesNavigationEvent) -> Unit,
    handleEvent: (event: MealCategoriesEvent) -> Unit,
) {
    if (mealCategoriesUiState.showProgress) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        MealCategories(
            modifier = modifier,
            mealCategoryModels = mealCategoriesUiState.categories,
            onCardClick = { mealCategoryName: String ->
                handleNavigationEvent(
                    MealCategoriesNavigationEvent.ShowMealCategory(
                        mealCategoryName
                    )
                )
            },
        )

        mealCategoriesUiState.error?.let {
            val errorMessage = stringResource(id = it)
            LaunchedEffect(snackBarHostState) {
                snackBarHostState.showSnackbar(
                    message = errorMessage,
                ).also { snackBarResult: SnackbarResult ->
                    when (snackBarResult) {
                        SnackbarResult.Dismissed -> {
                            handleEvent(MealCategoriesEvent.ErrorDismissed)
                        }
                        SnackbarResult.ActionPerformed -> {}
                    }
                }
            }
        }
    }
}