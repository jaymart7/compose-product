package com.mart.composeproduct.ui.newproduct

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.mart.composeproduct.R
import com.mart.composeproduct.ui.common.ProductFields
import com.mart.composeproduct.ui.common.SimpleButton
import com.mart.composeproduct.ui.common.TextFieldState
import com.mart.composeproduct.util.showToastMessage

@Composable
fun NewProduct(navController: NavController) {

    val viewModel: NewProductViewModel = viewModel()

    val state by viewModel.state.collectAsState()

    NewProductScreen(
        state.productName,
        state.productPrice,
        viewModel::addProduct,
        navController
    )
}

@Composable
fun NewProductScreen(
    name: TextFieldState,
    price: TextFieldState,
    addProduct: (String, String) -> Unit,
    navController: NavController,
    context: Context = LocalContext.current
) {
    val onSubmit = {
        addProduct(name.text, price.text)
        navController.popBackStack()
        context.showToastMessage(R.string.added_successfully)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth().fillMaxHeight()
    ) {
        ProductFields(name, price)

        SimpleButton(stringResource(R.string.add_product), onSubmit)
    }
}