package com.azienda.gestionale.repository

import com.azienda.gestionale.model.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long> {

    fun findByBarcode(barcode: String): Product?
    fun findByName(name: String): Product?
}