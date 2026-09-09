package com.azienda.gestionale.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "document_lines")
data class DocumentLine (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val barcode: String,
    val name: String,
    val pkg: Int = 0,
    val quantity: Int = 0,
    val quantityRequested: Int = 0,
    val quantityPicked: Int = 0,

    //Add db
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id")
    var document: Document
)