package com.marti_cv.minevera.shopping.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface IngredientDao {

    @Query("SELECT * from IngredientEntity WHERE toBuy=1")
    fun getItemsToBuy(): Flow<List<IngredientEntity>>

    @Query("SELECT * from IngredientEntity WHERE isBought=1")
    fun getLastItemsBought(): Flow<List<IngredientEntity>>

    @Insert
    suspend fun addItem(ingredientEntity: IngredientEntity)

    @Update
    suspend fun updateItem(ingredientEntity: IngredientEntity)

}