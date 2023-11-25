package com.marti_cv.minevera.shopping.ui.model

data class IngredientModel(
    val id: Long = System.currentTimeMillis(),
    val itemName: String,
    var toBuy: Boolean=true,
    var isBought: Boolean=false
)
