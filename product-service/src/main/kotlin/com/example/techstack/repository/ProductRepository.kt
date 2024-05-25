package com.example.techstack.repository

import com.example.techstack.domain.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, String> {
    override fun existsById(id: String) : Boolean
}