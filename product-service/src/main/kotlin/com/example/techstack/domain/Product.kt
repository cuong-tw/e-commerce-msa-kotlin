package com.example.techstack.domain

import jakarta.persistence.*
import lombok.*
import org.jetbrains.annotations.NotNull

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: String = ""

    @Column
    @NotNull
    var name: String = ""

    @Column
    var price: Double = 0.0

    @Column
    var quantity: Int = 0
}