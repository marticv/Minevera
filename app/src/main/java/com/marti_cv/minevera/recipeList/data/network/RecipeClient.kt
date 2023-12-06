package com.marti_cv.minevera.recipeList.data.network

import com.marti_cv.minevera.recipeList.data.network.response.RecipeResponse
import retrofit2.Response
import retrofit2.http.GET

interface RecipeClient {
    @GET("myrecipes.json")
    suspend fun getRecipes():Response<RecipeResponse>

}