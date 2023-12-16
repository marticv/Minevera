package com.marti_cv.minevera.recipeList.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.marti_cv.minevera.recipeList.ui.model.RecipeModel

@Composable
fun RecipeListScreen(recipeListViewModel: RecipeListViewModel) {

    val recipeList = recipeListViewModel.recipeList
    val isLoading: Boolean by recipeListViewModel.isLoading.observeAsState(initial = false)

    Box(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn() {
                items(recipeList, key = { recipe -> recipe.recipeId }) { recipe ->
                    RecipeItem(
                        recipeModel = recipe,
                        onFavSelected = { recipeListViewModel.onFavSelected(recipe) })
                }
            }
        }
    }
}


@Composable
fun RecipeItem(
    recipeModel: RecipeModel, onFavSelected: (RecipeModel) -> Unit
) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .height(50.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = recipeModel.recipeName, modifier = Modifier.padding(8.dp))
            if (recipeModel.isFavourite) {
                Icon(imageVector = Icons.Default.Favorite,
                    contentDescription = "filled fav icon",
                    modifier = Modifier.clickable { onFavSelected(recipeModel) })
            } else {
                Icon(imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "not filled fav icon",
                    modifier = Modifier.clickable { onFavSelected(recipeModel) })
            }
        }
    }
}