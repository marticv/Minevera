package com.marti_cv.minevera.recipeList.ui.model

data class RecipeModel(
    val recipeName: String,
    val ingredients: ArrayList<String>,
    val isVegan: Boolean,
    val difficulty: String,
    val time: Int,
    val image: String,
    var isFavourite: Boolean
)
