package com.azienda.gestionale.exceptions


import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(value = [DocumentNotFoundException::class,
                                AgentNotFoundException::class,
                                ProductNotFoundException::class])
    fun handleNotFound(ex: RuntimeException): ResponseEntity<Map<String, String>>{
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapOf("error" to ex.message!!))
    }


    @ExceptionHandler(value = [InvalidBarcodeException::class,
        QuantityExceededException::class])
    fun handleBadRequest(ex: RuntimeException): ResponseEntity<Map<String, String>> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapOf("error" to ex.message!!))
    }
}