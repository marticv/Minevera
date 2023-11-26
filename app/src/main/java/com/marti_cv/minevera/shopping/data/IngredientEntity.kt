package com.marti_cv.minevera.shopping.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.marti_cv.minevera.shopping.ui.model.IngredientModel

@Entity
data class IngredientEntity(
    @PrimaryKey
    val id: Int,
    val itemName: String,
    var toBuy: Int,
    var isBought: Int
) {
    fun toModel(): IngredientModel {
        return IngredientModel(
            this.id,
            this.itemName,
            toBuy != 0,
            isBought != 0
        )
    }
}
