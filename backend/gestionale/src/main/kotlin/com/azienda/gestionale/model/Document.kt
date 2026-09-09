package com.azienda.gestionale.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

/*
enum class TipoDocumento(val sigla: String, val descrizione: String) {
    FATTURA("FT", "Fattura"),
    NOTA_CREDITO("NC", "Nota di credito"),
    DDT("DDT", "Documento di trasporto");

    companion object {
        private val perSigla = entries.associateBy { it.sigla }
        fun daSigla(sigla: String): TipoDocumento? = perSigla[sigla]
    }
}
 */

enum class DocumentType(val refCode: String, val description: String) {
    ORDINE_CLIENTE("OC", "Ordine cliente"),
    BOLLA_CLIENTE("BC", "Bolla cliente"),
    FATTURA_CLIENTE("FC", "Fattura cliente"),
    ORDINE_FORNITORE("OF", "Ordine fornitore"),
    BOLLA_FORNITORE("BF", "Bolla fornitore"),
    FATTURA_FORNITORE("FF","Fattura fornitore"),
    PREVENTIVO("PR", "Preventivo");
    //OC, BC, FC, OF, BF, FF, PR

    companion object {
        private val byRefCode = entries.associateBy { it.refCode }
        fun fromRefCode(refCode: String): DocumentType? = byRefCode[refCode]
    }
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = true)
    val customer: Customer,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", nullable = true)
    val supplier: Supplier,

    @OneToMany(mappedBy = "document", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val lines: MutableList<DocumentLine> = mutableListOf()
) {
    fun addLine(line: DocumentLine) {
        lines.add(line)
        line.document = this
        }
}

