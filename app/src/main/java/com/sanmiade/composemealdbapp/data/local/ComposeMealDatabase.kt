package com.sanmiade.composemealdbapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sanmiade.composemealdbapp.data.local.dao.MealDao
import com.sanmiade.composemealdbapp.data.local.model.MealEntity

@Database(entities = [MealEntity::class], version = 1)
abstract class ComposeMealDatabase : RoomDatabase() {
    abstract fun mealDao(): MealDao
}