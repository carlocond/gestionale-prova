package com.azienda.gestionale.controller

import com.azienda.gestionale.dto.LoginRequestDTO
import com.azienda.gestionale.dto.LoginResponseDTO
import com.azienda.gestionale.service.AgentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController (private val agentService: AgentService) {

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequestDTO): ResponseEntity<LoginResponseDTO> {
        val response = agentService.login(request)
        return ResponseEntity.ok(response)
    }
    
}