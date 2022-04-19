package com.sanmiade.composemealdbapp.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sanmiade.composemealdbapp.domain.model.MealModel

@Entity
data class MealEntity(@PrimaryKey val id: String, val name: String, val thumbNail: String) {
    companion object {
        fun fromDomain(mealModel: MealModel) =
            MealEntity(mealModel.id, mealModel.name, mealModel.thumbNail)
    }
}


fun MealEntity.toDomain() = MealModel(id, name, thumbNail)