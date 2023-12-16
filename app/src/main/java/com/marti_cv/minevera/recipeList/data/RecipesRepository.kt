package com.marti_cv.minevera.recipeList.data

import com.marti_cv.minevera.recipeList.data.network.RecipeService
import com.marti_cv.minevera.recipeList.ui.model.RecipeModel
import javax.inject.Inject

class RecipesRepository @Inject constructor(private val api:RecipeService) {

    suspend fun getRecipes():List<RecipeModel> =api.getRecipes().map { it.toModel() }
}