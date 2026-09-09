package com.azienda.gestionale.service

import com.azienda.gestionale.dto.ScanRequestDTO
import com.azienda.gestionale.dto.ScanResponseDTO
import com.azienda.gestionale.exceptions.AgentNotFoundException
import com.azienda.gestionale.exceptions.DocumentNotFoundException
import com.azienda.gestionale.exceptions.InvalidBarcodeException
import com.azienda.gestionale.exceptions.ProductNotFoundException
import com.azienda.gestionale.exceptions.QuantityExceededException
import com.azienda.gestionale.model.Document
import com.azienda.gestionale.model.DocumentLine
import com.azienda.gestionale.model.DocumentType
import com.azienda.gestionale.model.Product
import com.azienda.gestionale.repository.AgentRepository
import com.azienda.gestionale.repository.DocumentRepository
import com.azienda.gestionale.repository.ProductRepository
import org.springframework.transaction.annotation.Transactional

open class PickingService(
    private val documentRepository: DocumentRepository,
    private val productRepository: ProductRepository,
    private val agentRepository: AgentRepository
) {

    @Transactional
    open fun scan(scanRequest: ScanRequestDTO): ScanResponseDTO {
        //Verify existence and validate
        agentRepository.findById(scanRequest.agentId).orElseThrow { AgentNotFoundException("Operatore non trovato") }
        val clientProduct = productRepository.findByBarcode(scanRequest.barcode)?: throw InvalidBarcodeException("Barcode non presente in anagrafica")
        val clientOrder = documentRepository.findById(scanRequest.documentId).orElseThrow { DocumentNotFoundException("Documento dell'ordine cliente non trovato") }
        val lineOrder = clientOrder.lines.find { it.product.id == clientProduct.id }?: throw InvalidBarcodeException("Prodotto '${clientProduct.name}' non è presente nell'ordine")

        if (lineOrder.quantityPicked >= lineOrder.quantityRequested) {
            throw QuantityExceededException("Prodotto '${clientProduct.name}' ha raggiunto il limite")
        }

        lineOrder.quantityPicked += 1
        documentRepository.save(clientOrder)

        updateOrCreateBC(clientOrder, clientProduct)
        val remaining = lineOrder.quantityRequested - lineOrder.quantityPicked
        val orderComplete = clientOrder.lines.all { it.quantityPicked >= it.quantityRequested }

        return ScanResponseDTO(
            documentId = clientOrder.id,
            scannedProduct = clientProduct.name,
            quantityLeft = remaining,
            completed = orderComplete,
            message = if (orderComplete) "Ordine completato" else "Ordine non completato"
        )
    }
    private fun updateOrCreateBC(clientOrder: Document, clientProduct: Product) {
        var bc = documentRepository.findByTypeAndSerialAndNumber(DocumentType.BC, clientOrder.serial, clientOrder.number)

        if (bc == null) {
            bc = Document(
                type = DocumentType.BC,
                serial = clientOrder.serial,
                number = clientOrder.number,
                customer = clientOrder.customer,
                supplier = clientOrder.supplier
            )
        }
        bc = documentRepository.save(bc)

        var bcLine = bc.lines.find { it.product.id == clientProduct.id }

        if (bcLine == null) {
            bcLine = DocumentLine(
                product = clientProduct,
                quantityRequested = 0,
                quantityPicked = 1,
                document = bc
            )
            bc.lines.add(bcLine)
        } else {
            bcLine.quantityPicked += 1
        }
        documentRepository.save(bc)
    }
}
