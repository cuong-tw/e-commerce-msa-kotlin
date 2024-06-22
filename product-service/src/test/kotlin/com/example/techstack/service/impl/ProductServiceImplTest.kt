package com.example.techstack.service.impl

import com.example.techstack.domain.OrderLineItemDTO
import com.example.techstack.domain.Product
import com.example.techstack.repository.ProductRepository
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.*
import java.util.*
import kotlin.test.BeforeTest

class ProductServiceImplTest {
    private lateinit var productService: ProductServiceImpl
    private val productRepository = mock<ProductRepository>()

    @BeforeTest
    fun setUp() {
        productService = ProductServiceImpl(productRepository)
    }

    @Test
    fun `should add product successfully`() {
        // given
        val product = Product("prd1", "Product 1", 1000.0, 2)

        // when
        `when`(productRepository.save(any(Product::class.java))).thenReturn(product)
        val result = productService.addProduct(product)

        // then
        assertEquals("prd1", result.id)
        assertEquals("Product 1", result.name)
        assertEquals(1000.0, result.price)
        assertEquals(2, result.quantity)
    }

    @Test
    fun `should find product successfully when product id is given`() {
        // given
        val productId = "prd1"
        val product = Product(productId, "Product 1", 1000.0, 2)

        // when
        `when`(productRepository.findById(anyString())).thenReturn(Optional.of(product))
        val result = productService.getProduct(productId)

        // then
        assert(result.isPresent)
        assertEquals("prd1", result.get().id)
        assertEquals("Product 1", result.get().name)
        assertEquals(1000.0, result.get().price)
        assertEquals(2, result.get().quantity)
    }

    @Test
    fun `should update product successfully`() {
        // given
        val productId = "prd1"
        val product = Product(productId, "Product 1", 1000.0, 2)

        val newProduct = Product(productId, "Product 2", 1500.0, 1)

        // when
        `when`(productRepository.findById(anyString())).thenReturn(Optional.of(product))
        `when`(productRepository.save(newProduct)).thenReturn(newProduct)
        val result = productService.updateProduct(newProduct)

        // then
        assertEquals("prd1", result.id)
        assertEquals("Product 2", result.name)
        assertEquals(1500.0, result.price)
        assertEquals(1, result.quantity)
    }

    @Test
    fun `should throw exception when update non-exist product`() {
        // given
        val newProduct = Product("prd1", "Product 1", 1000.0, 2)

        // when
        `when`(productRepository.findById(anyString())).thenReturn(Optional.empty())

        // then
        val exception = assertThrows<RuntimeException> { productService.updateProduct(newProduct) }
        assertEquals("Product not found", exception.message)
    }

    @Test
    fun `should delete product successfully`() {
        // given
        val id = "prd1"

        // when
        productService.deleteProduct(id)

        // then
        verify(productRepository, times(1)).deleteById(id)
    }

    @Test
    fun `should return TRUE when product exists`() {
        // given
        val id = "prd1"

        // when
        `when`(productRepository.existsById(id)).thenReturn(true)
        val isProductExist = productService.isProductExists(id)

        // then
        assertTrue(isProductExist)
    }

    @Test
    fun `should return FALSE when product does not exist`() {
        // given
        val id = "prd1"

        // when
        `when`(productRepository.existsById(id)).thenReturn(false)
        val isProductExist = productService.isProductExists(id)

        // then
        assertFalse(isProductExist)
    }

    @Test
    fun `should return product does not exist in verify payload`() {
        // given
        val orderLineItemDTOList: List<OrderLineItemDTO> = listOf(
            OrderLineItemDTO(productId = "prd1", 1000.0, 2),
            OrderLineItemDTO(productId = "prd2", 1500.0, 1)
        )

        // when
        `when`(productRepository.existsById("prd1")).thenReturn(false)
        val verifyPayload = productService.verifyProductListIsAvailable(orderLineItemDTOList)

        // then
        assertEquals(false, verifyPayload.isAvailable)
        assertEquals("Product with id prd1 does not exist", verifyPayload.message)
    }

    @Test
    fun `should return product out of stock in verify payload`() {
        // given
        val orderLineItemDTOList: List<OrderLineItemDTO> = listOf(
            OrderLineItemDTO(productId = "prd1", 1000.0, 2),
            OrderLineItemDTO(productId = "prd2", 1500.0, 1)
        )

        // when
        `when`(productRepository.existsById("prd1")).thenReturn(true)
        `when`(productRepository.findById("prd1")).thenReturn(
            Optional.of(Product("prd1", "Product 1",1000.0, 0))
        )
        val verifyPayload = productService.verifyProductListIsAvailable(orderLineItemDTOList)

        // then
        assertEquals(false, verifyPayload.isAvailable)
        assertEquals("Product with id prd1 is out of stock", verifyPayload.message)
    }

    @Test
    fun `should return product ready to sell in verify payload`() {
        // given
        val orderLineItemDTOList: List<OrderLineItemDTO> = listOf(
            OrderLineItemDTO(productId = "prd1", 1000.0, 2),
            OrderLineItemDTO(productId = "prd2", 1500.0, 1)
        )

        // when
        `when`(productRepository.existsById("prd1")).thenReturn(true)
        `when`(productRepository.findById("prd1")).thenReturn(
            Optional.of(Product("prd1", "Product 1",1000.0, 10))
        )
        `when`(productRepository.existsById("prd2")).thenReturn(true)
        `when`(productRepository.findById("prd2")).thenReturn(
            Optional.of(Product("prd2", "Product 2",1500.0, 10))
        )
        val verifyPayload = productService.verifyProductListIsAvailable(orderLineItemDTOList)

        // then
        assertEquals(true, verifyPayload.isAvailable)
        assertEquals("Ready to sell", verifyPayload.message)
    }
}
