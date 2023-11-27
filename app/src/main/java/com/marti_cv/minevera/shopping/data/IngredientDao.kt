package com.marti_cv.minevera.shopping.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface IngredientDao {

    @Query("SELECT * from IngredientEntity WHERE toBuy=1")
    suspend fun getItemsToBuy(): List<IngredientEntity>

    @Query("SELECT * from IngredientEntity WHERE isBought=1")
    suspend fun getLastItemsBought(): List<IngredientEntity>

    @Insert
    suspend fun addItem(ingredientEntity: IngredientEntity)

    @Update
    suspend fun updateItem(ingredientEntity: IngredientEntity)

    @Delete
    suspend fun deleteLasBoughtItems(lastBoughtItems:List<IngredientEntity>)

}