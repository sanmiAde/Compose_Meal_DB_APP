package com.sanmiade.composemealdbapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sanmiade.composemealdbapp.Screen
import com.sanmiade.composemealdbapp.domain.model.MealCategoryModel
import com.sanmiade.composemealdbapp.ui.features.mealCategories.MealCategoriesEvent
import com.sanmiade.composemealdbapp.ui.features.mealCategories.MealCategoriesNavigationEvent


@Composable
fun MealCategoryItem(
    modifier: Modifier,
    mealCategoryModel: MealCategoryModel,
    event: (MealCategoriesNavigationEvent) -> Unit
) {
    Column(modifier = modifier.clickable {
        event(
            MealCategoriesNavigationEvent.ShowMealCategory(
                mealId = mealCategoryModel.id
            )
        )
    }) {
        Text(text = mealCategoryModel.category)
        Text(text = mealCategoryModel.description, maxLines = 1)
    }
}

@Composable
fun MealCategories(
    modifier: Modifier,
    mealCategoryModels: List<MealCategoryModel>,
    event: (MealCategoriesNavigationEvent) -> Unit
) {
    LazyColumn {
        items(mealCategoryModels.size) { mealCategoryModelIndex ->
            val mealCategoryModel = mealCategoryModels[mealCategoryModelIndex]
            MealCategoryItem(
                modifier = Modifier,
                mealCategoryModel = mealCategoryModel,
                event = event
            )
        }
    }
}