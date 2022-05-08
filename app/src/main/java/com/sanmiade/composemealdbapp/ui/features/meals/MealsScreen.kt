package com.sanmiade.composemealdbapp.ui.features.meals

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.sanmiade.composemealdbapp.ui.components.Meals

@Composable
fun MealsScreen(
    snackBarHostState: SnackbarHostState,
    navigationEvent: (MealsNavigationEvent) -> Unit
) {
    val mealsViewModel: MealsViewModel = hiltViewModel()
    val mealsUiState by mealsViewModel.mealsUiState.collectAsState()

    MealsContent(
        modifier = Modifier,
        snackBarHostState = snackBarHostState,
        mealsUiState = mealsUiState,
        handleNavigationEvent = navigationEvent,
        handleEvent = mealsViewModel::handleEvent
    )
}

@Composable
fun MealsContent(
    modifier: Modifier,
    snackBarHostState: SnackbarHostState,
    mealsUiState: MealsUiState,
    handleNavigationEvent: (event: MealsNavigationEvent) -> Unit,
    handleEvent: (event: MealsEvent) -> Unit,
) {
    if (mealsUiState.showProgress) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        mealsUiState.meals?.let { meals ->
            Meals(modifier = modifier,
                mealCategoryModels = meals,
                onDoubleCardClick = {
                    handleEvent(MealsEvent.SaveMeal(it))
                }, onCardClick = {
                    handleNavigationEvent(MealsNavigationEvent.ShowMeal(it))
                })
        }
        mealsUiState.error?.let {
            val errorMessage = stringResource(id = it)
            LaunchedEffect(snackBarHostState) {
                snackBarHostState.showSnackbar(
                    message = errorMessage,
                ).also { snackBarResult: SnackbarResult ->
                    when (snackBarResult) {
                        SnackbarResult.Dismissed -> {
                            handleEvent(MealsEvent.ErrorDismissed)
                        }
                        SnackbarResult.ActionPerformed -> {}
                    }
                }
            }
        }
    }
}