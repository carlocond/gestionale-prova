package com.azienda.gestionale.dto

data class ScanResponseDTO(
    val documentId: Long,
    val scannedProduct: String,
    val quantityLeft: Int,
    val completed: Boolean,
    val message: String
)