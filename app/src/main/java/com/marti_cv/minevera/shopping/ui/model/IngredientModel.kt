package com.marti_cv.minevera.shopping.ui.model

import com.marti_cv.minevera.shopping.data.IngredientEntity

data class IngredientModel(
    val id: Int = System.currentTimeMillis().hashCode(),
    val itemName: String,
    var toBuy: Boolean = true,
    var isBought: Boolean = false
) {

    fun toEntity(): IngredientEntity {
        return IngredientEntity(
            this.id,
            this.itemName,
            if (this.toBuy) {
                1
            } else {
                0
            },
            if (this.isBought) {
                1
            } else {
                0
            }
        )
    }

}


