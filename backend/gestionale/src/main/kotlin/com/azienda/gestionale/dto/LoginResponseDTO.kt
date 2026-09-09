package com.azienda.gestionale.dto

data class LoginResponseDTO(
    val agentId: Long,
    val username: String,
    val message: String
)