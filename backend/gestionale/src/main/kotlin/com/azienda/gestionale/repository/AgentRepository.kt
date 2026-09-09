package com.azienda.gestionale.repository

import com.azienda.gestionale.model.Agent
import org.springframework.data.jpa.repository.JpaRepository

interface AgentRepository : JpaRepository<Agent, Long> {

    fun findByUsername(username: String): Agent?
}