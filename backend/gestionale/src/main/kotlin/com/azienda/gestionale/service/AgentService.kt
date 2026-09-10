package com.azienda.gestionale.service

import com.azienda.gestionale.dto.LoginRequestDTO
import com.azienda.gestionale.dto.LoginResponseDTO
import com.azienda.gestionale.exceptions.AgentNotFoundException
import com.azienda.gestionale.repository.AgentRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AgentService(private val repository: AgentRepository) {

    fun login(request: LoginRequestDTO): LoginResponseDTO {
        val agent = repository
                    .findByUsername(request.username
                                    .uppercase()
                                    .trim())?:
                                    throw AgentNotFoundException("Operatore '${request.username.uppercase()}' non trovato")

        return LoginResponseDTO(
            agentId = agent.id,
            username = agent.username,
            message = "Login effettuato con successo"
        )
    }

}