package com.marti_cv.minevera.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.marti_cv.minevera.recipeList.ui.RecipeListScreen
import com.marti_cv.minevera.recipeList.ui.RecipeListViewModel
import com.marti_cv.minevera.shopping.ui.ShoppingViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen(shoppingViewModel: ShoppingViewModel, recipeListViewModel: RecipeListViewModel) {

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()


    Scaffold(topBar = { MiNeveraTopBar({}, {}) }, bottomBar = { MiNeveraBottomBar() }) {
        Box(modifier = Modifier.padding(it)) {
            //ShoppingScreen(shoppingViewModel = shoppingViewModel)
            RecipeListScreen(recipeListViewModel = recipeListViewModel)
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiNeveraTopBar(onClickBack: () -> Unit, onClickClose: () -> Unit) {

    TopAppBar(title = { Text(text = "MiNeverApp") },
        modifier = Modifier.background(Color.Green),
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.Red, titleContentColor = Color.White
        ),
        navigationIcon = {
            IconButton(onClick = { onClickBack }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back icon")
            }
        },
        actions = {
            IconButton(onClick = { onClickClose }) {
                Icon(imageVector = Icons.Filled.Close, contentDescription = "Back icon")
            }
        })
}

@Composable
fun MiNeveraBottomBar() {
    var itemNumber by remember {
        mutableIntStateOf(0)
    }

    NavigationBar(containerColor = Color.Red, contentColor = Color.White) {
        NavigationBarItem(selected = itemNumber == 0, onClick = { itemNumber = 0 }, icon = {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Shopping screen",
                tint = Color.White
            )

        }, label = { Text(text = "lista", color = Color.White) })
        NavigationBarItem(selected = itemNumber == 1, onClick = { itemNumber = 1 }, icon = {
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = "Shopping screen",
                tint = Color.White
            )

        }, label = { Text(text = "favoritos", color = Color.White) })
    }
}