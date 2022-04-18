package com.sanmiade.composemealdbapp.data.remote.response.meals


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MealsResponse(
    @SerialName("meals")
    val meals: List<MealResponse>
)

fun MealsResponse.toDomain() = meals.map { it.toDomain() }