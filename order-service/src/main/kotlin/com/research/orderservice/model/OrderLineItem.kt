package com.research.orderservice.model

import jakarta.persistence.*
import lombok.Builder

@Entity
@Table(name = "order_line_items")
@Builder
class OrderLineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: String = ""

    @Column
    var productId: String = ""

    @Column
    var price: Double = 0.0

    @Column
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