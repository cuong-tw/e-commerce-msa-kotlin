package com.research.orderservice.repository

import com.research.orderservice.model.OrderLineItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderLineItemRepository : JpaRepository<OrderLineItem, String>{
}