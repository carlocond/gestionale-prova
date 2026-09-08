package com.azienda.gestionale.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
data class DocumentLine (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val barcode: String,
    val name: String,
    val quantityRequested: Int,
    val quantityPicked: Int,

    //Add db
    @ManyToOne
    @JoinColumn(name = "document_id")
    val document: Document
)