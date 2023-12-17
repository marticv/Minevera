package com.marti_cv.minevera.recipeList.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.marti_cv.minevera.recipeList.data.entities.RecipeEntity

@Dao
interface RecipeDao{

    @Query("SELECT * FROM RecipeEntity")
    suspend fun getAllRecipes():List<RecipeEntity>

    @Query("SELECT COUNT(recipeId) FROM recipeentity WHERE recipeId LIKE :recipeId")
    suspend fun recipeInDB(recipeId: String):Int

    @Insert
    suspend fun insertRecipeEntity(recipeEntity:RecipeEntity)

    @Update
    suspend fun updateRecipeEntity(recipeEntity: RecipeEntity)
}
