package com.example.techstack.service.impl

import com.example.techstack.domain.OrderLineItemDTO
import com.example.techstack.domain.Product
import com.example.techstack.domain.VerifyPayload
import com.example.techstack.repository.ProductRepository
import com.example.techstack.service.ProductService
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductServiceImpl(private val productRepository: ProductRepository) : ProductService {
    override fun addProduct(product: Product): Product {
        return productRepository.save(product)
    }

    override fun findAll(): List<Product> {
        return this.productRepository.findAll()
    }

    override fun getProduct(id: String): Optional<Product> {
        return productRepository.findById(id)
    }

    override fun updateProduct(product: Product): Product {
        var existedProductOptional: Optional<Product> = getProduct(product.id)
        if (existedProductOptional.isPresent) {
            val existedProduct: Product = existedProductOptional.get();
            existedProduct.name = product.name
            existedProduct.price = product.price
            existedProduct.quantity = product.quantity

            return productRepository.save(existedProduct)
        } else {
            throw RuntimeException("Product not found")
        }
    }

    override fun deleteProduct(id: String) {
        productRepository.deleteById(id)
    }

    override fun isProductExists(id: String): Boolean {
        return productRepository.existsById(id)
    }

    override fun isProductOutOfStock(id: String, orderedQuantity: Int): Boolean {
        val existedProduct = productRepository.findById(id).get()
        return existedProduct.quantity < orderedQuantity
    }

    override fun verifyProductListIsAvailable(orderLineItemDTOList: List<OrderLineItemDTO>): VerifyPayload {
        val verifyPayload = VerifyPayload()
        for (orderLineItem in orderLineItemDTOList) {
            val isProductExist = productRepository.existsById(orderLineItem.productId)

            if(!isProductExist) {
                verifyPayload.isAvailable = false
                verifyPayload.message = "Product with id ${orderLineItem.productId} does not exist"
                break
            }

            val isProductOutOfStock = isProductOutOfStock(orderLineItem.productId, orderLineItem.quantity)
            if(isProductOutOfStock) {
                verifyPayload.isAvailable = false
                verifyPayload.message = "Product with id ${orderLineItem.productId} is out of stock"
                break
            }
        }
        return verifyPayload
    }
}