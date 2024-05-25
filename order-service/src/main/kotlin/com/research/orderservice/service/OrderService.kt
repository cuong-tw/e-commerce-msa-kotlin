package com.research.orderservice.service

import com.research.orderservice.model.*

interface OrderService {
    fun createOrder(orderRequest: OrderRequest): Order
    fun getOrders(): List<Order>
    fun convertToDTO(order: Order): OrderDTO
    fun convertToDTO(orderLineItem: OrderLineItem): OrderLineItemDTO
    fun convertToEntity(orderLineItemDTO: OrderLineItemDTO): OrderLineItem
}