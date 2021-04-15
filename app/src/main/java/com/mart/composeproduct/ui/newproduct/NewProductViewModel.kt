package com.mart.composeproduct.ui.newproduct

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mart.composeproduct.data.repository.ProductRepository
import com.mart.composeproduct.ui.common.TextFieldState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewProductViewModel: ViewModel() {

    private val productRepository = ProductRepository()

    private val _state = MutableStateFlow(NewProductState())
    val state: StateFlow<NewProductState> = _state

    fun addProduct(name: String, price: String) {
        viewModelScope.launch {
            productRepository.addProduct(name, price)
        }
    }

}

class NewProductState {
    val productName = TextFieldState()
    val productPrice = TextFieldState()
}