package com.marti_cv.minevera.shopping.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.marti_cv.minevera.recipeList.data.RecipeDao
import com.marti_cv.minevera.recipeList.data.entities.RecipeEntity

@Database(entities = [ShoppingItemEntity::class,
                     RecipeEntity::class], version = 1)
abstract class MiNeveraDatabase:RoomDatabase() {
    abstract fun shoppingItemDao():ShoppingItemDao

    abstract fun recipeDao():RecipeDao
}