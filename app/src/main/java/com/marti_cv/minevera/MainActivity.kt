package com.marti_cv.minevera

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.marti_cv.minevera.recipeList.ui.RecipeListViewModel
import com.marti_cv.minevera.shopping.ui.ShoppingViewModel
import com.marti_cv.minevera.ui.AppScreen
import com.marti_cv.minevera.ui.theme.MineveraTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val shoppingViewModel: ShoppingViewModel by viewModels()
    private val recipeListViewModel: RecipeListViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MineveraTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppScreen(shoppingViewModel = shoppingViewModel, recipeListViewModel = recipeListViewModel)
                }
            }
        }
    }
}