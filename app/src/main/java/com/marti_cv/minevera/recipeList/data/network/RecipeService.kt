package com.marti_cv.minevera.recipeList.data.network

import com.marti_cv.minevera.recipeList.RetrofitHelper
import com.marti_cv.minevera.recipeList.ui.model.RecipeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecipeService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getRecipes():List<RecipeModel>{
         return withContext(Dispatchers.IO){
            val response=retrofit.create(RecipeClient::class.java).getRecipes()
             response.body()?.recipes.orEmpty()
        }
    }
}