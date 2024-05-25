package com.research.notificationservice.service

import com.research.notificationservice.OrderPublishRecord
import com.research.notificationservice.model.Order
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class NotificationService {
    @KafkaListener(topics = ["notification-topic"], groupId = "notificationId")
    fun consume(orderPublishRecord: OrderPublishRecord) {
        println("Receive an order ${orderPublishRecord.orderNumber}")
    }
}