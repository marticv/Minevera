package com.marti_cv.minevera.shopping.data.di

import android.content.Context
import androidx.room.Room
import com.marti_cv.minevera.shopping.data.IngredientDao
import com.marti_cv.minevera.shopping.data.ShoppingDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideIngredientDao(shoppingDatabase: ShoppingDatabase):IngredientDao{
        return  shoppingDatabase.ingredientDao()
    }

    @Provides
    @Singleton
    fun provideShoppingDatabase(@ApplicationContext appContext: Context): ShoppingDatabase{
        return Room.databaseBuilder(appContext,ShoppingDatabase::class.java,"ShoppingDatabase").build()
    }
}