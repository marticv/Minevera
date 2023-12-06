package com.marti_cv.minevera.recipeList.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marti_cv.minevera.recipeList.domain.GetRecipesUseCase
import com.marti_cv.minevera.recipeList.ui.model.RecipeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeListViewModel:ViewModel() {

    val getRecipesUseCase=GetRecipesUseCase()

    private val _recipeList= mutableStateListOf<RecipeModel>()
    val recipeList: List<RecipeModel> = _recipeList

    init {
        getRecipes()
    }


    private fun getRecipes(){
        viewModelScope.launch(Dispatchers.IO) {
            val recipes=getRecipesUseCase()
            recipes.map {
                _recipeList.add(it)
            }
        }
    }

}