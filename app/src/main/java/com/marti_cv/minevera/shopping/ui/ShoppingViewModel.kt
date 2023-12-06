package com.marti_cv.minevera.shopping.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marti_cv.minevera.shopping.domain.AddIngredientUseCase
import com.marti_cv.minevera.shopping.domain.DeleteLastIngredientsUseCase
import com.marti_cv.minevera.shopping.domain.GetIngredientsToBuyUseCase
import com.marti_cv.minevera.shopping.domain.GetLastBoughtIngedientsUseCase
import com.marti_cv.minevera.shopping.domain.UpdateIngredientUseCase
import com.marti_cv.minevera.shopping.ui.model.IngredientModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ShoppingViewModel @Inject constructor(
    private val addIngredientUseCase: AddIngredientUseCase,
    private val getIngredientsToBuyUseCase: GetIngredientsToBuyUseCase,
    private val getLastBoughtIngredientsUseCase: GetLastBoughtIngedientsUseCase,
    private val updateIngredientUseCase: UpdateIngredientUseCase,
    private val deleteLastIngredientsUseCase: DeleteLastIngredientsUseCase
) : ViewModel() {

    private val _showDialog = MutableLiveData<Boolean>()
    var showDialog: LiveData<Boolean> = _showDialog

    private val _shoppingList = mutableStateListOf<IngredientModel>()
    val shoppingList: List<IngredientModel> = _shoppingList

    private val _boughtList = mutableStateListOf<IngredientModel>()
    val boughtList: List<IngredientModel> = _boughtList

    init {
        getItemsToBuy()
        getLastBoughtItems()
    }


    private fun getItemsToBuy() {
        viewModelScope.launch(Dispatchers.IO) {
            val ingredientList = getIngredientsToBuyUseCase()
            ingredientList.map { _shoppingList.add(it) }
        }
    }

    private fun getLastBoughtItems() {
        viewModelScope.launch(Dispatchers.IO) {
            val ingredientsList = getLastBoughtIngredientsUseCase()
            ingredientsList.map { _boughtList.add(it) }
        }
    }


    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onItemAdded(item: String) {
        _showDialog.value = false
        if (item != "") {
            val newItem = IngredientModel(itemName = item)
            _shoppingList.add(newItem)
            viewModelScope.launch(Dispatchers.IO) {
                addIngredientUseCase(newItem)
            }
        }
    }

    fun onShowSelected() {
        _showDialog.value = true
    }

    fun onItemSelected(item: IngredientModel) {
        if (item.toBuy) {
            item.toBuy = false
            item.isBought = true
            viewModelScope.launch(Dispatchers.IO) {
                updateIngredientUseCase(item)
            }
            _boughtList.add(item)
            _shoppingList.remove(item)
        } else {
            item.toBuy = true
            item.isBought = false
            viewModelScope.launch(Dispatchers.IO) {
                updateIngredientUseCase(item)
            }
            _shoppingList.add(item)
            _boughtList.remove(item)
        }
    }

    fun onDelete() {
        val itemsToDelete:List<IngredientModel> = _boughtList

        viewModelScope.launch(Dispatchers.IO) {
            deleteLastIngredientsUseCase(itemsToDelete)
            _boughtList.clear()
        }
    }
}
