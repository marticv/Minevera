package com.marti_cv.minevera.recipeList.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.marti_cv.minevera.recipeList.ui.model.RecipeModel

@Composable
fun RecipeListScreen(recipeListViewModel: RecipeListViewModel){

    val recipeList= recipeListViewModel.recipeList

    Box(modifier = Modifier.fillMaxSize()){
        LazyColumn(){
            items(recipeList){
                RecipItem(recipeModel = it)
            }
        }
    }
}

@Composable
fun RecipItem(
    recipeModel: RecipeModel
) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)){
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = recipeModel.recipeName, modifier = Modifier.padding(8.dp))
        }
    }
}