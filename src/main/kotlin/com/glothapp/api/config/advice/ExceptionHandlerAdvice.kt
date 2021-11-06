package com.glothapp.api.config.advice

import com.glothapp.api.util.logger
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

@ControllerAdvice
class ExceptionHandlerAdvice {

    private val log = logger<ExceptionHandlerAdvice>()

    @ExceptionHandler(
        IllegalArgumentException::class,
        MethodArgumentNotValidException::class,
        MethodArgumentTypeMismatchException::class
    )
    @ResponseBody
    fun handleBadRequestException(e: Exception): ResponseEntity<Any> {
        log.warn("Bad request", e)
        return emptyResponse(HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    @ResponseBody
    fun handleException(e: Exception): ResponseEntity<Any> {
        log.error("Internal server error", e)
        return emptyResponse(HttpStatus.INTERNAL_SERVER_ERROR)
    }

    private fun emptyResponse(httpStatus: HttpStatus): ResponseEntity<Any> {
        return ResponseEntity.status(httpStatus).body(emptyMap<String, Any>())
    }
}
