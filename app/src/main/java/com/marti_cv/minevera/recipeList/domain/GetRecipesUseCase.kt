package com.marti_cv.minevera.recipeList.domain

import com.marti_cv.minevera.recipeList.data.RecipesRepository
import com.marti_cv.minevera.recipeList.ui.model.RecipeModel

class GetRecipesUseCase {
    private val repository = RecipesRepository()

    suspend operator fun invoke():List<RecipeModel>{
        return repository.getRecipes()
    }

}