package com.marti_cv.minevera.recipeList.data.network.response

import com.google.gson.annotations.SerializedName
import com.marti_cv.minevera.recipeList.core.network.RecipeNetwork

data class RecipeResponse(@SerializedName("recipes")var recipes : List<RecipeNetwork>)
