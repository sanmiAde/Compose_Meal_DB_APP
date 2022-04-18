package com.sanmiade.composemealdbapp.data.remote.response.meals


import com.sanmiade.composemealdbapp.domain.model.MealModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MealResponse(
    @SerialName("idMeal")
    val idMeal: String,
    @SerialName("strMeal")
    val strMeal: String,
    @SerialName("strMealThumb")
    val strMealThumb: String
)

fun MealResponse.toDomain() = MealModel(id = idMeal, name = strMeal, thumbNail = strMealThumb)