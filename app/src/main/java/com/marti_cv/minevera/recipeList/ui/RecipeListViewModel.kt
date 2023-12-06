package com.marti_cv.minevera.recipeList.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marti_cv.minevera.recipeList.domain.GetRecipesUseCase
import com.marti_cv.minevera.recipeList.ui.model.RecipeModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(private val getRecipesUseCase: GetRecipesUseCase) :ViewModel() {

    private val _recipeList= mutableStateListOf<RecipeModel>()
    val recipeList: List<RecipeModel> = _recipeList

   private val _isLoading= MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        getRecipes()
    }


    private fun getRecipes(){
        viewModelScope.launch {
            _isLoading.value=true
            val recipes=getRecipesUseCase()
            recipes.map {
                _recipeList.add(it)
            }
            _isLoading.value=false
        }
    }

}