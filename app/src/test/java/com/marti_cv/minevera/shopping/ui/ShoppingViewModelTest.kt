package com.marti_cv.minevera.shopping.ui

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.marti_cv.minevera.shopping.domain.AddIngredientUseCase
import com.marti_cv.minevera.shopping.domain.DeleteLastIngredientsUseCase
import com.marti_cv.minevera.shopping.domain.GetIngredientsToBuyUseCase
import com.marti_cv.minevera.shopping.domain.GetLastBoughtIngedientsUseCase
import com.marti_cv.minevera.shopping.domain.UpdateIngredientUseCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import javax.inject.Inject

@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(AndroidJUnit4::class)
class ShoppingViewModelTest() {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var addIngredientUseCase: AddIngredientUseCase

    @Inject
    lateinit var updateIngredientUseCase: UpdateIngredientUseCase

    @Inject
    lateinit var deleteLastIngredientsUseCase: DeleteLastIngredientsUseCase

    @Inject
    lateinit var getIngredientsToBuyUseCase: GetIngredientsToBuyUseCase

    @Inject
    lateinit var getLastIngredientsUseCase: GetLastBoughtIngedientsUseCase

    private lateinit var shoppingViewModel:ShoppingViewModel


    @Before
    fun init(){
        hiltRule.inject()
        shoppingViewModel = ShoppingViewModel(addIngredientUseCase,getIngredientsToBuyUseCase,getLastIngredientsUseCase,updateIngredientUseCase,deleteLastIngredientsUseCase)
    }

    @Test
    fun showDialogChange_returnsValueTest(){

        shoppingViewModel.onShowSelected()
        val showDialog=shoppingViewModel.showDialog.value
        assert(showDialog!=null)
    }

    @Test
    fun showDialogChange_changeShowDialogToTrueTest(){

        shoppingViewModel.onShowSelected()
        val showDialog=shoppingViewModel.showDialog.value
        assert(showDialog!!)
    }

    @Test
    fun onDialogClose_changeShowDialogToFalseTest(){
        shoppingViewModel.onDialogClose()
        val showDialog =shoppingViewModel.showDialog.value
        assert(showDialog==false)
    }

    @Test
    fun onDelete_clearsListTest(){
        shoppingViewModel.onDelete()
        assert(shoppingViewModel.boughtList.isEmpty())
    }

    @Test
    fun onItemAdded_addsItemToShoppingListTest(){
        val initialListSize =shoppingViewModel.shoppingList.size
        shoppingViewModel.onItemAdded("test")
        val finalListSize =shoppingViewModel.shoppingList.size
        assert(initialListSize+1==finalListSize)
    }

    @Test
    fun onItemAdded_addsCorrectItemToShoppingListTest(){
        shoppingViewModel.onItemAdded("test")
        val lastItem = shoppingViewModel.shoppingList.last()
        assert(lastItem.itemName == "test")
    }

    @Test
    fun onItemSelected_changeItemToBuyToFalseTest(){
        shoppingViewModel.onItemAdded("test")
        val lastItem = shoppingViewModel.shoppingList.last()
        shoppingViewModel.onItemSelected(lastItem)
        assert( !lastItem.toBuy)
    }

    @Test
    fun onItemSelected_changeItemToIsBoughtToFalseTest(){
        shoppingViewModel.onItemAdded("test")
        val lastItem = shoppingViewModel.shoppingList.last()
        shoppingViewModel.onItemSelected(lastItem)
        assert( lastItem.isBought)
    }

    @Test
    fun onItemSelected_deletesItemFromShoppingListTest(){
        shoppingViewModel.onItemAdded("test")
        val lastItem = shoppingViewModel.shoppingList.last()
        shoppingViewModel.onItemSelected(lastItem)
        assert( !shoppingViewModel.shoppingList.contains(lastItem))
    }

    @Test
    fun onItemSelected_changesItemFromShoppingListToBoughtListTest(){
        shoppingViewModel.onItemAdded("test")
        val lastItem = shoppingViewModel.shoppingList.last()
        shoppingViewModel.onItemSelected(lastItem)
        assert( shoppingViewModel.boughtList.contains(lastItem))
    }

}
