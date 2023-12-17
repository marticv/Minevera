package com.marti_cv.minevera.recipeList.domain

import com.marti_cv.minevera.recipeList.data.RecipesRepository
import com.marti_cv.minevera.recipeList.ui.model.RecipeModel
import javax.inject.Inject

class UpdateRecipeUseCase @Inject constructor(private val repository: RecipesRepository) {

    suspend operator fun invoke(recipeModel: RecipeModel){
        repository.updateRecipe(recipeModel)
    }

}