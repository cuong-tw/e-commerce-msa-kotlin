package com.example.techstack.domain

import jakarta.persistence.*
import lombok.*
import org.jetbrains.annotations.NotNull

@Entity
data class Product (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String = "",

    @Column
    @NotNull
    var name: String = "",

    @Column
    var price: Double = 0.0,

    @Column
    var quantity: Int = 0
)