package com.azienda.gestionale.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "customers")
data class Customer (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    val code: String,
    val name: String,
    val vat: String,
    val address: String,
    val city: String,
    val postalCode: String,
    val province: String,
    val telephone: String,
    val email: String,

)