package com.mart.composeproduct.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.mart.composeproduct.data.model.Product
import com.mart.composeproduct.navigateToProductDetail

@Composable
fun Home(navController: NavHostController, viewModel: HomeViewModel = viewModel()) {
    val productList by viewModel.productList.collectAsState()

    Column {
        ProductList(productList = productList) {
            navController.navigateToProductDetail(it)
        }
    }
}

@Composable
fun ProductList(
    productList: List<Product>,
    onItemClick: (id: Int) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 0.dp, vertical = 8.dp)
    ) {
        items(
            items = productList,
            key = { product -> product.id }
        ) { product ->
            ProductRow(product) { onItemClick(it) }
            ListDivider()
        }
    }
}

@Composable
fun ProductRow(
    product: Product,
    onClick: (id: Int) -> Unit
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            onClick(product.id)
        }
        .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(text = product.name, style = MaterialTheme.typography.h4)
        Text(text = product.price.toString(), style = MaterialTheme.typography.subtitle1)
    }
}

@Composable
private fun ListDivider() {
    Divider(
        modifier = Modifier.padding(horizontal = 14.dp),
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
    )
}