package com.research.notificationservice.model

class OrderLineItem {
    var id: String = ""
    var productId: String = ""
    var price: Double = 0.0
    var quantity: Int = 0

    constructor(productId: String, price: Double, quantity: Int) {
        this.productId = productId
        this.price = price
        this.quantity = quantity
    }

    constructor(id: String, productId: String, price: Double, quantity: Int) {
        this.id = id
        this.productId = productId
        this.price = price
        this.quantity = quantity
    }
}