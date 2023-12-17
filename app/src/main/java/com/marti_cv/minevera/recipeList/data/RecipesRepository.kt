package com.marti_cv.minevera.recipeList.data

import android.util.Log
import com.marti_cv.minevera.recipeList.core.network.RecipeNetwork
import com.marti_cv.minevera.recipeList.data.entities.RecipeEntity
import com.marti_cv.minevera.recipeList.data.network.RecipeService
import com.marti_cv.minevera.recipeList.ui.model.RecipeModel
import javax.inject.Inject

class RecipesRepository @Inject constructor(
    private val api: RecipeService,
    private val recipeDao: RecipeDao
) {


    private suspend fun getRecipesFromNetwork(): List<RecipeNetwork> = api.getRecipes()

    suspend fun insertNetworkRecipesToDB() {

        try {
            getRecipesFromNetwork().map {
                if (recipeDao.recipeInDB(it.recipeId) == 0) {
                    recipeDao.insertRecipeEntity(
                        RecipeEntity(
                            it.recipeId,
                            it.recipeName,
                            if (it.isVegan) {
                                1
                            } else {
                                0
                            },
                            it.difficulty,
                            it.time,
                            it.image,
                            0
                        )
                    )
                }
            }
        } catch (error: Exception) {
            Log.i("error", "internet error marti")
        }
    }

    suspend fun getRecipesFromDataBase(): List<RecipeModel> {
        return recipeDao.getAllRecipes().map {
            RecipeModel(
                it.recipeId, it.recipeName,
                arrayListOf(), it.isVegan != 0, it.difficulty, it.time, it.image, it.isFavourite != 0
            )
        }
    }

    suspend fun updateRecipe(recipeModel: RecipeModel) {
        val recipeEntity = RecipeEntity(
            recipeModel.recipeId, recipeModel.recipeName, if (recipeModel.isVegan) {
                1
            } else {
                0
            }, recipeModel.difficulty, recipeModel.time, recipeModel.image,
            if (recipeModel.isFavourite) {
                0
            } else {
                1
            }
        )
        recipeDao.updateRecipeEntity(recipeEntity)
    }
}