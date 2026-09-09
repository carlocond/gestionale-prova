package com.azienda.gestionale.exceptions

//Exception if the quantity of items scanned is exceeded
class QuantityExceededException(message: String) : RuntimeException(message)