package com.research.orderservice.model

import jakarta.persistence.*
import org.hibernate.annotations.Cascade
import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction

@Entity
@Table(name = "orders")
class Order() {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String = ""

    @Column
    var fullName: String = ""

    @Column
    var phoneNumber: String = ""

    @Column
    var address: String = ""

    @Column
    var postalCode: String = ""

    @OneToMany
    var orderLineItems: MutableList<OrderLineItem> = ArrayList()
}