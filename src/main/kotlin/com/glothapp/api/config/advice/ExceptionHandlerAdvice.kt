package com.glothapp.api.config.advice

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

@ControllerAdvice
class ExceptionHandlerAdvice {

    private val log = LoggerFactory.getLogger(ExceptionHandlerAdvice::class.java)

    @ExceptionHandler(
        IllegalArgumentException::class,
        MethodArgumentNotValidException::class,
        MethodArgumentTypeMismatchException::class
    )
    @ResponseBody
    fun handleBadRequestException(e: Exception): ResponseEntity<Any> {
        log.warn("Bad request", e)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(emptyMap<String, Any>())
    }

    @ExceptionHandler(Exception::class)
    @ResponseBody
    fun handleException(e: Exception): ResponseEntity<Any> {
        log.error("Internal server error", e)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(emptyMap<String, Any>())
    }
}
