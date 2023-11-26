package com.marti_cv.minevera.shopping.domain

import com.marti_cv.minevera.shopping.data.ShoppingRepository
import com.marti_cv.minevera.shopping.ui.model.IngredientModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetIngredientsToBuyUseCase @Inject constructor(
    private val shoppingRepository: ShoppingRepository
) {
    operator fun invoke():Flow<List<IngredientModel>>{
        return shoppingRepository.ingredientsToBuy
    }
}