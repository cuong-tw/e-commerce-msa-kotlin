package com.research.orderservice.service.impl

import com.research.orderservice.event.OrderPublishRecord
import com.research.orderservice.model.*
import com.research.orderservice.repository.OrderLineItemRepository
import com.research.orderservice.repository.OrderRepository
import com.research.orderservice.service.OrderService
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.producer.RecordMetadata
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Service
import org.springframework.util.concurrent.ListenableFuture
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Future
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@Service
class OrderServiceImpl(private val orderRepository: OrderRepository, private val orderLineItemRepository: OrderLineItemRepository) : OrderService{
    private val host: String = "localhost:9092"
    private val topic: String = "notification-topic"

    override fun createOrder(orderRequest: OrderRequest) : Order{
        val orderLineItems: List<OrderLineItem> = orderRequest.orderLineItems.stream().map { convertToEntity(it) }.toList()

        val order = Order()
        val savedOrderLineItems : MutableList<OrderLineItem> = arrayListOf()
        for (orderLineItem in orderLineItems){
            val savedOrderLineItem = orderLineItemRepository.save(orderLineItem)
            savedOrderLineItems.add(savedOrderLineItem)
        }

        order.orderLineItems = savedOrderLineItems
        order.fullName = orderRequest.fullName
        order.phoneNumber = orderRequest.phoneNumber
        order.address = orderRequest.address
        order.postalCode = orderRequest.postalCode
        val savedOrder = orderRepository.save(order)

        val orderPublishRecord = OrderPublishRecord()
        orderPublishRecord.orderNumber = savedOrder.id
        val producerRecord :ProducerRecord<String, OrderPublishRecord> = ProducerRecord(topic, orderPublishRecord)
        val map = mutableMapOf<String, String>()
        map["bootstrap.servers"] = host
        map["key.serializer"]   = "org.apache.kafka.common.serialization.StringSerializer"
        map["value.serializer"] = "org.springframework.kafka.support.serializer.JsonSerializer"
//        map["properties.spring.json.type.mapping"] = "token:com.research.orderservice.event.OrderPublishRecord"
//        map["properties.spring.json.add.type.headers"] = "false"
        val producer = KafkaProducer<String, OrderPublishRecord>(map as Map<String, Any>?)
        val future: Future<RecordMetadata> = (producer.send(producerRecord) as Future<RecordMetadata>?)!!
        println("Message sent to ${future.get().topic()} at timestamp ${future.get().timestamp()}")

        return savedOrder
    }

    override fun getOrders(): List<Order> {
        return orderRepository.findAll()
    }

    override fun convertToDTO(order: Order): OrderDTO {
        val orderLineItemDTOList: MutableList<OrderLineItemDTO> = arrayListOf()
        val orderLineItems = order.orderLineItems

        for (orderLineItem in orderLineItems){
            val orderLineItemDTO = convertToDTO(orderLineItem)
            orderLineItemDTOList.add(orderLineItemDTO)
        }

        return OrderDTO(order.id, order.fullName, order.phoneNumber, order.address, order.postalCode, orderLineItemDTOList)
    }

    override fun convertToDTO(orderLineItem: OrderLineItem): OrderLineItemDTO {
        return OrderLineItemDTO(orderLineItem.id, orderLineItem.productId, orderLineItem.price, orderLineItem.quantity)
    }

    override fun convertToEntity(orderLineItemDTO: OrderLineItemDTO): OrderLineItem {
        return OrderLineItem(orderLineItemDTO.productId, orderLineItemDTO.price, orderLineItemDTO.quantity)
    }
}