package com.shipping_eligibility

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import java.util.*

data class ErrorDetails(val time: Date, val message: String, val details: String?)
/* Exception Types */
class InvalidInputException(override val message: String?) : Exception(message)

// This controller looks for all exceptions thrown globally, If an exception is
// implemented it should invoke the associated @ExceptionHandler function.
@ControllerAdvice
class GlobalExceptionController: ResponseEntityExceptionHandler() {

    //Handles case where bad input is provided, returns a response which pairs the data
    //class "ErrorDetails" with the http status return code.
    @ExceptionHandler(value = [(InvalidInputException::class)])
    fun handleInvalidInput(exception: InvalidInputException, request: WebRequest):
        ResponseEntity<ErrorDetails>{
        return ResponseEntity(
            ErrorDetails(Date(), "Invalid Input Received", exception.message),
            HttpStatus.BAD_REQUEST)
    }
}

