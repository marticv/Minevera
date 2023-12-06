package com.marti_cv.minevera.shopping.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.marti_cv.minevera.shopping.ui.model.IngredientModel
import com.marti_cv.minevera.ui.theme.AppBackground
import com.marti_cv.minevera.ui.theme.ButtonBackground
import com.marti_cv.minevera.ui.theme.TextBackground

@Composable
fun ShoppingScreen(shoppingViewModel: ShoppingViewModel) {


    val showDialog by shoppingViewModel.showDialog.observeAsState(false)


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {
        AddItemDialog(
            show = showDialog,
            onDismiss = { shoppingViewModel.onDialogClose() },
            onItemAdded = { shoppingViewModel.onItemAdded(it) })
        Column(modifier = Modifier.fillMaxSize().background(AppBackground)) {
            ShoppingList(shoppingViewModel = shoppingViewModel)
            OptionButtons(modifier = Modifier.weight(1f), shoppingViewModel)
        }
    }
}

@Composable
fun OptionButtons(modifier: Modifier = Modifier, shoppingViewModel: ShoppingViewModel) {

    Row() {
        Button(
            onClick = { shoppingViewModel.onDelete() },
            modifier = modifier.padding(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = ButtonBackground),
        ) {
            Row {
                Text(text = "Borrar", color = TextBackground)
                Spacer(modifier = Modifier.size(8.dp))
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete icon", tint = TextBackground)
            }
        }
        Button(
            onClick = { shoppingViewModel.onShowSelected() },
            modifier = modifier.padding(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = ButtonBackground),
        ) {
            Row {
                Text(text = "Añadir", color = TextBackground)
                Spacer(modifier = Modifier.size(8.dp))
                Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "add ", tint = TextBackground)
            }
        }
    }
}


@Composable
fun ShoppingList(shoppingViewModel: ShoppingViewModel) {


    val myShoppingItems: List<IngredientModel> = shoppingViewModel.shoppingList

    val lastBoughtItems: List<IngredientModel> = shoppingViewModel.boughtList

    Column {
        if (myShoppingItems.isNotEmpty()) {
            Text(
                text = "Por comprar:",
                fontSize = 12.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(8.dp)
            )
            LazyColumn {
                items(myShoppingItems, key = { ingredient -> ingredient.id }) { ingredient ->
                    ShoppingItem(
                        ingredientModel = ingredient,
                        shoppingViewModel = shoppingViewModel,
                        color = Color.Green
                    )
                }
            }

        }
        if (lastBoughtItems.isNotEmpty()) {
            Text(
                text = "Ultimas compras:",
                fontSize = 12.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(8.dp)
            )
            LazyColumn {
                items(lastBoughtItems, key = { ingredient -> ingredient.id }) { ingredient ->
                    ShoppingItem(
                        ingredientModel = ingredient,
                        shoppingViewModel = shoppingViewModel,
                        color = Color.Red
                    )
                }
            }
        }

    }
}


@Composable
fun ShoppingItem(
    ingredientModel: IngredientModel,
    shoppingViewModel: ShoppingViewModel,
    color: Color
) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clickable { shoppingViewModel.onItemSelected(ingredientModel) }) {
        Box(
            modifier = Modifier
                .background(color)
                .fillMaxWidth()
        ) {
            Text(text = ingredientModel.itemName, modifier = Modifier.padding(8.dp))
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItemDialog(show: Boolean, onDismiss: () -> Unit, onItemAdded: (String) -> Unit) {

    var myItem by remember {
        mutableStateOf("")
    }

    if (show) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White)
                    .padding(8.dp)
            ) {
                OutlinedTextField(
                    value = myItem,
                    onValueChange = { myItem = it },
                    label = {
                        Text(
                            text = "Que añadimos a la lista?"
                        )
                    },
                    singleLine = true,
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.size(8.dp))
                Button(
                    onClick = {
                        onItemAdded(myItem)
                        myItem = ""
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Añadir")
                }
            }
        }
    }
}