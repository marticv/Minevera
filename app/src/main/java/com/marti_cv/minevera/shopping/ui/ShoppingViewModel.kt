package com.marti_cv.minevera.shopping.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marti_cv.minevera.shopping.ui.model.IngredientModel
import javax.inject.Inject


class ShoppingViewModel @Inject constructor():ViewModel(){
    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog:LiveData<Boolean> = _showDialog

    private val _shoppingList = mutableStateListOf<IngredientModel>()
    val shoppingList:List<IngredientModel> = _shoppingList

    private val _boughtList = mutableStateListOf<IngredientModel>()
    val boughtList:List<IngredientModel> = _boughtList



    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onItemAdded(item: String) {
        _showDialog.value = false
        if (item!="") {
            _shoppingList.add(IngredientModel(itemName = item))
        }
    }

    fun onShowSelected() {
        _showDialog.value=true
    }

    fun onItemSelected(item: IngredientModel) {
        if(item.toBuy){
            item.toBuy=false
            item.isBought=true
            _boughtList.add(item)
            _shoppingList.remove(item)
        }else{
            item.toBuy=true
            item.isBought=false
            _shoppingList.add(item)
            _boughtList.remove(item)
        }
    }

    fun onDelete() {
        _boughtList.clear()
    }
}
