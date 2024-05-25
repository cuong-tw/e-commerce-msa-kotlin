package com.research.orderservice.model

class OrderDTO(
    id: String,
    fullName: String,
    phoneNumber: String,
    address: String,
    postalCode: String,
    orderLineItemDTOList: List<OrderLineItemDTO>
)
