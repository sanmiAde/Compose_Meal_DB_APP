package com.sanmiade.composemealdbapp.ui.features.meals

sealed interface MealsEvent {
    object ErrorDismissed : MealsEvent
}

sealed interface MealsNavigationEvent {

}