package com.azienda.gestionale.repository

import com.azienda.gestionale.model.Customer
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository : JpaRepository<Customer, Long>