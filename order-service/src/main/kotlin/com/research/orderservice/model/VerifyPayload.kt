package com.research.orderservice.model

class VerifyPayload() {
    var isAvailable: Boolean = true
    var message: String = "Ready to sell"

    constructor(isAvailable: Boolean, message: String) : this() {
        this.isAvailable = isAvailable
        this.message = message
    }
}