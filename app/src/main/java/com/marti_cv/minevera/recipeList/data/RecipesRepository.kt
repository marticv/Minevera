package com.marti_cv.minevera.recipeList.data

import com.marti_cv.minevera.recipeList.data.network.RecipeService
import com.marti_cv.minevera.recipeList.ui.model.RecipeModel

class RecipesRepository {
    private val api = RecipeService()

    suspend fun getRecipes():List<RecipeModel>{
         return api.getRecipes()
    }
}