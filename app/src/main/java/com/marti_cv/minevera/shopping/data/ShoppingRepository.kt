package com.marti_cv.minevera.shopping.data

import com.marti_cv.minevera.shopping.ui.model.IngredientModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShoppingRepository @Inject constructor(private val shoppingItemDao: ShoppingItemDao) {

    suspend fun getIngredientsToBuy(): List<IngredientModel> =
        shoppingItemDao.getItemsToBuy().map{it.toModel()}
    suspend fun ingredientsBought(): List<IngredientModel> =
        shoppingItemDao.getLastItemsBought().map { items -> items.toModel()  }

    suspend fun addItem(ingredientModel: IngredientModel) {
        shoppingItemDao.addItem(ingredientModel.toEntity())
    }

    suspend fun modifyItem(ingredientModel: IngredientModel) {
        shoppingItemDao.updateItem(ingredientModel.toEntity())
    }

    suspend fun deleteLastBoughtItems(lastBoughtItems:List<IngredientModel>){
        val entityToDeleteList: List<ShoppingItemEntity> = lastBoughtItems.map { it.toEntity() }
        shoppingItemDao.deleteLasBoughtItems(entityToDeleteList)
    }

}