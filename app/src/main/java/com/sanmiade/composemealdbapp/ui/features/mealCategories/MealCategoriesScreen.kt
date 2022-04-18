package com.sanmiade.composemealdbapp.ui.features.mealCategories


import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sanmiade.composemealdbapp.ui.components.MealCategories

@Composable
fun MealCategoriesScreen(
    scaffoldState: ScaffoldState,
    navigationEvent: (event: MealCategoriesNavigationEvent) -> Unit
) {
    val viewModel: MealCategoriesViewModel = hiltViewModel()
    val mealCategoriesUiState by viewModel.mealCategoriesUiState.collectAsState()
    MealCategoriesContent(
        modifier = Modifier,
        mealCategoriesUiState = mealCategoriesUiState,
        scaffoldState = scaffoldState,
        handleNavigationEvent = navigationEvent,
        handleEvent = viewModel::handleEvent
    )
}

@Composable
fun MealCategoriesContent(
    modifier: Modifier,
    scaffoldState: ScaffoldState,
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
            LaunchedEffect(scaffoldState.snackbarHostState) {
                scaffoldState.snackbarHostState.showSnackbar(
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