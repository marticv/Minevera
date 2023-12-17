package com.marti_cv.minevera.shopping.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ShoppingItemDao {

    @Query("SELECT * from ShoppingItemEntity WHERE toBuy=1")
    suspend fun getItemsToBuy(): List<ShoppingItemEntity>

    @Query("SELECT * from ShoppingItemEntity WHERE isBought=1")
    suspend fun getLastItemsBought(): List<ShoppingItemEntity>

    @Insert
    suspend fun addItem(shoppingItemEntity: ShoppingItemEntity)

    @Update
    suspend fun updateItem(shoppingItemEntity: ShoppingItemEntity)

    @Delete
    suspend fun deleteLasBoughtItems(lastBoughtItems:List<ShoppingItemEntity>)

}