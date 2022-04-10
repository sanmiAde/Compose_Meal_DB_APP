package com.sanmiade.composemealdbapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberComposeMealDbAppState(navController: NavHostController = rememberNavController()) =
    remember(navController) {
        ComposeMealDbAppState(navController)
    }

class ComposeMealDbAppState(val navController: NavHostController) {
    fun navigateBack() {
        navController.popBackStack()
    }

    fun navigateUp() {
        navController.navigateUp()
    }
}