package com.marti_cv.minevera.recipeList.data.network.response

import com.google.gson.annotations.SerializedName
import com.marti_cv.minevera.recipeList.ui.model.RecipeModel

data class RecipeResponse(@SerializedName("recipes")var recipes : List<RecipeModel>)
