package com.marti_cv.minevera.shopping.data.di

import android.content.Context
import androidx.room.Room
import com.marti_cv.minevera.recipeList.data.RecipeDao
import com.marti_cv.minevera.shopping.data.MiNeveraDatabase
import com.marti_cv.minevera.shopping.data.ShoppingItemDao
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
    fun provideShoppingItemDao(miNeveraDatabase: MiNeveraDatabase):ShoppingItemDao{
        return  miNeveraDatabase.shoppingItemDao()
    }

    @Provides
    fun provideRecipeDao(miNeveraDatabase: MiNeveraDatabase):RecipeDao{
        return  miNeveraDatabase.recipeDao()
    }

    @Provides
    @Singleton
    fun provideMiNeveraDatabase(@ApplicationContext appContext: Context): MiNeveraDatabase{
        return Room.databaseBuilder(appContext,MiNeveraDatabase::class.java,"MiNeveraDatabase").build()
    }
}