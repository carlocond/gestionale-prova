package com.azienda.gestionale.dto

import com.azienda.gestionale.model.DocumentType

data class DocumentRequestDTO(
    val type: DocumentType,
    val serial: String,
    val number: String,
    val customerId: Long?,
    val supplierId: Long?,
    val lines: List<DocumentLineRequestDTO>?
)