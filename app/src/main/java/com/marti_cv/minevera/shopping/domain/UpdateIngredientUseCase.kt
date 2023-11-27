package com.marti_cv.minevera.shopping.domain

import com.marti_cv.minevera.shopping.data.ShoppingRepository
import com.marti_cv.minevera.shopping.ui.model.IngredientModel
import javax.inject.Inject

class UpdateIngredientUseCase @Inject constructor(private val shoppingRepository: ShoppingRepository) {
    suspend operator fun invoke(ingredientModel: IngredientModel) {
        shoppingRepository.modifyItem(ingredientModel)
    }
}