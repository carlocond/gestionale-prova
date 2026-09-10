package com.azienda.gestionale.dto

import com.azienda.gestionale.model.DocumentType

data class DocumentResponseDTO(
    val id: Long,
    val type: DocumentType,
    val serial: String,
    val number: String,
    val customerId: Long?,
    val supplierId: Long?,
    val lines: List<DocumentLineResponseDTO>
)