package com.mart.composeproduct.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.mart.composeproduct.R

@Composable
fun ProductFields(
    productNameState: TextFieldState,
    productPriceState: TextFieldState
) {
    SimpleTextField(
        productNameState.text,
        stringResource(R.string.name),
        { productNameState.text = it }
    )

    SimpleTextField(
        productPriceState.text,
        stringResource(R.string.price),
        { productPriceState.text = it },
        KeyboardType.Number
    )
}