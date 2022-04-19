package com.sanmiade.composemealdbapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sanmiade.composemealdbapp.data.local.model.MealEntity

@Dao
interface MealDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMeal(mealEntity: MealEntity)

    @Delete
    suspend fun deleteMeal(mealEntity: MealEntity)

    @Query("select * from MealEntity where id = :id")
    suspend fun getMeal(id: String) : MealEntity?
}