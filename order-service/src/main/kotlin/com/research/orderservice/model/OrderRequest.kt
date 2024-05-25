package com.research.orderservice.model

class OrderRequest {
    val fullName: String = ""
    val phoneNumber: String = ""
    val address: String = ""
    val postalCode: String = ""
    val orderLineItems: List<OrderLineItemDTO> = emptyList()
}