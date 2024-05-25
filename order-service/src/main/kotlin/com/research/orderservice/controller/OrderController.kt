package com.research.orderservice.controller

import com.research.orderservice.model.*
import com.research.orderservice.service.impl.OrderServiceImpl
import io.netty.handler.ssl.SslContextBuilder
import io.netty.handler.ssl.util.InsecureTrustManagerFactory
import org.apache.coyote.BadRequestException
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.bind.annotation.*
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.netty.http.client.HttpClient


@RestController
@RequestMapping("api/v1/orders")
@CrossOrigin(value = ["http://localhost:3000"], maxAge = 3600)
class OrderController(private val orderService: OrderServiceImpl) {
    @GetMapping
    fun getOrder(): List<Order> {
        return orderService.getOrders()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createOrder(@RequestBody orderRequest: OrderRequest): Order {
        val orderLineItemDTOs: List<OrderLineItemDTO> = orderRequest.orderLineItems
        val context = SslContextBuilder.forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build()
        val webClient = WebClient.builder()
                .clientConnector(ReactorClientHttpConnector(HttpClient.create().secure { t -> t.sslContext(context) }))
                .build()

        var verifyPayload = VerifyPayload()
        try {
            verifyPayload = webClient.post()
                    .uri("http://localhost:8080/api/v1/products/verify")
                    .bodyValue(orderLineItemDTOs)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono<VerifyPayload>()
                    .block()!!
        } catch (ex: Exception){
            println(ex.message)
        }

        val isAllOrderLineItemsAvailable = verifyPayload.isAvailable
        if(!isAllOrderLineItemsAvailable){
            throw IllegalStateException(verifyPayload.message)
        }

        return orderService.createOrder(orderRequest)
    }
}