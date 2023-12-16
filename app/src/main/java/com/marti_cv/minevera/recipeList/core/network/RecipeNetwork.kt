package com.marti_cv.minevera.recipeList.core.network

import com.marti_cv.minevera.recipeList.ui.model.RecipeModel

data class RecipeNetwork(
    val recipeId:String,
    val recipeName: String,
    val ingredients: ArrayList<String>,
    val isVegan: Boolean,
    val difficulty: String,
    val time: Int,
    val image: String,
) {
    fun toModel(): RecipeModel {
        return RecipeModel(
            this.recipeId,
            this.recipeName,
            this.ingredients,
            this.isVegan,
            this.difficulty,
            this.time,
            this.image,
            false
        )
    }
}
