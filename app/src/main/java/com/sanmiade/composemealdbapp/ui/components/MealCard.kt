@file:OptIn(ExperimentalMaterialApi::class)

package com.sanmiade.composemealdbapp.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sanmiade.composemealdbapp.domain.model.MealModel

@Composable
fun MealCard(
    modifier: Modifier,
    mealModel: MealModel,
    onCardClick: (mealCategoryId: String) -> Unit
) {
    Card(modifier = modifier, onClick = {
        onCardClick(mealModel.id)
    }) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                modifier = Modifier.height(150.dp),
                model = mealModel.thumbNail,
                contentDescription = mealModel.name,
                contentScale = ContentScale.FillBounds,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(text = mealModel.name, modifier = Modifier.padding(top = 4.dp), maxLines = 1)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Meals(
    modifier: Modifier,
    mealCategoryModels: List<MealModel>,
    onCardClick: (mealCategoryId: String) -> Unit,
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2)
    ) {
        items(mealCategoryModels.size) { mealCategoryModelIndex ->
            val mealCategoryModel = mealCategoryModels[mealCategoryModelIndex]
            MealCard(
                modifier = modifier
                    .height(200.dp)
                    .padding(8.dp),
                mealModel = mealCategoryModel,
                onCardClick = onCardClick
            )
        }
    }
}