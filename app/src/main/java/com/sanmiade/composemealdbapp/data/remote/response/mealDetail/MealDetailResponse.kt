package com.sanmiade.composemealdbapp.data.remote.response.mealDetail


import com.sanmiade.composemealdbapp.domain.model.Ingredient
import com.sanmiade.composemealdbapp.domain.model.MealDetailModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MealDetailResponse(
    @SerialName("dateModified")
    val dateModified: String?,
    @SerialName("idMeal")
    val idMeal: String,
    @SerialName("strArea")
    val strArea: String,
    @SerialName("strCategory")
    val strCategory: String,
    @SerialName("strCreativeCommonsConfirmed")
    val strCreativeCommonsConfirmed: String?,
    @SerialName("strDrinkAlternate")
    val strDrinkAlternate: String?,
    @SerialName("strImageSource")
    val strImageSource: String?,
    @SerialName("strIngredient1")
    val strIngredient1: String?,
    @SerialName("strIngredient10")
    val strIngredient10: String?,
    @SerialName("strIngredient11")
    val strIngredient11: String?,
    @SerialName("strIngredient12")
    val strIngredient12: String?,
    @SerialName("strIngredient13")
    val strIngredient13: String?,
    @SerialName("strIngredient14")
    val strIngredient14: String?,
    @SerialName("strIngredient15")
    val strIngredient15: String?,
    @SerialName("strIngredient16")
    val strIngredient16: String?,
    @SerialName("strIngredient17")
    val strIngredient17: String?,
    @SerialName("strIngredient18")
    val strIngredient18: String?,
    @SerialName("strIngredient19")
    val strIngredient19: String?,
    @SerialName("strIngredient2")
    val strIngredient2: String?,
    @SerialName("strIngredient20")
    val strIngredient20: String?,
    @SerialName("strIngredient3")
    val strIngredient3: String?,
    @SerialName("strIngredient4")
    val strIngredient4: String?,
    @SerialName("strIngredient5")
    val strIngredient5: String?,
    @SerialName("strIngredient6")
    val strIngredient6: String?,
    @SerialName("strIngredient7")
    val strIngredient7: String?,
    @SerialName("strIngredient8")
    val strIngredient8: String?,
    @SerialName("strIngredient9")
    val strIngredient9: String?,
    @SerialName("strInstructions")
    val strInstructions: String,
    @SerialName("strMeal")
    val strMeal: String,
    @SerialName("strMealThumb")
    val strMealThumb: String,
    @SerialName("strMeasure1")
    val strMeasure1: String?,
    @SerialName("strMeasure10")
    val strMeasure10: String?,
    @SerialName("strMeasure11")
    val strMeasure11: String?,
    @SerialName("strMeasure12")
    val strMeasure12: String?,
    @SerialName("strMeasure13")
    val strMeasure13: String?,
    @SerialName("strMeasure14")
    val strMeasure14: String?,
    @SerialName("strMeasure15")
    val strMeasure15: String?,
    @SerialName("strMeasure16")
    val strMeasure16: String?,
    @SerialName("strMeasure17")
    val strMeasure17: String?,
    @SerialName("strMeasure18")
    val strMeasure18: String?,
    @SerialName("strMeasure19")
    val strMeasure19: String?,
    @SerialName("strMeasure2")
    val strMeasure2: String?,
    @SerialName("strMeasure20")
    val strMeasure20: String?,
    @SerialName("strMeasure3")
    val strMeasure3: String?,
    @SerialName("strMeasure4")
    val strMeasure4: String?,
    @SerialName("strMeasure5")
    val strMeasure5: String?,
    @SerialName("strMeasure6")
    val strMeasure6: String?,
    @SerialName("strMeasure7")
    val strMeasure7: String?,
    @SerialName("strMeasure8")
    val strMeasure8: String?,
    @SerialName("strMeasure9")
    val strMeasure9: String?,
    @SerialName("strSource")
    val strSource: String?,
    @SerialName("strTags")
    val strTags: String?,
    @SerialName("strYoutube")
    val strYoutube: String
)

fun MealDetailResponse.toDomain(): MealDetailModel {
    val ingredients = mutableListOf<Ingredient>().apply {
        Ingredient(this@toDomain.strIngredient1, this@toDomain.strMeasure1)
        Ingredient(this@toDomain.strIngredient2, this@toDomain.strMeasure2)
        Ingredient(this@toDomain.strIngredient3, this@toDomain.strMeasure3)
        Ingredient(this@toDomain.strIngredient4, this@toDomain.strMeasure4)
        Ingredient(this@toDomain.strIngredient5, this@toDomain.strMeasure5)
        Ingredient(this@toDomain.strIngredient6, this@toDomain.strMeasure6)
        Ingredient(this@toDomain.strIngredient7, this@toDomain.strMeasure7)
        Ingredient(this@toDomain.strIngredient8, this@toDomain.strMeasure8)
        Ingredient(this@toDomain.strIngredient9, this@toDomain.strMeasure9)
        Ingredient(this@toDomain.strIngredient10, this@toDomain.strMeasure10)
        Ingredient(this@toDomain.strIngredient11, this@toDomain.strMeasure11)
        Ingredient(this@toDomain.strIngredient12, this@toDomain.strMeasure12)
        Ingredient(this@toDomain.strIngredient13, this@toDomain.strMeasure13)
        Ingredient(this@toDomain.strIngredient15, this@toDomain.strMeasure15)
        Ingredient(this@toDomain.strIngredient16, this@toDomain.strMeasure16)
        Ingredient(this@toDomain.strIngredient17, this@toDomain.strMeasure17)
        Ingredient(this@toDomain.strIngredient18, this@toDomain.strMeasure18)
        Ingredient(this@toDomain.strIngredient19, this@toDomain.strMeasure19)
        Ingredient(this@toDomain.strIngredient20, this@toDomain.strMeasure20)
    }
   return MealDetailModel(
        id = idMeal,
        name = strMeal,
        category = strCategory,
        area = strArea,
        instructions = strInstructions,
        thumbNail = strMealThumb,
        videoLink = strYoutube,
        source = strSource
    )
}