package com.mart.composeproduct.data.repository

import com.mart.composeproduct.data.model.Product

val productList = mutableListOf(
    Product(1, "Rubiks", 30)
)

class ProductRepository {

    fun getProductList(): List<Product> {
        return productList
    }

    fun getProduct(productId: Int): Product {
        return productList.find { it.id == productId }!!
    }

    fun updateProduct(product: Product) {
        getProduct(product.id).apply {
            name = product.name
            price = product.price
        }
    }

    fun deleteProduct(productId: Int) {
        productList.removeAll { it.id == productId }
    }

    fun addProduct(name: String, price: String) {
        val newProduct = Product(productList.size + 1, name, price.toIntOrNull() ?: 0)
        productList.add(newProduct)
    }

}