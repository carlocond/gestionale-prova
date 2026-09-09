package com.azienda.gestionale.controller

import com.azienda.gestionale.dto.ScanRequestDTO
import com.azienda.gestionale.dto.ScanResponseDTO
import com.azienda.gestionale.service.PickingService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/picking")
class PickingController (private val pickingService: PickingService) {

    @PostMapping("/scan")
    fun scanBarcode(@RequestBody request: ScanRequestDTO): ResponseEntity<ScanResponseDTO> {
        val response = pickingService.scan(request)
        return ResponseEntity.ok(response)
    }

}