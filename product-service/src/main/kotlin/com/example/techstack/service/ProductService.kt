package com.example.techstack.service

import com.example.techstack.domain.OrderLineItemDTO
import com.example.techstack.domain.Product
import com.example.techstack.domain.VerifyPayload
import java.util.*

interface ProductService {
    fun addProduct(product: Product): Product
    fun findAll(): List<Product>
    fun getProduct(id: String): Optional<Product>
    fun updateProduct(product: Product): Product
    fun deleteProduct(id: String)
    fun isProductExists(id: String): Boolean
    fun isProductOutOfStock(id: String, orderedQuantity: Int): Boolean
    fun verifyProductListIsAvailable(orderLineItemDTOList: List<OrderLineItemDTO>): VerifyPayload
}