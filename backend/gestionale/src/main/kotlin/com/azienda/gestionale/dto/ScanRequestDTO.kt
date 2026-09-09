package com.azienda.gestionale.dto

data class ScanRequestDTO(
    val barcode: String,
    val documentId: Long,
    val agentId: Long
)