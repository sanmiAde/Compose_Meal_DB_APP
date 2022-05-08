package com.sanmiade.composemealdbapp.domain.model

data class MealDetailModel(
    val id: String,
    val name: String,
    val category: String,
    val area: String,
    val instructions: String,
    val thumbNail: String,
    val videoLink: String,
    val source: String?,
    val ingredients : List<Ingredient>
)

data class Ingredient(val item: String?, val quantity: String?) {
    override fun toString(): String {
        return "${quantity ?: ""} of ${item ?: ""}"
    }
}