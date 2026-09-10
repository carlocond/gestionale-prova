package com.azienda.gestionale.repository

import com.azienda.gestionale.model.Supplier
import org.springframework.data.jpa.repository.JpaRepository

interface SupplierRepository : JpaRepository<Supplier, Long>