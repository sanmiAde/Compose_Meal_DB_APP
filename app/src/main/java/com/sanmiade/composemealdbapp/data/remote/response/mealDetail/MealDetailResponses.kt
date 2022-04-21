package com.sanmiade.composemealdbapp.data.remote.response.mealDetail


import com.sanmiade.composemealdbapp.domain.model.MealDetailModel
import com.sanmiade.composemealdbapp.domain.model.MealModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MealDetailResponses(
    @SerialName("meals")
    val mealDetails: List<MealDetailResponse>?
)

fun MealDetailResponses.toDomain(): List<MealDetailModel> =
    this.mealDetails?.map { it.toDomain() } ?: emptyList()

fun MealDetailResponses.toMealDomain(): List<MealModel> =
    this.mealDetails?.map { MealModel(it.idMeal, it.strMeal, it.strMealThumb) } ?: emptyList()