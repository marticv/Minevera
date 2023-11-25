package com.marti_cv.minevera.shopping.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class IngredientEntity(
    @PrimaryKey
    val id: Int,
    val itemName: String,
    var toBuy: Int,
    var isBought: Int
)
