package com.marti_cv.minevera.shopping.data

import com.marti_cv.minevera.shopping.ui.model.IngredientModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShoppingRepository @Inject constructor(private val ingredientDao: IngredientDao) {

    val ingredientsToBuy: Flow<List<IngredientModel>> =
        ingredientDao.getItemsToBuy().map { items -> items.map { it.toModel() } }
    val ingredientsBought: Flow<List<IngredientModel>> =
        ingredientDao.getLastItemsBought().map { items -> items.map { it.toModel() } }

    suspend fun addItem(ingredientModel: IngredientModel) {
        ingredientDao.addItem(ingredientModel.toEntity())
    }

    suspend fun modifyItem(ingredientModel: IngredientModel) {
        ingredientDao.updateItem(ingredientModel.toEntity())
    }

}