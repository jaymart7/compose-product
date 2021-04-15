package com.mart.composeproduct.ui.productdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mart.composeproduct.data.model.Product
import com.mart.composeproduct.data.repository.ProductRepository
import com.mart.composeproduct.ui.common.TextFieldState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductDetailViewModel(private val productId: Int) : ViewModel() {

    private val productRepository = ProductRepository()

    // Holds our view state which the UI collects via [state]
    private val _state = MutableStateFlow(ProductDetailState())
    val state: StateFlow<ProductDetailState> = _state

    init {
        getProduct(productId)
    }

    private fun getProduct(productId: Int) {
        viewModelScope.launch {
            val product = productRepository.getProduct(productId)
            _state.value = ProductDetailState().apply {
                name.text = product.name
                price.text = product.price.toString()
            }

        }
    }

    fun updateProduct(name: String, price: String) {
        viewModelScope.launch {
            val product = Product(productId, name, price.toIntOrNull() ?: 0)
            productRepository.updateProduct(product)
        }
    }

    fun deleteProduct() {
        viewModelScope.launch {
            productRepository.deleteProduct(productId)
        }
    }

}

class ProductDetailState {
    val name = TextFieldState()
    val price = TextFieldState()
}