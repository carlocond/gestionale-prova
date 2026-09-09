package com.azienda.gestionale.exceptions

//If a product is not found
class ProductNotFoundException(message: String) : RuntimeException(message)