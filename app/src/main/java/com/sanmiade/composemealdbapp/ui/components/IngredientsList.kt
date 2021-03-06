package com.sanmiade.composemealdbapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sanmiade.composemealdbapp.domain.model.Ingredient

@Composable
fun IngredientsList(modifier: Modifier, ingredients: List<Ingredient>) {
    LazyColumn(modifier.height(200.dp)) {
        item { IngredientsHeader(ingredients) }
        items(ingredients.size) { ingredientIndex ->
            val ingredient = ingredients[ingredientIndex]
            IngredientRow(modifier = Modifier.fillMaxWidth(), ingredient = ingredient)
        }
    }
}

@Composable
fun IngredientsHeader(ingredients: List<Ingredient>) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = "Ingredients")
        // TODO: Check out localisation library. I think it supports pluralisation.
        Text("${ingredients.size} Items")
    }
}

@Composable
fun IngredientRow(modifier: Modifier, ingredient: Ingredient) {
    Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = "${ingredient.item}")
        Text(text = "${ingredient.quantity}")
    }
}