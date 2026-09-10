package com.azienda.gestionale.dto

data class DocumentLineResponseDTO (
    val id: Long,
    val productId: Long,
    val quantityRequested: Int,
    val quantityPicked: Int
)