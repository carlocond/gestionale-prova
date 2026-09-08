package com.azienda.gestionale.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
data class Document (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val type: String,
    val number: String,

    @OneToMany(mappedBy = "document", cascade = [CascadeType.ALL])
    val lines: MutableList<DocumentLine> = mutableListOf()
)

