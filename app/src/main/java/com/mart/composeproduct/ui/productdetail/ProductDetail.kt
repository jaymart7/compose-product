package com.mart.composeproduct.ui.productdetail

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mart.composeproduct.R
import com.mart.composeproduct.ui.common.ProductFields
import com.mart.composeproduct.ui.common.SimpleButton
import com.mart.composeproduct.ui.common.TextFieldState
import com.mart.composeproduct.util.showToastMessage
import com.mart.composeproduct.util.viewModelProviderFactoryOf

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ProductDetail(
    navController: NavController,
    productId: Int?
) {
    if (productId == null) {
        Text(stringResource(R.string.invalid_product))
        return
    }

    val viewModel: ProductDetailViewModel = viewModel(
        key = "product_id_$productId",
        factory = viewModelProviderFactoryOf { ProductDetailViewModel(productId) }
    )

    val state by viewModel.state.collectAsState()

    ProductDetailScreen(
        state.name,
        state.price,
        viewModel::updateProduct,
        viewModel::deleteProduct,
        navController
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ProductDetailScreen(
    name: TextFieldState,
    price: TextFieldState,
    onUpdate: (String, String) -> Unit,
    onDelete: () -> Unit,
    navController: NavController,
    context: Context = LocalContext.current
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth().fillMaxHeight()
    ) {
        ProductFields(name, price)

        SimpleButton(stringResource(R.string.update)) {
            onUpdate(name.text, price.text)
            navController.popBackStack()
            context.showToastMessage(R.string.updated_successfully)
        }

        SimpleButton(stringResource(R.string.delete)) {
            onDelete()
            navController.popBackStack()
            context.showToastMessage(R.string.deleted_successfully)
        }
    }
}

@Preview
@Composable
fun ProductDetailPreview() {
    ProductDetail(rememberNavController(), 1)
}