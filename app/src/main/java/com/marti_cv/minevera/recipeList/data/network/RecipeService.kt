package com.marti_cv.minevera.recipeList.data.network

import com.marti_cv.minevera.recipeList.ui.model.RecipeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecipeService @Inject constructor(private val recipeClient: RecipeClient) {

    suspend fun getRecipes():List<RecipeModel>{
         return withContext(Dispatchers.IO){
            val response=recipeClient.getRecipes()
             response.body()?.recipes.orEmpty()
        }
    }
}