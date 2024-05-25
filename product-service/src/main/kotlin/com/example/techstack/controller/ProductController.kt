package com.example.techstack.controller

import com.example.techstack.domain.OrderLineItemDTO
import com.example.techstack.domain.Product
import com.example.techstack.domain.VerifyPayload
import com.example.techstack.service.impl.ProductServiceImpl
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin(value = ["*"], maxAge = 3600)
class ProductController(private val productService: ProductServiceImpl) {
    @GetMapping
    private fun getAllProducts(): List<Product> {
        return productService.findAll()
    }

    @GetMapping("/{id}")
    private fun getProduct(@PathVariable id: String): Product {
        return productService.getProduct(id).get()
    }

    @PostMapping("/verify")
    private fun verify(@RequestBody orderLineItemDTOList: List<OrderLineItemDTO>): VerifyPayload {
        return productService.verifyProductListIsAvailable(orderLineItemDTOList)
    }

    @PostMapping
    private fun addProduct(@Validated @RequestBody product: Product): Product {
        return productService.addProduct(product)
    }

    @PutMapping
    private fun updateProduct(@Validated @RequestBody product: Product): Product {
        return productService.updateProduct(product)
    }

    @DeleteMapping("/{id}")
    private fun deleteProduct(@PathVariable id: String) {
        return productService.deleteProduct(id)
    }
}