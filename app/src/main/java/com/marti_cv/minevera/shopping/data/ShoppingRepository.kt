package com.marti_cv.minevera.shopping.data

import com.marti_cv.minevera.shopping.ui.model.IngredientModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShoppingRepository @Inject constructor(private val ingredientDao: IngredientDao) {

    suspend fun getIngredientsToBuy(): List<IngredientModel> =
        ingredientDao.getItemsToBuy().map{it.toModel()}
    suspend fun ingredientsBought(): List<IngredientModel> =
        ingredientDao.getLastItemsBought().map { items -> items.toModel()  }

    suspend fun addItem(ingredientModel: IngredientModel) {
        ingredientDao.addItem(ingredientModel.toEntity())
    }

    suspend fun modifyItem(ingredientModel: IngredientModel) {
        ingredientDao.updateItem(ingredientModel.toEntity())
    }

    suspend fun deleteLastBoughtItems(lastBoughtItems:List<IngredientModel>){
        val entityToDeleteList: List<IngredientEntity> = lastBoughtItems.map { it.toEntity() }
        ingredientDao.deleteLasBoughtItems(entityToDeleteList)
    }

}