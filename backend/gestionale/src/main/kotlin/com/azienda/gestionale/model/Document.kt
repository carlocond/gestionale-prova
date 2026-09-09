package com.azienda.gestionale.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

enum class DocumentType {
    OC, BC, FC, OF, BF, FF, PR
}
@Entity
@Table(name = "documents")
data class Document (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Enumerated(EnumType.STRING)
    val type: DocumentType,
    val serial: String,
    val number: String,

    @OneToMany(mappedBy = "document", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val lines: MutableList<DocumentLine> = mutableListOf()
) {
    fun addLine(line: DocumentLine) {
        lines.add(line)
        line.document = this
        }
}

