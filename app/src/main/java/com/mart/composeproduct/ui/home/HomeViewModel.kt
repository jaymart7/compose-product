package com.mart.composeproduct.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mart.composeproduct.data.model.Product
import com.mart.composeproduct.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    private val productRepository = ProductRepository()

    private val _productList: MutableStateFlow<List<Product>> = MutableStateFlow(emptyList())
    val productList: StateFlow<List<Product>> get() = _productList

    init {
        getProductList()
    }

    private fun getProductList() {
        viewModelScope.launch {
            _productList.value = productRepository.getProductList()
        }
    }
}