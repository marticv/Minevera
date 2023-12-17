package com.marti_cv.minevera.recipeList.domain

import com.marti_cv.minevera.recipeList.data.RecipesRepository
import com.marti_cv.minevera.recipeList.ui.model.RecipeModel
import javax.inject.Inject

class GetRecipesUseCase @Inject constructor(private val repository: RecipesRepository) {

    suspend operator fun invoke():List<RecipeModel>{
        repository.insertNetworkRecipesToDB()
        return repository.getRecipesFromDataBase()
    }

}