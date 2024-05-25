package com.research.orderservice.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionControllerAdvice {
    @ExceptionHandler
    fun handleIllegalStateException(ex: IllegalStateException): ResponseEntity<ErrorMessage> {
        val errorMessage = ErrorMessage()
        errorMessage.status = HttpStatus.BAD_REQUEST.value()
        errorMessage.message = ex.message
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }
}