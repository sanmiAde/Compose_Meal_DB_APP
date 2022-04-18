package com.sanmiade.composemealdbapp.ui.components

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sanmiade.composemealdbapp.domain.model.MealCategoryModel


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MealCategoryCard(
    modifier: Modifier,
    mealCategoryModel: MealCategoryModel,
    onCardClick: (id: String) -> Unit,
) {
    val context = LocalContext.current
    Card(modifier = modifier, onClick = {
        onCardClick(mealCategoryModel.title)
    }) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                modifier = Modifier.height(150.dp),
                model = mealCategoryModel.thumbnail,
                contentDescription = mealCategoryModel.title,
                contentScale = ContentScale.FillBounds,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = mealCategoryModel.title)
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Meal category info",
                    Modifier.clickable {
                        // TODO: Use bottom sheet here
                        Toast.makeText(context, mealCategoryModel.description, Toast.LENGTH_SHORT)
                            .show()
                    })
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MealCategories(
    modifier: Modifier,
    mealCategoryModels: List<MealCategoryModel>,
    onCardClick: (mealCategoryId: String) -> Unit,
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2)
    ) {
        items(mealCategoryModels.size) { mealCategoryModelIndex ->
            val mealCategoryModel = mealCategoryModels[mealCategoryModelIndex]
            MealCategoryCard(
                modifier = modifier
                    .height(200.dp)
                    .padding(8.dp),
                mealCategoryModel = mealCategoryModel,
                onCardClick = onCardClick,
            )
        }
    }
}