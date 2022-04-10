package com.sanmiade.composemealdbapp.data.remote.response

import com.sanmiade.composemealdbapp.domain.model.MealCategoryModel
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class MealCategory(
    @SerialName("idCategory")
    val id: String,
    @SerialName("strCategory")
    val category: String,
    @SerialName("strCategoryThumb")
    val thumbnail: String,
    @SerialName("strCategoryDescription")
    val description: String
)

@kotlinx.serialization.Serializable
data class MealCategoriesResponse(@SerialName("categories") val data: List<MealCategory>)

fun MealCategory.toDomain() = MealCategoryModel(id, category, thumbnail, description)

fun MealCategoriesResponse.toDomain() = data.map { it.toDomain() }