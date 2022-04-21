package com.sanmiade.composemealdbapp.data.remote.response.meals


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MealResponses(
    @SerialName("meals")
    val meals: List<MealResponse>
)

fun MealResponses.toDomain() = meals.map { it.toDomain() }