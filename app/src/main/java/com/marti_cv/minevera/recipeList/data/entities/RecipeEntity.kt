package com.marti_cv.minevera.recipeList.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecipeEntity(
    @PrimaryKey
    val recipeId:String,
    val recipeName: String,
    val isVegan: Int,
    val difficulty: String,
    val time: Int,
    val image: String,
    var isFavourite: Int
)
