package com.marti_cv.minevera.shopping.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [IngredientEntity::class], version = 1)
abstract class ShoppingDatabase:RoomDatabase() {
    abstract fun ingredientDao():IngredientDao
}