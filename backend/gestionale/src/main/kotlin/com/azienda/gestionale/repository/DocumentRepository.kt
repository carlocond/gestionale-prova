package com.azienda.gestionale.repository

import com.azienda.gestionale.model.Document
import com.azienda.gestionale.model.DocumentType
import org.springframework.data.jpa.repository.JpaRepository

interface DocumentRepository : JpaRepository<Document, Long> {

    //Find a document by type serial and number (ex. find OC 123)
    fun findByTypeAndSerialAndNumber(type: DocumentType, serial: String, number: String): List<Document>?
}